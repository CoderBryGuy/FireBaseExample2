package com.example.firebaseexample2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    EditText mail, password;
    Button enter;

    private static final String TAG = "SignUp";

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mail = findViewById(R.id.editTextEmail_Signup);
        password = findViewById(R.id.editTextPassword_Signup);
        enter = findViewById(R.id.button_Signup);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String userEmail = mail.getText().toString().trim();
                    String userPassword = password.getText().toString();

                    signUpFirebase(userEmail, userPassword);

            }
        });
    }

    public void signUpFirebase(String userEmail, String userPassword){

//                mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCustomToken:success");
                            Toast.makeText(SignUp.this, "Your Account Has Been Created Successfully",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCustomToken:failure", task.getException());
                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}