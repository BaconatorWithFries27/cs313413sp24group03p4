package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import android.widget.EditText;
import edu.luc.etl.cs313.android.simplestopwatch.common.Constants;


class StoppedState implements StopwatchState {

    public StoppedState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onButtonPress() {
        //Depending on user number entry, can go to either incrementing or running state. Call statemachine / forward to adapter to check
        // if the user entered a number. -5000 is sentinel value if user entered no number in UI.
        
        int time = sm.manualTime();
        if (time == Constants.UI_SENTINEL) {
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
