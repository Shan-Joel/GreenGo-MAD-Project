package com.example.greengomadproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    private Button logout;

    String name;
    String email;
    String password;

    TextView greetingTextView;
    TextView emailTextView;
    TextView nameTextView;
    TextView passwordTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        logout = (Button) findViewById(R.id.logoutBtn);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, Login_Form.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        greetingTextView = (TextView) findViewById(R.id.welcomeTxt);
        emailTextView = (TextView) findViewById(R.id.userEmailRetrieve);
        nameTextView = (TextView) findViewById(R.id.userNameRetrieve);
        passwordTextView = (TextView) findViewById(R.id.userPasswordRetrieve);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null) {
                    name = userProfile.name;
                    email = userProfile.email;
                    password = userProfile.password;

                    greetingTextView.setText("Welcome, " + name + "!");
                    emailTextView.setText(email);
                    nameTextView.setText(name);
                    passwordTextView.setText(password);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Something Wrong Happened!", Toast.LENGTH_LONG).show();
            }

        });
    }
            public void update(View view) {
                    if(isNameChanged() || isPasswordChanged() || isEmailChanged()) {
                        Toast.makeText(ProfileActivity.this, "Data has been updated", Toast.LENGTH_LONG).show();
                    }
            }

            private boolean isNameChanged() {
            if(!name.equals(nameTextView.getText().toString())) {
                reference.child(name).child("name").setValue(nameTextView.getText().toString());
                return true;
            }

            else {
                return false;
            }
        }

            private boolean isPasswordChanged() {
                if(!password.equals(nameTextView.getText().toString())) {
                    reference.child(password).child("password").setValue(passwordTextView.getText().toString());
                    return true;
                }

                else {
                    return false;
                }
            }

            private boolean isEmailChanged() {
                if(!email.equals(nameTextView.getText().toString())) {
                    reference.child(email).child("password").setValue(emailTextView.getText().toString());
                    return true;
                }

                else {
                    return false;
                }
            }
}






