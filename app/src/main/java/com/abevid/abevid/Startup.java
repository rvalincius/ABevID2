package com.abevid.abevid;

import android.app.Application;
import android.os.SystemClock;

import java.util.concurrent.TimeUnit;

/**
 * Class used to delay startup in order to display loading splash screen
 * @author John Moser
 */
public class Startup extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Give time for Splash Screen
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(4));
    }
}