package com.example.wander.profile;

import android.os.Handler;
import android.text.TextUtils;

public class ProfileInteractor {

    interface OnUpdateFinishedListener {

        void onNameError();

        void onAgeError();

        void onSuccess();

        void onFailure();
    }

    void update(final String name, final String age,final int gender, final OnUpdateFinishedListener listener){
        Handler handler = new Handler();


        handler.postDelayed(() -> {
            if (TextUtils.isEmpty(name)) {
                listener.onNameError();
                return;
            }
            if (TextUtils.isEmpty(age)) {
                listener.onAgeError();
                return;
            }

            if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(age)){
                //Update the profile information in the database from here

                listener.onSuccess();
                return;
            }

            listener.onFailure();

        },2000);
    }
}
