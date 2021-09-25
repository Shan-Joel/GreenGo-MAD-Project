package com.example.greengomadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class card_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);


        final EditText card_number = findViewById(R.id.card_number);
        final EditText expiry_date = findViewById(R.id.expiry_date);
        final EditText cvv = findViewById(R.id.cvv);
        Button pay_now_btn = findViewById(R.id.pay_now_btn);
        DAOcard_info dao = new DAOcard_info();

        pay_now_btn.setOnClickListener(v -> {

            card_info cardInfo = new card_info(card_number.getText().toString(),expiry_date.getText().toString(),cvv.getText().toString());
            dao.add(cardInfo).addOnSuccessListener(suc->{
                Toast.makeText(card_details.this, "Details Received Successfully ", Toast.LENGTH_SHORT).show();
            })
                    .addOnFailureListener(er->{
                        Toast.makeText(card_details.this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();

            });

        });

    }
}
