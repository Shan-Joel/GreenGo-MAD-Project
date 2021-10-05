package com.example.greengomadproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.greengomadproject.R;
import com.example.greengomadproject.listener.ICartLoadListener;
import com.example.greengomadproject.listener.IRecyclerViewClickListener;
import com.example.greengomadproject.model.CartModel;
import com.example.greengomadproject.model.FoodModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyFoodAdapter extends RecyclerView.Adapter<MyFoodAdapter.MyFoodHolder> {

    private Context context;
    private List<FoodModel> foodModelList;


    public MyFoodAdapter(Context context, List<FoodModel> foodModelList) {
        this.context = context;
        this.foodModelList = foodModelList;

    }

    @NonNull
    @Override
    public MyFoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyFoodHolder(LayoutInflater.from(context)
                .inflate(R.layout.layout_food_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyFoodHolder holder, int position) {
        Glide.with(context)
                .load(foodModelList.get(position).getImage())
                .into(holder.imageView8);
        holder.aplPrice.setText(new StringBuilder("Rs").append(foodModelList.get(position).getPrice()));
        holder.txtName.setText(new StringBuilder().append(foodModelList.get(position).getName()));


    }

    /*private void addToCart(FoodModel foodModel) {
        DatabaseReference userCart = FirebaseDatabase
                .getInstance()
                .getReference("Cart")
                .child("UNIQUE_USER_ID");

        userCart.child(foodModel.getKey())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            CartModel cartModel =snapshot.getValue(CartModel.class);
                            cartModel.setQuantity(cartModel.getQuantity()+1);
                            Map<String,Object> updateData = new HashMap<>();
                            updateData.put("quantity",cartModel.getQuantity());
                            updateData.put("totalPrice",cartModel.getQuantity()*Float.parseFloat(cartModel.getPrice()));

                            userCart.child(foodModel.getKey())
                                    .updateChildren(updateData)
                                    .addOnSuccessListener(unused -> {
                                        iCartLoadListener.onCartLoadFailed("Add to Cart Success");
                                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    iCartLoadListener.onCartLoadFailed(e.getMessage());
                                }
                            });

                        }
                        else
                        {
                            CartModel cartModel=new CartModel();
                            cartModel.setName(foodModel.getName());
                            cartModel.setImage(foodModel.getImage());
                            cartModel.setKey(foodModel.getKey());
                            cartModel.setPrice(foodModel.getPrice());
                            cartModel.setQuantity(1);
                            cartModel.setTotalPrice(Float.parseFloat(foodModel.getPrice()));


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        iCartLoadListener.onCartLoadFailed(error.getMessage());
                    }
                });
    } */

    @Override
    public int getItemCount() {
        return foodModelList.size();
    }

    public class MyFoodHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView8)
        ImageView imageView8;
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.aplPrice)
        TextView aplPrice;


        private Unbinder unbinder;
        public MyFoodHolder(@NonNull View itemView) {
            super(itemView);
            Unbinder unbinder = ButterKnife.bind(this, itemView);
        }

    }
}
