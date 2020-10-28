package com.cedric.goalfitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText username,emailAddress,phone,password;
    Button btnRegister;
    TextView txtViewLogin;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.txtUsername);
        emailAddress = findViewById(R.id.txtEmail);
        phone = findViewById(R.id.txtPhoneNumber);
        password = findViewById(R.id.txtPassword);
        btnRegister = findViewById(R.id.btnRegister);
        txtViewLogin = findViewById(R.id.txtViewLogin);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if (fAuth.getCurrentUser() != null){
            fAuth.signOut();
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailAddress.getText().toString().trim();
                String userPassword = password.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    emailAddress.setError("Email address is required !");
                    return;
                }
                if (TextUtils.isEmpty(userPassword)){
                    password.setError("Password is required !");
                }
                if (userPassword.length() < 8){
                    password.setError("Password must have a minimum of 8 characters");
                }

                progressBar.setVisibility(View.VISIBLE);
                // Registering the user in firebase

                fAuth.createUserWithEmailAndPassword(email,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Register.this, "User Successfully Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                            Toast.makeText(Register.this, "An Error as Occurred"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
         // Text view takes the user to the login activity
        txtViewLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

    }
}