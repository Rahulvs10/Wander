package com.example.wander.login;

import android.os.Handler;
import android.text.TextUtils;

public class LoginInteractor {

    interface OnLoginFinishedListener {
        void onUsernameError();

        void onPasswordError();

        void onSuccess();

        void onFailure();
    }

    void login(final String username, final String password, final OnLoginFinishedListener listener){
        Handler handler = new Handler();

        //Mock login. USERNAME = 1234, PASSWORD = 1234

        handler.postDelayed(() -> {
            if (TextUtils.isEmpty(username)) {
                listener.onUsernameError();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                listener.onPasswordError();
                return;
            }

            if(TextUtils.equals(username,"1234")&&TextUtils.equals(password,"1234")){
                listener.onSuccess();
                return;
            }

            listener.onFailure();

        },2000);
    }
}
