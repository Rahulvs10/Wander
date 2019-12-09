package com.example.wander.login;

public interface LoginView {

    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();

    void showCredentialsError();

    void hideCredentialError();

    void navigateToSignUp();
}
