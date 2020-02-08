package com.example.wander.signup;

public interface SignupView {

    void showProgress();

    void hideProgress();

    void setFirstnameError();

    void setLastnameError();

    void setPasswordError();

    void setCountryError();

    void setPhoneNumberError();

    void setEmailError();

    void setTermsandConditionsError();

    void showCredentialsError();

    void hideCredentialError();

    void navigateToLogin();
}
