package com.example.greengomadproject;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOcard_info {

    private DatabaseReference databaseReference;
    public DAOcard_info()

    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(card_info.class.getSimpleName());

    }

    public Task<Void> add(card_info card_info){
        return databaseReference.push().setValue(card_info);
    }


}
