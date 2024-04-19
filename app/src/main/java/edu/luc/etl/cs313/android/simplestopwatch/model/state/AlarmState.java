package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class AlarmState implements StopwatchState {

    public AlarmState(final StopwatchSMStateView sm) {
        this.sm = sm;

    }
    private final StopwatchSMStateView sm;


    @Override
    public void onButtonPress() {
        sm.actionStop();
        sm.actionInit();

    }

    @Override
    public void onTick() {
       sm.playAlarmSound();
    }

    @Override
    public void updateView() {

    }

    @Override
    public int getId() {
        return R.string.ALARM;
    }

}
