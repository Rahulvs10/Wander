package com.example.wander.profile.settings.Account;

public class AccountPresenter implements AccountInteractor.onUpdateFinishedListener{

    private AccountInteractor accountInteractor;
    private AccountView accountView;

    AccountPresenter(AccountView accountView,AccountInteractor accountInteractor) {
        this.accountInteractor = accountInteractor;
        this.accountView = accountView;
    }

    void updatePassword(String oldPassword,String newPassword) {
        if(accountView!=null){
            accountView.showProgress();
            accountView.hideErrorText();
        }
        accountInteractor.updatePassword(oldPassword,newPassword,this);
    }

    void updateEmail(String email) {
        if(accountView!=null){
            accountView.showProgress();
            accountView.hideErrorText();
        }
        accountInteractor.updateEmail(email,this);
    }

    public void onDestroy(){
        accountView=null;
    }

    @Override
    public void onOldPasswordEmptyError() {
        if(accountView!=null){
            accountView.setOldPasswordEmptyError();
            accountView.hideProgress();
        }
    }

    @Override
    public void onNewPasswordEmptyError() {
        if(accountView!=null){
            accountView.setNewPasswordEmptyError();
            accountView.hideProgress();
        }
    }

    @Override
    public void onOldPasswordMismatchError() {
        if(accountView!=null){
            accountView.setOldPasswordMismatchError();
            accountView.hideProgress();
        }
    }

    @Override
    public void samePasswordError() {
        if(accountView!=null){
            accountView.setSamePasswordError();
            accountView.hideProgress();
        }
    }

    @Override
    public void onEmailError() {
        if(accountView!=null){
            accountView.setEmailError();
            accountView.hideProgress();
        }
    }

    @Override
    public void onSuccess(int error_num) {
        if(accountView!=null){
            switch (error_num) {
                case 1:
                    accountView.showSuccess("Successfully updated the password!");
                    break;
                case 2:
                    accountView.showSuccess("Successfully added your email address!");
                    break;
            }
            accountView.hideProgress();
        }
    }

    @Override
    public void onFailure(String error_text,int errror_num) {
        if(accountView!=null){
            accountView.showErrorText(error_text,errror_num);
            accountView.hideProgress();
        }
    }
}
