package com.example.greengomadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;

import java.util.concurrent.Callable;

public class feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        final EditText edit_name =findViewById(R.id.edit_name);
        final EditText edit_email =findViewById(R.id.edit_email);
        final EditText edit_feedback =findViewById(R.id.edit_feedback);
        Button btn =findViewById(R.id.btnsubmit);

        DAOCustomfeedback dao =new DAOCustomfeedback();
        btn.setOnClickListener(vr->
                {
                   Customfeedback cus = new Customfeedback(edit_name.getText().toString(),edit_email.getText().toString(),edit_feedback.getText().toString());
                    dao.add(cus).addOnSuccessListener(suc->
                    {
                        Toast.makeText(this, "Feedback inserted successfully", Toast.LENGTH_SHORT).show();
                    }).addOnFailureListener(er->
                    {
                        Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                    });

        Button btn_view = findViewById(R.id.btnview);
        btn_view.setOnClickListener(v->
                {
                    Intent intent = new Intent(feedback.this,viewfeedback.class);
                    startActivity(intent);
                }

        );



                }
                );



    }
}



/*
package com.example.greengomadproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Callable;

public class feedback extends AppCompatActivity {

    EditText name, email, feedback;
    Button viewbtn;
    DatabaseReference myreff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        name = findViewById(R.id.edit_name);
        email = findViewById(R.id.edit_email);
        feedback = findViewById(R.id.edit_feedback);

        viewbtn = findViewById(R.id.btnview);

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myreff = FirebaseDatabase.getInstance().getReference().child("MkUoZ25eCnZ14Jj6R6-");
                myreff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String nameT = snapshot.child("name").getValue().toString();
                        String emailT = snapshot.child("email").getValue().toString();
                        String feedbackT = snapshot.child("feedback").getValue().toString();

                        name.setText(nameT);
                        email.setText(emailT);
                        feedback.setText(feedbackT);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }
}

 */