package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import android.media.MediaPlayer;


class AlarmState extends MediaPlayer implements StopwatchState {

    public AlarmState(final StopwatchSMStateView sm) {
        this.sm = sm;
        //MediaPlayer mediaPlayer = MediaPlayer.create(, R.raw.beep);
       // mediaPlayer.start();
    }

    private final StopwatchSMStateView sm;


    @Override
    public void onButtonPress() {
        sm.actionStop();
        sm.actionInit();
    }

    @Override
    public void onTick() {
        /*throw new UnsupportedOperationException("onTick");*/
    }

    @Override
    public void updateView() {

    }

    @Override
    public int getId() {
        return R.string.ALARM;
    }

}
