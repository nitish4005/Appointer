package com.example.android.appointer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {
   private EditText name,number,email,password,confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name = findViewById(R.id.signupName);
        number = findViewById(R.id.signupNumber);
        email = findViewById(R.id.signupEmail);
        password = findViewById(R.id.signupPassword);
        confirmPassword = findViewById(R.id.signupConfirmPassword);
    }
}
