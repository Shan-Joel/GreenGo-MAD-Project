package com.example.greengomadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addFood extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        final EditText edit_image = findViewById(R.id.edit_image);
        final EditText edit_name = findViewById(R.id.edit_name);
        final EditText edit_price = findViewById(R.id.edit_price);

        Button btn = findViewById(R.id.btn_add);
        DAOEmployee dao =new DAOEmployee();
        btn.setOnClickListener(view ->
        {
            Employee emp = new Employee(edit_image.getText().toString(),edit_name.getText().toString(),edit_price.getText().toString());
            dao.add(emp).addOnSuccessListener(suc ->
            {
                Toast.makeText(this,"Record is inserted", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->
            {
                Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();
            });

        });
    }

    public void onclick(View view) {
        Intent i=new Intent(addFood.this,foodCart.class);
        startActivity(i);
    }
}