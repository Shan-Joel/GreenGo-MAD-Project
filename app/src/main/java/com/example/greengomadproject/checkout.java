package com.example.greengomadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class checkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        final EditText enter_address = findViewById(R.id.enter_address);
        final EditText phone = findViewById(R.id.phone);
        Button pay_btn = findViewById(R.id.pay_btn);

        DAOcheckout_details dao = new DAOcheckout_details();

        pay_btn.setOnClickListener(v -> {

            checkout_details checkout_details = new checkout_details(enter_address.getText().toString(),phone.getText().toString());

            dao.add(checkout_details).addOnSuccessListener(suc->
            {
                Toast.makeText(checkout.this, "Details Received Successfully ", Toast.LENGTH_SHORT).show();
            })
                    .addOnFailureListener(er->{
                Toast.makeText(checkout.this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });

        });
    }
}