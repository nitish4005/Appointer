package com.example.android.appointer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.appointer.Model.MyLocation;
import com.example.android.appointer.Model.SignupModelClass;
import com.example.android.appointer.Model.SignupResponseModel;
import com.example.android.appointer.Service.ServiceFactory;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SignupActivity extends AppCompatActivity {
    private EditText name, number, email, password, confirmPassword,enterCode;
    private CheckBox checkBox;
    private ImageButton next;
    private CountryCodePicker cpp;
    private LinearLayout layout1;
    private RelativeLayout layout2;
    private Button resend,verifyCode;
    private TextView verifyNumberTv,waitMessageTv;
    private FirebaseAuth mAuth;
    private String phoneNumber,mVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private SignupModelClass signupModelClass;
    private MyLocation location;
    private SignupResponseModel signupResponseModel = null;
    private FusedLocationProviderClient mFusedLocationClient;
    private ArrayList<Long> coordinates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        coordinates.add(12323L);
        coordinates.add(133533L);
        location = new MyLocation("point",coordinates);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        initViews();
        setButtonsOnClick();



}
    private void initViews() {
        layout1 = findViewById(R.id.layoutSignUpForm);
        layout2 = findViewById(R.id.layout2);
        name = findViewById(R.id.signupName);
        number = findViewById(R.id.signupNumber);
        email = findViewById(R.id.signupEmail);
        password = findViewById(R.id.signupPassword);
        confirmPassword = findViewById(R.id.signupConfirmPassword);
        checkBox = findViewById(R.id.signupTermsAndConditionsCheckBox);
        next = findViewById(R.id.signupButton);
        cpp = findViewById(R.id.countryCodePicker);
        verifyNumberTv= findViewById(R.id.verifyNumber);
        waitMessageTv= findViewById(R.id.waitMesseage_TV);
        enterCode= findViewById(R.id.enterCode);
        resend= findViewById(R.id.resend);
        mAuth= FirebaseAuth.getInstance();
        verifyCode= findViewById(R.id.verify_code);
    }

    private void setButtonsOnClick() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout1.setVisibility(View.INVISIBLE);
                cpp.registerCarrierNumberEditText(number);
                phoneNumber="+" + cpp.getFullNumber();
                verifyNumberTv.append(" "+phoneNumber);
                waitMessageTv.append(phoneNumber);
                Log.i("phonenumber",phoneNumber);
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phoneNumber,        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        SignupActivity.this,               // Activity (for callback binding)
                        mCallbacks);        // OnVerificationStateChangedCallbacks

                layout2.setVisibility(View.VISIBLE);
            }
        });
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Log.i("onVerificationCompleted","Automatically code detected");
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.i("error",Log.getStackTraceString(e));
            }
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.

                // Save verification ID and resending token so we can use them later
                Log.i("onCodeSent","Code has been sent");
                mVerificationId = verificationId;
                mResendToken = token;

                // ...
            }

        };
        verifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = enterCode.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
                signInWithPhoneAuthCredential(credential);
            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phoneNumber,        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        SignupActivity.this,               // Activity (for callback binding)
                        mCallbacks);        // OnVerificationStateChangedCallbacks

                layout2.setVisibility(View.VISIBLE);
            }

        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("tasksuccesfull","loged in sucessfully");
                            final SignupModelClass signupModelClass = new SignupModelClass(location,name.getText().toString(),
                                    phoneNumber,email.getText().toString(),password.getText().toString());
                            new AsyncTask<Void,Void,SignupResponseModel>() {

                                @Override
                                protected SignupResponseModel doInBackground(Void... voids) {
                                     signupResponseModel= ServiceFactory.getNetworkCalls_service().signupUser(signupModelClass);
                                    return signupResponseModel;
                                }

                                @Override
                                protected void onPostExecute(SignupResponseModel signupResponseModel) {
                                    Log.i("nk","inside postexecue123");
                                    if(signupModelClass!=null) {
                                        Intent mainIntent = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(mainIntent);
                                        finish();
                                        Log.i("nk","inside postexecue");
                                    }
                                }
                            }.execute();

                        } else {
                            // Sign in failed, display a message and update the UI
                            Toast.makeText(getApplicationContext(),"signInWithCredential:failure",Toast.LENGTH_LONG).show();
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
}
