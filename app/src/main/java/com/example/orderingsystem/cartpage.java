package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class cartpage extends AppCompatActivity {

    TextView profile_tv,home_tv;
    public RecyclerView cartProductsView;
    private CartAdapter cartAdapter;
    //public RecyclerView.LayoutManager layoutManager;
    public ArrayList<CartData> cartListProducts = new ArrayList<>();
    private static final String BASE_URL ="http:/192.168.8.100/test_conn/getProducts.php";
    FloatingActionButton floatingButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartpage);


        init();

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

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d("satryto", "TESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSST");
                        try {

                            Log.d("satryto", "ayoown");
                            Log.i("tagconvertstr", "[" + response + "]");

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                Log.d("tag", "PUMASOK NAAA");
                                JSONObject object = array.getJSONObject(i);
                                Integer product_id = object.getInt("product_id");
                                String product_name = object.getString("product_name");
                                Double product_price = object.getDouble("product_price");
                                Integer product_stock = object.getInt("product_stock");
                                String product_code = object.getString("product_code");
                                String product_picture = object.getString("product_picture");


                                CartData product = new CartData(product_id, product_name, product_price, product_stock, product_code, product_picture);
                                cartListProducts.add(product);

                            }
                            Log.d("te", " " + cartListProducts.size());
//                            recyclerView = findViewById(R.id.recyclerView);
//                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Recyclerpage.this);
//                            recyclerView.setLayoutManager(linearLayoutManager);
//                            recyclerView.setHasFixedSize(true);

                            cartAdapter = new CartAdapter(cartpage.this, cartListProducts);
                            cartProductsView.setAdapter(cartAdapter);

                        } catch (Exception e) {
                            Log.d("test", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(cartpage.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }
    public void init() {

        profile_tv = (TextView) findViewById(R.id.profile_tv);
        home_tv = (TextView) findViewById(R.id.home_tv);

        cartProductsView = findViewById(R.id.cartProductsView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        cartProductsView.setLayoutManager(linearLayoutManager);
        cartProductsView.setHasFixedSize(true);
        CartAdapter cAdapter = new CartAdapter(this, cartListProducts);
        cartProductsView.setAdapter(cAdapter);

    }
}