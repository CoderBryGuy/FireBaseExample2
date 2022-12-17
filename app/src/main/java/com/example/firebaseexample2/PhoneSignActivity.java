package com.example.firebaseexample2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneSignActivity extends AppCompatActivity {

    EditText phoneNumber;
    Button sendCode;
    EditText smsCode;
    Button signWithPhone;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_sign);

        phoneNumber = findViewById(R.id.phone_txtvw_phone_act);
        sendCode = findViewById(R.id.btn_sendSMS_phone_act);
        smsCode = findViewById(R.id.smsEdittxt_phone_act);
        signWithPhone = findViewById(R.id.btn_sign_phone_act);

        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userPhoneNumber = phoneNumber.getText().toString();
                PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(userPhoneNumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(PhoneSignActivity.this)
                        .setCallbacks(mCallbacks)
                        .build();

                PhoneAuthProvider.verifyPhoneNumber(options);
            }
        });

        signWithPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}