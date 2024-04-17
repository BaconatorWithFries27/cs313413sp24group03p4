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
import java.io.IOException;


class AlarmState extends Activity implements StopwatchState {

    public AlarmState(final StopwatchSMStateView sm) {
        this.sm = sm;
        playAlarmSound();
    }

    private final StopwatchSMStateView sm;


    // RJ: This method is mainly ripped from ClickCounter (https://github.com/LoyolaChicagoCode/clickcounter-android-java)
    // RJ: I don't think this is the right sounds, but my andriod studio is tweaking nad I can't test :|
    protected void playAlarmSound() {
        final Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final MediaPlayer mediaPlayer = new MediaPlayer();
        final Context context = getApplicationContext();

        try {
            mediaPlayer.setDataSource(context, defaultRingtoneUri);
            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build());
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
            mediaPlayer.start();
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

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
