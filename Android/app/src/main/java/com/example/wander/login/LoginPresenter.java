package com.example.wander.login;

public class LoginPresenter implements LoginInteractor.OnLoginFinishedListener{
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenter(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    public void validateCredentials(String username, String password){
        if(loginView != null){
            loginView.showProgress();
            loginView.hideCredentialError();
        }

        loginInteractor.login(username,password,this);
    }

    public void onDestroy(){
        loginView=null;
    }

    @Override
    public void onUsernameError() {
        if(loginView!=null){
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if(loginView!=null){
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if(loginView!=null){
            loginView.navigateToHome();
        }
    }

    @Override
    public void onFailure() {
        if(loginView!=null){
            loginView.showCredentialsError();
            loginView.hideProgress();
        }
    }
}
