package com.example.wander.signup;

public class SignupPresenter implements SignupInteractor.onSignupFailedListener {

    private SignupView signupView;
    private SignupInteractor signupInteractor;

    public SignupPresenter(SignupView signupView, SignupInteractor signupInteractor) {
        this.signupView = signupView;
        this.signupInteractor = signupInteractor;
    }

    public void validateCredentials(String firstname,String lastname,String email,String phone,String password,String country,Boolean termsIsChecked){
        if(signupView != null){
            signupView.showProgress();
            signupView.hideCredentialError();
        }

        signupInteractor.signup(firstname,lastname,email,phone,password,country,termsIsChecked,this);
    }

    public void onDestroy(){
        signupView=null;
    }

    @Override
    public void onFirstNameError() {
        if(signupView!=null){
            signupView.setFirstnameError();
            signupView.hideProgress();
        }
    }

    @Override
    public void onLastNameError() {
        if(signupView!=null){
            signupView.setLastnameError();
            signupView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if(signupView!=null){
            signupView.setPasswordError();
            signupView.hideProgress();
        }
    }

    @Override
    public void onEmailError() {
        if(signupView!=null){
            signupView.setEmailError();
            signupView.hideProgress();
        }
    }

    @Override
    public void onPhoneError() {
        if(signupView!=null){
            signupView.setPhoneNumberError();
            signupView.hideProgress();
        }
    }

    @Override
    public void onCountryError() {
        if(signupView!=null){
            signupView.setCountryError();
            signupView.hideProgress();
        }
    }

    @Override
    public void onTermsandConditionsError() {
        if(signupView!=null){
            signupView.setTermsandConditionsError();
            signupView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if(signupView!=null){
            signupView.navigateToLogin();
        }
    }

    @Override
    public void onFailure() {
        if(signupView!=null){
            signupView.showCredentialsError();
            signupView.hideProgress();
        }
    }
}
