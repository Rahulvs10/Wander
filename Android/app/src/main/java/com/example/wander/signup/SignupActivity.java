package com.example.wander.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wander.R;
import com.example.wander.login.LoginActivity;

public class SignupActivity extends AppCompatActivity implements SignupView {

    private ProgressBar progressBar;
    private EditText firstname;
    private EditText lastname;
    private EditText email;
    private EditText phone;
    private TextView country;
    private Spinner countries;
    private EditText password;
    private CheckBox terms;
    private TextView errorText;
    private Button signup;
    private SignupPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        progressBar = findViewById(R.id.progress);
        firstname = findViewById(R.id.first_name);
        lastname = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        country = findViewById(R.id.country);
        countries = findViewById(R.id.country_spinner);
        password = findViewById(R.id.password);
        terms = findViewById(R.id.terms);
        errorText = findViewById(R.id.errorText);
        signup = findViewById(R.id.btn_signup);

        presenter = new SignupPresenter(this, new SignupInteractor());

        signup.setOnClickListener(view -> validateCredentials());

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
    public void setFirstnameError() {
        firstname.setError("First name cannot be empty");
    }

    @Override
    public void setLastnameError() {
        lastname.setError("Last name cannot be empty");
    }

    @Override
    public void setPasswordError() {
        password.setError("Password cannot be empty");
    }

    @Override
    public void setCountryError() {
        country.setError("Select a country");
    }

    @Override
    public void setPhoneNumberError() {
        phone.setError("Phone number cannot be empty");
    }

    @Override
    public void setEmailError() {
        email.setError("Email cannot be empty");
    }

    @Override
    public void setTermsandConditionsError() {
        terms.setError("Check the terms and conditions");
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
    public void navigateToLogin() {
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
    }

    public void validateCredentials() {
        presenter.validateCredentials(firstname.getText().toString(),lastname.getText().toString(),email.getText().toString(),phone.getText().toString(),password.getText().toString(),countries.getSelectedItem().toString(),terms.isChecked());
    }
}
