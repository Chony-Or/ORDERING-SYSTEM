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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder>{

    ArrayList<ProductData> productDatas;
    Context context;

    public ProductAdapter(Context context, ArrayList<ProductData> productDatas) {
        this.productDatas = productDatas;
        this.context = context;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView product_picture;
        private TextView product_name;
        private TextView product_price;
        private TextView product_stock;
        private LinearLayout product_container;

        public MyViewHolder(@NonNull View view) {
            super(view);

            product_name = view.findViewById(R.id.product_name);
            product_price = view.findViewById(R.id.product_price);
            product_stock = view.findViewById(R.id.product_stock);
            product_picture = view.findViewById(R.id.product_picture);
            product_container = view.findViewById(R.id.product_container);



        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_item_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ProductData productData = productDatas.get(position);

        holder.product_name.setText(productData.getProduct_name());
        holder.product_price.setText(productData.getProduct_price().toString());
        holder.product_stock.setText(productData.getProduct_stock().toString());
        Glide.with(context).load(Constants.get_image + productData.getProduct_picture()).into(holder.product_picture);
        Log.d("glide", String.valueOf(productData.getProduct_picture()));

         holder.product_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
 //               Toast.makeText(context,productData.getProduct_name(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,Productpage.class);

                intent.putExtra("product_id",productData.getProduct_id());
                intent.putExtra("product_name",productData.getProduct_name());
                intent.putExtra("product_price",productData.getProduct_price());
                intent.putExtra("product_stock",productData.getProduct_stock());
                intent.putExtra("product_details",productData.getProduct_details());
                Log.d("STOCKS", String.valueOf(productData.getProduct_stock()));
                intent.putExtra("product_picture",productData.getProduct_picture());
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount()
    {
            return productDatas.size();

    }
}
