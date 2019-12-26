package com.example.wander.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.wander.home.HomeActivity;
import com.example.wander.R;
import com.example.wander.signup.SignupActivity;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private Button loginButton;
    private TextView errorText;
    private TextView signUp;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progress);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.button);
        errorText = findViewById(R.id.errorText);
        signUp = findViewById(R.id.signup);

        presenter = new LoginPresenter(this, new LoginInteractor());

        loginButton.setOnClickListener(view -> validateCredentials());

        signUp.setOnClickListener(view -> navigateToSignUp());

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
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
    public void setUsernameError() {
        username.setError("Username cannot be empty!");
    }

    @Override
    public void setPasswordError() {
        password.setError("password cannot be empty!");
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void showCredentialsError() {
        errorText.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCredentialError() {
        errorText.setVisibility(View.INVISIBLE);
    }

    @Override
    public void navigateToSignUp() {
        startActivity(new Intent(this, SignupActivity.class));
    }

    public void validateCredentials(){
        presenter.validateCredentials(username.getText().toString(),password.getText().toString());
    }
}
