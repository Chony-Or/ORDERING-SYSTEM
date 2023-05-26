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

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.MyViewHolder>{

    ArrayList<ReceiptData> receiptDatas;
    Context context;

    public ReceiptAdapter(Context context, ArrayList<ReceiptData> receiptDatas) {
        this.receiptDatas = receiptDatas;
        this.context = context;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView productName_tv;
        private TextView quantity_tv;
        private TextView productPrice_tv;
        private TextView productsubtotal_tv;

        public MyViewHolder(@NonNull View view) {
            super(view);

            productName_tv = view.findViewById(R.id.productName_tv);
            quantity_tv= view.findViewById(R.id.quantity_tv);
            productPrice_tv = view.findViewById(R.id.productPrice_tv);
            productsubtotal_tv = view.findViewById(R.id.productsubtotal_tv);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_receipt_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ReceiptData receiptData = receiptDatas.get(position);

        double price = receiptData.getAmount();
        double quantity = receiptData.getQuantity();
        double subtotal = price * quantity;

        holder.productName_tv.setText(receiptData.getProduct_name());
        holder.quantity_tv.setText(receiptData.getQuantity().toString());
        holder.productPrice_tv.setText(receiptData.getAmount().toString());
        holder.productsubtotal_tv.setText(String.valueOf(subtotal));

        Intent intent = new Intent(context,Receiptpage.class);
        intent.putExtra("ordernumber",receiptData.getOrder_number());

    }
    @Override
    public int getItemCount()
    {
        return receiptDatas.size();

    }

}

