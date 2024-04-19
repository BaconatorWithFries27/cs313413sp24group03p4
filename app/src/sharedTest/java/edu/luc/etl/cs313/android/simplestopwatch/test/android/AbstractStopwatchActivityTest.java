package edu.luc.etl.cs313.android.simplestopwatch.test.android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import android.widget.Button;
import android.widget.TextView;
import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.android.StopwatchAdapter;

import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.SEC_PER_MIN;

/**
 * Abstract GUI-level test superclass of several essential stopwatch scenarios.
 *
 * @author laufer
 *
 * TODO move this and the other tests to src/test once Android Studio supports
 * non-instrumentation unit tests properly.
 */
public abstract class AbstractStopwatchActivityTest {

    /**
     * Verifies that the activity under test can be launched.
     */
    @Test
    public void testActivityCheckTestCaseSetUpProperly() {
        assertNotNull("activity should be launched successfully", getActivity());
    }

    /**
     * Verifies the following scenario: time is 0.
     *
     * @throws Throwable
     */
    @Test
    public void testActivityScenarioInit() throws Throwable {
        getActivity().runOnUiThread(() -> assertEquals(0, getDisplayedValue()));
    }

    /**
     * Verifies the following scenario: time is 0, press button 3 times, expected time 3.
     *
     * @throws Throwable
     */
    @Test
    public void testActivityScenarioIncrement() throws Throwable {
        getActivity().runOnUiThread(() -> {
            assertEquals(0, getDisplayedValue());
            for (int i = 0; i < 3; i++) {
                assertTrue(getStartStopButton().performClick());
            }
        });
        getActivity().runOnUiThread(() -> {
            assertEquals(3, getDisplayedValue());
            assertTrue(getStartStopButton().performClick());
        });
    }

    @Test
    public void testActivityScenarioRun() throws Throwable {

        getActivity().runOnUiThread(() -> {

            assertEquals(0, getDisplayedValue());
            for (int i = 0; i < 3; i++) {
                assertTrue(getStartStopButton().performClick());
            }
            assertEquals(3, getDisplayedValue());
            //assertTrue(getStartStopButton().performClick());
            long startTime = System.currentTimeMillis();
            long endTime = startTime + 4500;
            while(System.currentTimeMillis() < endTime) {System.out.print("");}

        });

        //long startTime = System.currentTimeMillis();
        //long endTime = startTime + 4500;
        //while(System.currentTimeMillis() < endTime) {}

        //Thread.sleep(4200); // <-- do not run this in the UI thread!
        //runUiThreadTasks();
        //getActivity().runOnUiThread(() -> {
            assertEquals(2, getDisplayedValue());
            assertTrue(getStartStopButton().performClick());
        //});
    }

    /**
     * Verifies the following scenario: time is 0, press start, wait 5+ seconds,
     * expect time 5, press lap, wait 4 seconds, expect time 5, press start,
     * expect time 5, press lap, expect time 9, press lap, expect time 0.
     *
     * @throws Throwable
     */

    // auxiliary methods for easy access to UI widgets

    protected abstract StopwatchAdapter getActivity();

    protected int tvToInt(final TextView t) {
        return Integer.parseInt(t.getText().toString().trim());
    }

    protected int getDisplayedValue() {
        final TextView ts = getActivity().findViewById(R.id.seconds);
        return tvToInt(ts);
    }

    protected Button getStartStopButton() {
        return getActivity().findViewById(R.id.Increment);
    }

    /*protected Button getResetLapButton() {
        //return getActivity().findViewById(R.id.resetLap);
    }*/

    /**
     * Explicitly runs tasks scheduled to run on the UI thread in case this is required
     * by the testing framework, e.g., Robolectric.
     */
    protected void runUiThreadTasks() { }
}
