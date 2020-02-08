package com.example.wander.profile.settings.Account;

public interface AccountView {

    void setOldPasswordEmptyError();

    void setNewPasswordEmptyError();

    void setOldPasswordMismatchError();

    void setSamePasswordError();

    void setEmailError();

    void showProgress();

    void hideProgress();

    void showSuccess(String success_message);

    void showErrorText(String errorText,int error_num);

    void hideErrorText();

}
