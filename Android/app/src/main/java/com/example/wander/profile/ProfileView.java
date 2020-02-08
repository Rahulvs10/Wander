package com.example.wander.profile;

public interface ProfileView {

    void showProgress();

    void hideProgress();

    void setNameError();

    void setAgeError();

    void setGenderError();

    void showSuccessfulUpdate();

    void logout();

    void showErrorText();

    void hideErrorText();

}
