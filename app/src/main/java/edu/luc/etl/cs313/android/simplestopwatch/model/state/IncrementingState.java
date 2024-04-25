package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.common.Constants;

class IncrementingState implements StopwatchState {

    public IncrementingState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    int tickCountToRunning = 0;
    @Override
    public void onButtonPress() {
        sm.actionStart();
        if(sm.getTime() == Constants.MAX_TIME) {
            sm.playAlarmSound();
            sm.toRunningState();
        }
        else {
            tickCountToRunning = 0;
        }
    }


    @Override
    public void onTick() {
        tickCountToRunning++;
        //if three seconds have passed, play alarm and begin decrementing
        if (tickCountToRunning == 3) {sm.playAlarmSound(); sm.toRunningState();}
    }

    @Override
    public void updateView() {
            sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.INCREMENTING;
    }
}
