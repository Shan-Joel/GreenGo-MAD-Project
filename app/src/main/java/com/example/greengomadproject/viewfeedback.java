package com.example.greengomadproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewfeedback extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    RVadapterFeed adapter;
    DAOCustomfeedback dao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfeedback);

        swipeRefreshLayout = findViewById(R.id.swip);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        adapter = new RVadapterFeed(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOCustomfeedback();
        
        loadData();
    }

    private void loadData() {

        dao.get().addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<Customfeedback> cusf = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren())
                {
                    Customfeedback cus = data.getValue(Customfeedback.class);
                            cusf.add(cus);
                }

                adapter.setItems(cusf);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}