package com.example.wander.profile;

public interface ProfileView {

    void showProgress();

    void hideProgress();

    void setNameError();

    void setAgeError();

    void showSuccessfulUpdate();

    void showErrorText();

    void hideErrorText();

}
