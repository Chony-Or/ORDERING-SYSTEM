package com.example.orderingsystem;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.getIntent;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ImageViewTargetFactory;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private static final String deleteHoldProduct_Url = "http://" + Constants.IP_ADDRESS + "/db_conn/deleteHoldproduct.php";
    private CartAdapter cartAdapter;
    ArrayList<CartData> cartDataList ;
    Context context;

    @SuppressLint("NotifyDataSetChanged")
    public CartAdapter(Context context, ArrayList<CartData> cartDataList) {
        this.cartDataList = cartDataList;
        this.context = context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView product_picture;
        private TextView productName;
        private TextView productPrice;
        private TextView quantity;
        private ImageButton delete_bt;
        private LinearLayout cartLinearLayout;

        public ViewHolder(@NonNull View view) {
            super(view);


            productName = view.findViewById(R.id.productName_tv);
            productPrice = view.findViewById(R.id.productPrice_tv);
            quantity = view.findViewById(R.id.quantity_tv);
            product_picture = view.findViewById(R.id.productImage);
            delete_bt = view.findViewById(R.id.delete_bt);
            cartLinearLayout = view.findViewById(R.id.cartLinearLayout);



        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_cart_item_list,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {

        CartData cartData = cartDataList.get(position);
        holder.productName.setText(cartData.getProduct_name());
        holder.productPrice.setText(String.valueOf(cartData.getAmount()));
        holder.quantity.setText(String.valueOf(cartData.getQuantity()));
        Glide.with(context).load(Constants.get_image + cartData.getProduct_picture()).into(holder.product_picture);

        Log.e("cart Product name", String.valueOf(cartData.getProduct_name()));
        Log.e("cart quantity", String.valueOf(cartData.getQuantity()));
        Log.e("cart amount", String.valueOf(cartData.getAmount()));
        Log.e("cart product_picture", String.valueOf(cartData.getProduct_picture()));


         Log.d("glidessssssss", String.valueOf(cartData.getProduct_picture()));


        holder.cartLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, cartData.getProduct_name(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,Productpage.class);

                intent.putExtra("productName",cartData.getProduct_name());
                intent.putExtra("productPrice",cartData.getAmount());
                intent.putExtra("quantity",cartData.getQuantity());
                intent.putExtra("productImage",cartData.getProduct_picture());
            }
        });

        holder.delete_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        String[] field = new String[1];
                        field[0] = "product_id"; // Fields in the database


                        String[] data = new String[1];
                        data[0] = String.valueOf(cartData.getProduct_id());


                        PutData putData = new PutData(deleteHoldProduct_Url, "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();

                                if (result.equals("Delete Success")) {

                                    Toast.makeText(context, "Delete Success", Toast.LENGTH_SHORT).show();
//                                    cartpage cart = new cartpage();
//                                    cart.reload();
                                    notifyDataSetChanged();
                                    Intent intent = new Intent(context,cartpage.class);
                                    context.startActivity(intent);

                                }

                                //End ProgressBar (Set visibility to GONE)
                                Log.i("PutData", result);
                            }
                        }
                    }
                });

            }
        });


    }

    @Override
    public int getItemCount()
    {
        return cartDataList.size();
    }
/////////////////////////
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}