package com.example.firebaseexample2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ForgetActivity extends AppCompatActivity {

    EditText resetEmail;
    Button reset;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        resetEmail = findViewById(R.id.editTextReset);
        reset = findViewById(R.id.buttonReset);
    }
}