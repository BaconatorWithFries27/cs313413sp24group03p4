package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class IncrementingState implements StopwatchState {

    public IncrementingState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    int tickCountToRunning = 0;
    @Override
    public void onButtonPress() {
        sm.actionStart();
        tickCountToRunning = 0;
    }


    @Override
    public void onTick() {
        //sm.actionInc();
        tickCountToRunning++;
        if (tickCountToRunning == 3) {sm.toRunningState();}
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