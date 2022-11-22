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

public class Recyclerpage extends AppCompatActivity {

    TextView profile_tv,home_tv;
    public RecyclerView recyclerView;
    private ProductAdapter mAdapter;
    //public RecyclerView.LayoutManager layoutManager;
    public ArrayList<ProductData> listproducts = new ArrayList<>();
    private static final String BASE_URL ="http:/"+Constants.IP_ADDRESS+"/test_conn/getProducts.php";
    FloatingActionButton floatingButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerpage);



        init();
        //        getProducts();

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        mAdapter = new ProductAdapter(this,listproducts);
        recyclerView.setAdapter(mAdapter);


        //recyclerView.setAdapter(mAdapter);




        profile_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Recyclerpage.this ,Profile.class);
                startActivity(intent);
            }
        });
        home_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Recyclerpage.this ,Homepage.class);
                startActivity(intent);
            }
        });

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Recyclerpage.this, cartpage.class);
                startActivity(intent);
            }
        });

        // pass data
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d("satryto", "TESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSST");
                        try {

                            Log.d("satryto", "ayoown");
                            Log.i("tagconvertstr", "["+response+"]");

                            JSONArray array = new JSONArray(response);

                            for(int i = 0; i<array.length();i++)
                            {
                                Log.d("tag", "PUMASOK NAAA");
                                JSONObject object = array.getJSONObject(i);
                                Integer product_id = object.getInt("product_id");
                                String product_name = object.getString("product_name");
                                Double product_price = object.getDouble("product_price");
                                Integer product_stock = object.getInt("product_stock");
                                String product_code = object.getString("product_code");
                                String product_picture = object.getString("product_picture");



                                ProductData product = new ProductData(product_id,product_name,product_price,product_stock,product_code,product_picture);
                                listproducts.add(product);
                            }

                            Log.d("te"," " + listproducts.size());
                            mAdapter = new ProductAdapter(Recyclerpage.this,listproducts);
                            recyclerView.setAdapter(mAdapter);

                        }catch(Exception e)
                        {
                            Log.d("test", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Recyclerpage.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }

    public void init()
    {

        profile_tv = (TextView) findViewById(R.id.profile_tv);
        home_tv = (TextView) findViewById(R.id.home_tv);
        floatingButton = (FloatingActionButton) findViewById(R.id.floatingButton);
    }
}