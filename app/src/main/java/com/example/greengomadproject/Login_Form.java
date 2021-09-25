package com.example.greengomadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Form extends AppCompatActivity {

    private TextInputEditText userNameEdt, pwdEdt;
    private Button loginBtn;
    private TextView newUserLink;
    private FirebaseAuth mAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        userNameEdt = findViewById(R.id.editTextTextPersonName);
        pwdEdt = findViewById(R.id.editTextTextPersonPassword);
        loginBtn = findViewById(R.id.loginBtn);
        newUserLink = findViewById(R.id.newUserLink);

        mAuth = FirebaseAuth.getInstance();

        newUserLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login_Form.this, SignUp_Form.class);
                startActivity(i);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userNameEdt.getText().toString();
                String pwd = pwdEdt.getText().toString();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null) {
            Intent i = new Intent(Login_Form.this, SignUp_Form.class);
            startActivity(i);
        }
    }
}