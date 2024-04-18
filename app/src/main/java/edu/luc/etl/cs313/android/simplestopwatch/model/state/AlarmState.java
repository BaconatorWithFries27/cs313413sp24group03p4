package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class AlarmState implements StopwatchState {

    public AlarmState(final StopwatchSMStateView sm) {
        this.sm = sm;

    }

    /*public void playAlarmSound() {
        final Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final MediaPlayer mediaPlayer = new MediaPlayer();
        final Context context = StopwatchAdapter.getContext();

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
    }*/
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
