package com.example.natw.annonce.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.natw.annonce.R;
import com.example.natw.annonce.annonces.Annonce;

public class Login extends AppCompatActivity {

    private static final String ACCOUNT_PASSWORD = "password";
    private static final String ACCOUNT_NAME = "name";
    private static final String REMEMBER = "connected";
    private SharedPreferences myPreferences;
    private EditText emailAccount;
    private EditText passwordAccount;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findWidget();
        isEvenLogin();
    }

    private void findWidget() {
        this.checkBox = findViewById(R.id.rememberMe);
        this.emailAccount = findViewById(R.id.email);
        this.passwordAccount = findViewById(R.id.password);
    }


    public void managePrefs(View view) {
        myPreferences = getSharedPreferences(REMEMBER, MODE_PRIVATE);
        SharedPreferences.Editor myEditor = myPreferences.edit();

        if (checkBox.isChecked() && !(this.emailAccount.getText().toString().equals("") && this.passwordAccount.getText().toString().equals(""))) {
            myEditor.putString(ACCOUNT_NAME, this.emailAccount.getText().toString());
            myEditor.putString(ACCOUNT_PASSWORD, this.passwordAccount.getText().toString());
            myEditor.putBoolean(REMEMBER, checkBox.isChecked());
        } else if (!checkBox.isChecked()) {
            myEditor.clear();
            myEditor.putBoolean(REMEMBER, false);
        }
        myEditor.apply();

        Intent myIntent = new Intent(this, Annonce.class);
        startActivity(myIntent);
    }

    private void isEvenLogin() {
        myPreferences = getSharedPreferences(REMEMBER, MODE_PRIVATE);
        boolean isLogin = myPreferences.getBoolean(REMEMBER, false);
        Snackbar.make(findViewById(R.id.layout), "isLogin = " + isLogin, Snackbar.LENGTH_LONG).show();
        if (isLogin) {
            this.emailAccount.setText(myPreferences.getString(ACCOUNT_NAME, ""));
            this.passwordAccount.setText(myPreferences.getString(ACCOUNT_PASSWORD, ""));
            this.checkBox.setChecked(true);
        }
    }
}
