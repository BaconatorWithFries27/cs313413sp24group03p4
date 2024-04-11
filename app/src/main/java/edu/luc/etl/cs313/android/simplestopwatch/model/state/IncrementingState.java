package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class IncrementingState implements StopwatchState {

    public IncrementingState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onButtonPress() {
        sm.actionStart();
    }

    @Override
    public void onTick() {
        sm.actionInc();
    }

    @Override
    public void updateView() {
            sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.LAP_RUNNING;
    }
}
