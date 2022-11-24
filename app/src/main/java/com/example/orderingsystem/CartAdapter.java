package com.example.orderingsystem;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    ArrayList<CartData> cartDataList ;
    Context cartpage;

    public CartAdapter(Context cartpage, ArrayList<CartData> cartDataList) {
        this.cartDataList = cartDataList;
        this.cartpage = cartpage;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView productImage;
        private TextView productName;
        private TextView productPrice;
        private LinearLayout cartLinearLayout;

        public ViewHolder(@NonNull View view) {
            super(view);

            productName = view.findViewById(R.id.productName);
            productPrice = view.findViewById(R.id.productPrice);
            productImage = view.findViewById(R.id.productImage);
            cartLinearLayout = view.findViewById(R.id.cartLinearLayout);



        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_cart_item_list, parent, false);
        // = new RecyclerView.ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {

        CartData cartDatas = cartDataList.get(position);
        holder.productName.setText(CartData.getCartProductName());
        holder.productPrice.setText(CartData.getCartProductPrice());
        holder.productImage.setImageResource(CartData.getCartProductImage());

      Glide.with(cartpage).load(Constants.get_image + cartDatas.getCartProductImage()).into(holder.productImage);
       Log.d("glide", String.valueOf(cartDatas.getCartProductImage()));


        holder.cartLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(cartpage, cartDatas.getCartProductName(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(cartpage,Productpage.class);

                intent.putExtra("productName",cartDatas.getCartProductName());
                intent.putExtra("productPrice",cartDatas.getCartProductPrice());
               // intent.putExtra("product_stock",cartDatas.getProduct_stock());
                intent.putExtra("productImage",cartDatas.getCartProductImage());
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return cartDataList.size();
    }


}