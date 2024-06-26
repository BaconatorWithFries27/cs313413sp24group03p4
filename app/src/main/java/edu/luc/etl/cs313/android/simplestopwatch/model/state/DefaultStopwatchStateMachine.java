package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchModelListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;


/**
 * An implementation of the state machine for the stopwatch.
 *
 * @author laufer
 */
public class DefaultStopwatchStateMachine implements StopwatchStateMachine {

    public DefaultStopwatchStateMachine(final TimeModel timeModel, final ClockModel clockModel) {
        this.timeModel = timeModel;
        this.clockModel = clockModel;
    }

    private final TimeModel timeModel;

    private final ClockModel clockModel;



    /**
     * The internal state of this adapter component. Required for the State pattern.
     */
    private StopwatchState state;

    protected void setState(final StopwatchState state) {
        this.state = state;
        listener.onStateUpdate(state.getId());
    }

    private StopwatchModelListener listener;

    @Override
    public void setModelListener(final StopwatchModelListener listener) {
        this.listener = listener;
    }

    // forward event uiUpdateListener methods to the current state
    // these must be synchronized because events can come from the
    // UI thread or the timer thread
    @Override public synchronized void onButtonPress() { state.onButtonPress(); }
    @Override public synchronized void onTick()      { state.onTick(); }

    @Override public void updateUIRuntime() { listener.onTimeUpdate(timeModel.getRuntime()); }

    @Override public int getTime(){ return timeModel.getRuntime();}

    // known states

    private final StopwatchState STOPPED      = new StoppedState(this);
    private final StopwatchState RUNNING      = new RunningState(this);
    private final StopwatchState INCREMENTING = new IncrementingState(this);
    private final StopwatchState ALARM        = new AlarmState(this);

    // transitions
    @Override public void toRunningState()    { setState(RUNNING); }
    @Override public void startClock() { clockModel.start(); }
    @Override public void toStoppedState()    { setState(STOPPED); }
    @Override public void toIncrementingState() { setState(INCREMENTING); clockModel.start(); }
    @Override public void toAlarmState() { setState(ALARM); }

    // actions
    @Override public void actionInit()       { toStoppedState(); actionReset(); }
    public void actionReset()      { timeModel.resetRuntime(); actionUpdateView(); }
    @Override public void actionStart()      {timeModel.addRunTime(); actionUpdateView(); }
    @Override public void actionStop()       { clockModel.stop(); }
    @Override public void actionInc()        { timeModel.decRuntime(); actionUpdateView(); }
    @Override public void actionUpdateView() { state.updateView(); }




    @Override public void playAlarmSound() {listener.playAlarmSound();}

    public void userInputTime(int time){ timeModel.setTime(time); }

    //Forward call from stopped state to adapter to get UI number (if any)
    public int manualTime() {
        int time = listener.getUserNum();
        return time;
    }
}
