package com.example.wander.home;


public interface HomeView {
    void openCamera();

    void navigateToProfile();

    void navigateToFeed();

    void showProgress();

    void hideProgress();

    void displaySuccess();

    void displayFailure();

    void logout();
}
