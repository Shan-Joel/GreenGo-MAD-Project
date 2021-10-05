package com.example.greengomadproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.greengomadproject.adapter.MyFoodAdapter;
import com.example.greengomadproject.eventbus.MyUpdateCartEvent;
import com.example.greengomadproject.listener.ICartLoadListener;
import com.example.greengomadproject.listener.IFoodLoadListener;
import com.example.greengomadproject.model.CartModel;
import com.example.greengomadproject.model.FoodModel;
import com.example.greengomadproject.utils.SpaceItemDecoration;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class foodCart extends AppCompatActivity implements IFoodLoadListener, ICartLoadListener {
    @BindView(R.id.recycler_food)
    RecyclerView recyclerFood;
    @BindView(R.id.mainLayout)
    RelativeLayout mainLayout;
    @BindView(R.id.btnCart)
    FrameLayout btnCart;

    IFoodLoadListener foodLoadListener;
    ICartLoadListener cartLoadListener;
    //private Notification.Builder badge;

    /*@Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


     @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onUpdateCart(MyUpdateCartEvent event)
    {
        countCartItem();
    } */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_cart);

        init();
        loadFoodFromFirebase();
        countCartItem();
    }

    private void loadFoodFromFirebase() {
        List<FoodModel> foodModels=new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Drink")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            for(DataSnapshot foodSnapshot:snapshot.getChildren())
                            {
                                FoodModel foodModel=foodSnapshot.getValue(FoodModel.class);
                                foodModel.setKey(foodSnapshot.getKey());
                                foodModels.add(foodModel);
                            }
                            foodLoadListener.onFoodLoadSuccess(foodModels);
                        }
                        else
                            foodLoadListener.onFoodLoadFailed("Can't find food");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
foodLoadListener.onFoodLoadFailed(error.getMessage());
                    }
                });
    }

    private void init(){
        ButterKnife.bind(this);

        foodLoadListener = this;
        cartLoadListener = this;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerFood.setLayoutManager(gridLayoutManager);
        recyclerFood.addItemDecoration(new SpaceItemDecoration());
    }

    @Override
    public void onFoodLoadSuccess(List<FoodModel> foodModelList) {
        MyFoodAdapter adapter =new MyFoodAdapter(this,foodModelList);
        recyclerFood.setAdapter(adapter);

    }

    @Override
    public void onFoodLoadFailed(String message) {
        Snackbar.make(mainLayout,message,Snackbar.LENGTH_LONG).show();

    }

    @Override
    public void onCartLoadSuccess(List<CartModel> cartModelList) {

        int cartSum =0;
        for(CartModel cartModel: cartModelList)
            cartSum += cartModel.getQuantity();
        //badge.setNumber(cartSum);
    }

    @Override
    public void onCartLoadFailed(String message) {
        Snackbar.make(mainLayout,message,Snackbar.LENGTH_LONG).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        countCartItem();
    }

    private void countCartItem() {
        List<CartModel> cartModels = new ArrayList<>();
        FirebaseDatabase
                .getInstance().getReference("Cart")
                .child("UNIQUE_USER_ID")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot cartsnaphot:snapshot.getChildren())
                        {
                            CartModel cartModel=cartsnaphot.getValue(CartModel.class);
                            cartModel.setKey(cartsnaphot.getKey());
                            cartModels.add(cartModel);
                        }
                        cartLoadListener.onCartLoadSuccess(cartModels);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        cartLoadListener.onCartLoadFailed(error.getMessage());

                    }
                });
    }
}
