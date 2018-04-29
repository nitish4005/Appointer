package com.example.android.appointer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends Activity {
    public static final String ENTER_PASSWORD = "Enter Password";
    public static final String ENTER_USERNAME = "Enter Username";
    private EditText usernameEt,passwordEt;
    private ImageButton loginButton;
    private String username,password;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEt = findViewById(R.id.usernameEt);
        passwordEt = findViewById(R.id.passwordEt);
        loginButton = findViewById(R.id.loginBtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginWithCredentials();
            }
        });

    }

    private void loginWithCredentials() {
        username = usernameEt.getText().toString();
        password = passwordEt.getText().toString();
        if(username.trim().equals("")){
            Toast.makeText(LoginActivity.this,ENTER_USERNAME,Toast.LENGTH_LONG).show();
            return;
        }

        if (password.trim().equals("")){
            Toast.makeText(LoginActivity.this, ENTER_PASSWORD,Toast.LENGTH_LONG).show();
            return;
        }


    }
}
