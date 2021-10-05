package com.example.greengomadproject;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOCustomfeedback {

    private DatabaseReference databaseReference;
    public DAOCustomfeedback()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Customfeedback.class.getSimpleName());
    }
    public Task<Void> add(Customfeedback cusfeed)
    {
    return databaseReference.push().setValue(cusfeed);
    }
    /*public Task<Void> update(String key, HashMap<String ,Object> hashMap)
    {
        return databaseReference.child(key).updateChildren(hashMap);
    }*/


    public Query get()
    {
       return databaseReference.orderByKey();
    }




}
