package com.example.greengomadproject.listener;

import android.view.View;

public interface IRecyclerViewClickListener {
    default void onRecyclerClick(View view, int position) {

    }
}
