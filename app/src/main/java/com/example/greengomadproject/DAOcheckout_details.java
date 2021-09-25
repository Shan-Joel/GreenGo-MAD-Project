package com.example.greengomadproject;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOcheckout_details {

    private DatabaseReference databaseReference;
    public DAOcheckout_details()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(checkout_details.class.getSimpleName());
    }
    public Task<Void> add(checkout_details checkout_details){
        return databaseReference.push().setValue(checkout_details);
    }
}
