package com.example.wander.profile;

public class ProfilePresenter implements ProfileInteractor.OnUpdateFinishedListener{
    private ProfileView profileView;
    private ProfileInteractor profileInteractor;

    public ProfilePresenter(ProfileView profileView, ProfileInteractor profileInteractor) {
        this.profileView = profileView;
        this.profileInteractor = profileInteractor;
    }


    public void update(String name,String age,int gender) {
        if(profileView != null){
            profileView.showProgress();
            profileView.hideErrorText();
        }

        profileInteractor.update(name,age,gender,this);
    }

    public void onDestroy(){
        profileView=null;
    }

    @Override
    public void onNameError() {
        if(profileView!=null){
            profileView.setNameError();
            profileView.hideProgress();
        }
    }

    @Override
    public void onAgeError() {
        if(profileView!=null){
            profileView.setAgeError();
            profileView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if(profileView!=null){
            profileView.showSuccessfulUpdate();
            profileView.hideProgress();
        }
    }

    @Override
    public void onFailure() {
        if(profileView!=null){
            profileView.showErrorText();
            profileView.hideProgress();
        }
    }
}

