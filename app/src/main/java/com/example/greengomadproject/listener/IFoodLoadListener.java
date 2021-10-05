package com.example.greengomadproject.listener;

import com.example.greengomadproject.model.FoodModel;

import java.util.List;

public interface IFoodLoadListener {
    void onFoodLoadSuccess(List<FoodModel> foodModelList);
    void onFoodLoadFailed(String message);
}
