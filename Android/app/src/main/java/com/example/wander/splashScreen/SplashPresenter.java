package com.example.wander.splashScreen;

public class SplashPresenter implements SplashInteractor.onSplashFinishedListener {

    private SplashView splashView;
    private SplashInteractor splashInteractor;

    SplashPresenter(SplashView splashView, SplashInteractor splashInteractor) {
        this.splashView = splashView;
        this.splashInteractor = splashInteractor;
    }

    public void onDestroy(){ splashView = null; }

    public void callSplash(){ splashInteractor.splash(this); }

    @Override
    public void onSuccess() {
        if (splashView != null) {
            splashView.navigateToLogin();
        }
    }
}
