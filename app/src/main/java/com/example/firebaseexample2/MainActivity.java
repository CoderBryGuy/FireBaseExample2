package com.example.firebaseexample2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class MainActivity extends AppCompatActivity {

    EditText mail, password;
    Button signIn, signUp, forgotPassword;
    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mail = findViewById(R.id.editTextEmail_signin);
        password = findViewById(R.id.editTextPassword_signin);
        signIn = findViewById(R.id.button_signin);
        signUp = findViewById(R.id.button_signup_signin);
        forgotPassword = findViewById(R.id.forgotPassword);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userMail = mail.getText().toString();
                String userPassword = password.getText().toString();

                signInFirebase(userMail, userPassword);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ForgetActivity.class);
                startActivity(intent);
            }
        });


    }

    public void signInFirebase(String userMail, String userPassword) {
        mAuth.signInWithEmailAndPassword(userMail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent = new Intent(MainActivity.this, MainMenu.class);
                            startActivity(intent);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Mail or Password is not correct.",
                                    Toast.LENGTH_SHORT).show();



                        }
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            Intent intent = new Intent(MainActivity.this, MainMenu.class);
            startActivity(intent);
            finish();
        }
    }
}