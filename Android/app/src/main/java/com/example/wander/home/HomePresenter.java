package com.example.wander.home;

import android.graphics.Bitmap;

public class HomePresenter implements HomeInteractor.onImageStoredListener {

    private HomeView homeView;
    private HomeInteractor homeInteractor;

    public HomePresenter(HomeView homeView, HomeInteractor homeInteractor) {
        this.homeView = homeView;
        this.homeInteractor = homeInteractor;
    }

    public void storeImage(Bitmap capturedImage) {
        if (homeView != null) {
            homeView.showProgress();
        }
        homeInteractor.storeImage(capturedImage,this);
    }

    public void onDestroy(){
        homeView=null;
    }

    @Override
    public void onSuccess() {
        if (homeView != null) {
            homeView.hideProgress();
            homeView.displaySuccess();
        }
    }

    @Override
    public void onFailure() {
        if (homeView != null) {
            homeView.hideProgress();
            homeView.displayFailure();
        }
    }
}
