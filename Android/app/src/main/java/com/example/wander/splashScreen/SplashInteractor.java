package com.example.wander.splashScreen;

import android.os.Handler;

public class SplashInteractor {

    interface onSplashFinishedListener {
        void onSuccess();
    }

    void splash(final onSplashFinishedListener splashFinishedListener) {
        Handler handler = new Handler();

        // Splash time = 2 seconds
        int SPLASH_TIME = 2000;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                splashFinishedListener.onSuccess();
            }
        },SPLASH_TIME);
    }
}
