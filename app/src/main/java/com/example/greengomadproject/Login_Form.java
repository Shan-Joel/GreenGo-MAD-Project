package com.example.greengomadproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Form extends AppCompatActivity implements View.OnClickListener{

    private TextView register, forgotpassword;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        register = (TextView) findViewById(R.id.newUserLink);
        register.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.loginBtn);
        signIn.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.loginUserEmail);
        editTextPassword = (EditText) findViewById(R.id.loginUserPassword);

        forgotpassword = (TextView) findViewById(R.id.loginForgotPassword);
        forgotpassword.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newUserLink:
                startActivity(new Intent(this, SignUp_Form.class));
                break;

            case R.id.loginBtn:
                userLogin();
                break;

            case R.id.loginForgotPassword:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()) {
            editTextEmail.setError("Email is Required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please Provide a Valid Email Address");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            editTextPassword.setError("Password is Required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6) {
            editTextPassword.setError("Min Password Length Should be 6 Characters");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified()) {
                        //redirect to user profile
                        startActivity(new Intent(Login_Form.this, ProfileActivity.class));
                    }else {
                        user.sendEmailVerification();
                        Toast.makeText(Login_Form.this, "Check Your Email to Verify Your Account", Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(Login_Form.this, "Failed to Login! Please Try Again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}