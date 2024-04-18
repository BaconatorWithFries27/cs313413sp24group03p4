package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import edu.luc.etl.cs313.android.simplestopwatch.android.StopwatchAdapter;

import java.io.IOException;


class AlarmState extends Activity implements StopwatchState {

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
