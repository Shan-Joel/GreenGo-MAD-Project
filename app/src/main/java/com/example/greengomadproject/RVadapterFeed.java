package com.example.greengomadproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVadapterFeed extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Context context;
    ArrayList<Customfeedback> list = new ArrayList<>();

    public RVadapterFeed(Context ctx) {
        this.context = ctx;
    }
    public void setItems(ArrayList<Customfeedback> cus)
    {
        list.addAll(cus);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_feedbackitems,parent,false);
        return new CustomfeedbackVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    CustomfeedbackVH vh = (CustomfeedbackVH) holder;
    Customfeedback cus = list.get(position);
    vh.text_name.setText(cus.getName());
    vh.text_email.setText(cus.getEmail());
    vh.text_feedback.setText(cus.getFeedback());
    }

    @Override
    public int getItemCount() {

        return list.size();
    }
}
