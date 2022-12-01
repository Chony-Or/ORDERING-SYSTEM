package com.example.orderingsystem;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class cartpage extends AppCompatActivity {

    TextView profile_tv,home_tv;
    public RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    public ArrayList<CartData> cartListProducts = new ArrayList<>();
    private static final String getCustomer_Order = "http:/" + Constants.IP_ADDRESS + "/test_conn/getholdOrders.php";
    private final String BASE_URL ="http:/"+Constants.IP_ADDRESS+"/test_conn/getProducts.php";
    // Database version
    private static final int DATABASE_VERSION = 1;
    // Database name
    FloatingActionButton floatingButton;
    TextView total_tv;
    Button checkbox_btn;
    CheckBox selectAll_cb;
    String product_name;



    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartpage);

        SQLiteDatabase db = openOrCreateDatabase("Hold_Order",MODE_PRIVATE,null);
        init();


        recyclerView = findViewById(R.id.cartProductsRView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

//        Cursor cursor = db.rawQuery("select * from ProductOrder_tbl",null);
//
//
//        if (cursor.moveToFirst()) {
//            while (!cursor.isAfterLast()) {
//
//                Integer product_id;
//                Integer customer_id;
//                String product_name;
//                Integer quantity;
//                Double amount;
//                String product_picture;
//
//                product_id = cursor.getInt(cursor.getColumnIndex("product_id"));
//                customer_id = cursor.getInt(cursor.getColumnIndex("customer_id"));
//                product_name = cursor.getString(cursor.getColumnIndex("product_name"));
//                quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
//                amount = cursor.getDouble(cursor.getColumnIndex("amount"));
//                product_picture = cursor.getString(cursor.getColumnIndex("product_picture"));
//
//                CartData cartData = new CartData(product_id, customer_id, product_name, quantity, amount, product_picture);
//                cartListProducts.add(cartData);
//
//                cursor.moveToNext();
//            }
//            cartAdapter = new CartAdapter(cartpage.this, cartListProducts);
//            recyclerView.setAdapter(cartAdapter);
//        }


        String[] field = new String[1];
        field[0] = "customer_id"; // Fields in the database
        String[] data = new String[1];
        data[0] = String.valueOf(UserData.getCustomer_id());

        PutData putData = new PutData(getCustomer_Order, "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                String result = putData.getResult();
                Log.e("php",result);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

                try {
                    JSONArray array = new JSONArray(result);

                for (int i = 0; i < array.length(); i++) {
                    Log.d("ORDERS", "Get USER ORDEr");

                    JSONObject object = array.getJSONObject(i);
                    Integer product_id = object.getInt("product_id");
                    Integer customer_id = object.getInt("customer_id");
                    String product_name = object.getString("product_name");
                    Integer quantity = object.getInt("quantity");
                    Double amount = object.getDouble("amount");
                    String product_picture = object.getString("product_picture");

                    CartData cartData = new CartData(product_id,customer_id, product_name, quantity, amount, product_picture);
                    cartListProducts.add(cartData);
                }
                    cartAdapter = new CartAdapter(cartpage.this, cartListProducts);
                    recyclerView.setAdapter(cartAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }



        Log.d("cart list", " " + cartListProducts.size());



        profile_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cartpage.this, Profile.class);
                startActivity(intent);
            }
        });


        home_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cartpage.this, Homepage.class);
                startActivity(intent);
            }
        });

        checkbox_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cartpage.this, Receiptpage.class);
                startActivity(intent);
            }
        });




    }
    public void init() {

        profile_tv = (TextView) findViewById(R.id.profile_tv);
        home_tv = (TextView) findViewById(R.id.home_tv);
        total_tv = findViewById(R.id.total_tv);
        checkbox_btn = findViewById(R.id.checkout_btn);
        selectAll_cb = findViewById(R.id.selectAll_cb);

    }
    public void reload()
    {
        finish();
        startActivity(getIntent());
        cartAdapter.notifyDataSetChanged();

    }



}