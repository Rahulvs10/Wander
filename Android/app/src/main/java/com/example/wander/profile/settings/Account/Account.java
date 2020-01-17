package com.example.wander.profile.settings.Account;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wander.R;

public class Account extends Fragment implements AccountView{

    private EditText oldPassword, newPassword,email_address;
    private TextView text_error_password,text_error_email;
    private Button btn_change_psw,btn_change_email;
    private String old_psw,new_psw,email;
    private AccountPresenter presenter;
    private ProgressBar progressBar;

    public Account() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        text_error_email = view.findViewById(R.id.error_email);
        text_error_password = view.findViewById(R.id.error_psw);
        oldPassword = view.findViewById(R.id.old_psw);
        newPassword = view.findViewById(R.id.new_psw);
        email_address = view.findViewById(R.id.email_address);
        btn_change_psw = view.findViewById(R.id.btn_change_psw);
        btn_change_email = view.findViewById(R.id.btn_change_email);
        progressBar = view.findViewById(R.id.progress);

        presenter = new AccountPresenter(this, new AccountInteractor());

        btn_change_psw.setOnClickListener(v->updatePassword());
        btn_change_email.setOnClickListener(v->updateEmail());
        return view;
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void setOldPasswordEmptyError() {
        oldPassword.setError("Old Password field cannot be empty");
    }

    @Override
    public void setNewPasswordEmptyError() {
        newPassword.setError("New Passoword field cannot be empty");
    }

    @Override
    public void setOldPasswordMismatchError() {
        showErrorText("Old password is not right!",1);
    }

    @Override
    public void setSamePasswordError() {
        showErrorText("New password cannot be the same as the old password!",1);
    }

    @Override
    public void setEmailError() {
        email_address.setError("Email address field cannot be empty!");
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showSuccess(String success_message) {
        oldPassword.getText().clear();
        newPassword.getText().clear();
        email_address.getText().clear();
        Toast.makeText(getContext(),success_message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorText(String errorText,int error_num) {

        /* error_num = 1 => Error in password
        error_num = 2 => Error in email */
        switch (error_num) {
            case 1:
                text_error_password.setText(errorText);
                text_error_password.setVisibility(View.VISIBLE);
                break;
            case 2:
                text_error_email.setText(errorText);
                text_error_email.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void hideErrorText() {
        /* error_num = 1 => Error in password
        error_num = 2 => Error in email */
        text_error_password.setVisibility(View.GONE);
        text_error_email.setVisibility(View.GONE);
    }

    private void updatePassword() {
        old_psw = oldPassword.getText().toString();
        new_psw = newPassword.getText().toString();
        presenter.updatePassword(old_psw,new_psw);
    }

    private void updateEmail() {
        email = email_address.getText().toString();
        presenter.updateEmail(email);
    }
}
