package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import android.media.MediaPlayer;
import edu.luc.etl.cs313.android.simplestopwatch.R;

class RunningState implements StopwatchState {

    public RunningState(final StopwatchSMStateView sm) {
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
        sm.actionInc();
        //When done decrementing, play alarm and go to alarm state
        if (sm.getTime() == 0) {
            sm.toAlarmState();
        }
        else {
            sm.toRunningState();
        }
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.RUNNING;
    }
}
