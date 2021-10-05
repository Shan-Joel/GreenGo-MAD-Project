package com.example.greengomadproject.listener;

import com.example.greengomadproject.model.CartModel;
import com.example.greengomadproject.model.FoodModel;

import java.util.List;

public interface ICartLoadListener {
    void onCartLoadSuccess(List<CartModel> cartModelList);
    void onCartLoadFailed(String message);
}
