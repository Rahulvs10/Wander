package com.example.wander.signup;

import android.os.Handler;
import android.text.TextUtils;
import android.widget.TextView;

public class SignupInteractor {

    interface onSignupFailedListener {
        void onFirstNameError();

        void onLastNameError();

        void onPasswordError();

        void onEmailError();

        void onPhoneError();

        void onCountryError();

        void onTermsandConditionsError();

        void onSuccess();

        void onFailure();
    }

    void signup(String firstname,String lastname,String email,String phone,String password,String country,Boolean termsIsChecked,final onSignupFailedListener listener) {
        Handler handler = new Handler();

        handler.postDelayed(() -> {
            if (TextUtils.isEmpty(firstname)) {
                listener.onFirstNameError();
                return;
            }

            if (TextUtils.isEmpty(lastname)) {
                listener.onLastNameError();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                listener.onPasswordError();
                return;
            }

            if (TextUtils.isEmpty(phone)) {
                listener.onPhoneError();
                return;
            }

            if (TextUtils.isEmpty(email)) {
                listener.onEmailError();
                return;
            }

            if (TextUtils.isEmpty(country)) {
                listener.onCountryError();
                return;
            }

            if (!termsIsChecked) {
                listener.onTermsandConditionsError();
                return;
            }

            if (phone.length() == 10 && email.contains("@")) {
                listener.onSuccess();
                return;
            }

            listener.onFailure();

        },2000);

    }
}
