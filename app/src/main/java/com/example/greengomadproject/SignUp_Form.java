package com.example.greengomadproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp_Form extends AppCompatActivity {

    private TextInputEditText userNameEdt, pwdEdt, emailEdt;
    private Button registerBtn;
    private TextView loginform;
    private FirebaseAuth mAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);

        userNameEdt = findViewById(R.id.editTextTextPersonName);
        pwdEdt = findViewById(R.id.editTextTextPersonPassword);
        emailEdt = findViewById(R.id.editTextTextPersonEmail);
        registerBtn = findViewById(R.id.registerbtn);

        loginform = findViewById(R.id.alreadyHaveAccount);
        loginform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp_Form.this, Login_Form.class);
                startActivity(i);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userNameEdt.getText().toString();
                String pwd = pwdEdt.getText().toString();
                String email = emailEdt.getText().toString();

                if(TextUtils.isEmpty(userName) && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(email)) {
                    Toast.makeText(SignUp_Form.this, "All Fields are Required!", Toast.LENGTH_LONG);
                }else {
                    mAuth.createUserWithEmailAndPassword(userName, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(SignUp_Form.this, "User Registered Successfull", Toast.LENGTH_LONG);
                                Intent i = new Intent(SignUp_Form.this, Login_Form.class);
                                startActivity(i);
                                finish();
                            }else {
                                Toast.makeText(SignUp_Form.this, "Fail to Register This User", Toast.LENGTH_LONG);
                            }
                        }
                    });
                }
            }
        });


    }
}

