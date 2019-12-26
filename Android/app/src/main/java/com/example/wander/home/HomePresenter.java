package com.example.wander.home;

import android.widget.ImageView;

public class HomePresenter implements HomeInteractor.onImageStoredListener {

    private HomeView homeView;
    private HomeInteractor homeInteractor;

    public HomePresenter(HomeView homeView, HomeInteractor homeInteractor) {
        this.homeView = homeView;
        this.homeInteractor = homeInteractor;
    }

    public void storeImage(ImageView imageView) {
        if (homeView != null) {
            homeView.showProgress();
        }
        homeInteractor.storeImage(imageView,this);
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
