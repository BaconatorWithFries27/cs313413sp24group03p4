package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import android.widget.EditText;



class StoppedState implements StopwatchState {

    public StoppedState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onButtonPress() {
        
        int time = sm.manualTime();
        if (time == -5000) {
            sm.actionStart();
            sm.toIncrementingState();
        } else {
            sm.userInputTime(time);
            updateView();
            sm.playAlarmSound();
            sm.toRunningState();
            sm.startClock();
        }

    }
    @Override
    public void onTick() {
        throw new UnsupportedOperationException("onTick");
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.STOPPED;
    }

}
