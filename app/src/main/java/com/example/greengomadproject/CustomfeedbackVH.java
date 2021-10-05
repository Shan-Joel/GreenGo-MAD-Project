package com.example.greengomadproject;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomfeedbackVH extends RecyclerView.ViewHolder {

    public TextView text_name,text_email,text_feedback;
    public CustomfeedbackVH(@NonNull View itemView) {
        super(itemView);

        text_name = itemView.findViewById(R.id.text_name);
        text_email = itemView.findViewById(R.id.text_email);
        text_feedback = itemView.findViewById(R.id.text_feedback);
    }
}
