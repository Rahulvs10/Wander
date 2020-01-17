package com.example.wander.profile.settings.Account;

import android.text.TextUtils;

import android.os.Handler;
import android.widget.Toast;

public class AccountInteractor {

    interface onUpdateFinishedListener {

        void onOldPasswordEmptyError();

        void onNewPasswordEmptyError();

        void onOldPasswordMismatchError();

        void samePasswordError();

        void onEmailError();

        void onSuccess(int error_num);

        void onFailure(String error_text,int errror_num);
    }

    void updatePassword(final String oldPassword, final String newPassword, final onUpdateFinishedListener listener) {

        //Sample old password from database :1234
        String sampleOldPassword = "1234";

        Handler handler = new Handler();

        handler.postDelayed(()-> {

            if (TextUtils.isEmpty(oldPassword)) {
                listener.onOldPasswordEmptyError();
                return;
            }

            if (TextUtils.isEmpty(newPassword)) {
                listener.onNewPasswordEmptyError();
                return;
            }

            if (!oldPassword.equals(sampleOldPassword)) {
                //If the old password entered does not match the password in the database
                listener.onOldPasswordMismatchError();
                return;
            }

            if (oldPassword.equals(newPassword)) {
                listener.samePasswordError();
                return;
            }

            if (newPassword.length() >= 6) {
                //Update the new password to the database
                listener.onSuccess(1);
                return;
            }

            listener.onFailure("The length of the new password cannot be less than 6 characters",1);

        },2000);
    }

    void updateEmail(final String email,final onUpdateFinishedListener listener) {

        Handler handler = new Handler();

        handler.postDelayed(()->{

            if (TextUtils.isEmpty(email)) {
                listener.onEmailError();
                return;
            }

            if (email.contains("@")) {
                listener.onSuccess(2);
                return;
            }

            listener.onFailure("Please enter a valid email address",2);

        },2000);
    }
}
