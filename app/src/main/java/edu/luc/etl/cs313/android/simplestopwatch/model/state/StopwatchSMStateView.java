package edu.luc.etl.cs313.android.simplestopwatch.model.state;

/**
 * The restricted view states have of their surrounding state machine.
 * This is a client-specific interface in Peter Coad's terminology.
 *
 * @author laufer
 */
interface StopwatchSMStateView {
    int getTime();

    // transitions
    void toRunningState();
    void toStoppedState();
    void toIncrementingState();
    void toAlarmState();

    // actions
    void actionInit();
    void actionStart();
    void actionStop();
    void actionInc();
    void actionUpdateView();
    void actionReset();

    // state-dependent UI updates
    void updateUIRuntime();
    void onButtonPress();
    void playAlarmSound();

    int manualTime();
    void userInputTime(int time);
}
