package com.example.greengomadproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignUp_Form extends AppCompatActivity implements View.OnClickListener {

    private TextView registerBtn, register;
    private EditText userName, userEmail, userPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);

        mAuth = FirebaseAuth.getInstance();

        register = (TextView) findViewById(R.id.alreadyHaveAccount);
        register.setOnClickListener(this);

        registerBtn = (Button) findViewById(R.id.registerbtn);
        registerBtn.setOnClickListener(this);

        userName = (EditText) findViewById(R.id.editTextTextPersonName);
        userEmail = (EditText) findViewById(R.id.editTextTextPersonEmail);
        userPassword = (EditText) findViewById(R.id.editTextTextPersonPassword);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerbtn:
                registerBtn();
                break;

            case R.id.alreadyHaveAccount:
                startActivity(new Intent(this, Login_Form.class));
                break;
        }
    }

    private void registerBtn() {
        String name = userName.getText().toString().trim();
        String email = userEmail.getText().toString().trim();
        String password = userPassword.getText().toString().trim();

        if(name.isEmpty()) {
            userName.setError("Name is Required");
            userName.requestFocus();
            return;
        }

        if(email.isEmpty()) {
            userEmail.setError("Email is Required");
            userEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userEmail.setError("Please Provide a Valid Email Address");
            userEmail.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            userPassword.setError("Password is Required");
            userPassword.requestFocus();
            return;
        }

        if(password.length() < 6) {
            userPassword.setError("Min Password Length Should be 6 Characters");
            userPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            User user = new User(name, email, password);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(SignUp_Form.this, "User Has Been Registered", Toast.LENGTH_LONG).show();
                                    }else {
                                        Toast.makeText(SignUp_Form.this, "Failed to Register, Try Again", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }else {
                            Toast.makeText(SignUp_Form.this, "Failed to Register, Try Again", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}

