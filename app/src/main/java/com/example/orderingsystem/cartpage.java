package com.example.orderingsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class cartpage extends AppCompatActivity {

    TextView profile_tv,home_tv;
    public RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    public ArrayList<CartData> cartListProducts = new ArrayList<>();
    private final String BASE_URL ="http:/"+Constants.IP_ADDRESS+"/test_conn/getProducts.php";
    FloatingActionButton floatingButton;
    TextView total_tv;
    Button checkbox_btn;
    CheckBox selectAll_cb;



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

        Cursor cursor = db.rawQuery("select * from ProductOrder_tbl",null);


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                Integer product_id;
                Integer customer_id;
                String product_name;
                Integer quantity;
                Double amount;
                String product_picture;

                product_id = cursor.getInt(cursor.getColumnIndex("product_id"));
                customer_id = cursor.getInt(cursor.getColumnIndex("customer_id"));
                product_name = cursor.getString(cursor.getColumnIndex("product_name"));
                quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
                amount = cursor.getDouble(cursor.getColumnIndex("amount"));
                product_picture = cursor.getString(cursor.getColumnIndex("product_picture"));

                CartData cartData = new CartData(product_id, customer_id, product_name, quantity, amount, product_picture);
                cartListProducts.add(cartData);

                cursor.moveToNext();
            }
            cartAdapter = new CartAdapter(cartpage.this, cartListProducts);
            recyclerView.setAdapter(cartAdapter);
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
}