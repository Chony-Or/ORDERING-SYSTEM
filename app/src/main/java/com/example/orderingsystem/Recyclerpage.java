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
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Recyclerpage extends AppCompatActivity {

    TextView profile_tv,home_tv;
    public RecyclerView recyclerView;
    private ProductAdapter mAdapter;
    public ArrayList<ProductData> listproducts = new ArrayList<>();
    private final String BASE_URL ="http:/"+Constants.IP_ADDRESS+"/test_conn/getProducts.php";
    private final String getSpecificDrinks_URL ="http:/"+Constants.IP_ADDRESS+"/test_conn/getSpecificDrinks.php";

    private Integer data;
    FloatingActionButton floatingButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the upper part
        setContentView(R.layout.activity_recyclerpage);


        Intent intent = getIntent();

        data = intent.getIntExtra("data",0);

        init();

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        mAdapter = new ProductAdapter(this,listproducts);
        recyclerView.setAdapter(mAdapter);



        profile_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(UserData.getCust_id()==null)
                {
                    Toast.makeText(getApplicationContext(),"Please login your Account first",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Recyclerpage.this ,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Recyclerpage.this ,Profile.class);
                    startActivity(intent);
                }

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

        if (data == 5) // See all products
        {
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
                                String product_details = object.getString("product_details");
                                String product_code = object.getString("product_code");
                                String product_picture = object.getString("product_picture");



                                ProductData product = new ProductData(product_id,product_name,product_price,product_stock,product_details,product_code,product_picture);
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
        else if (data == 1)
        {
            String[] field = new String[1];
            field[0] = "productClass_id"; // Fields in the database
            String[] data = new String[1];
            data[0] = String.valueOf(1);

            PutData putData = new PutData(getSpecificDrinks_URL, "POST", field, data);
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    Log.e("php",result);
 //                   Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

                    try {
                        JSONArray array = new JSONArray(result);

                        for(int i = 0; i<array.length();i++)
                        {
                            Log.d("tag", "PUMASOK NAAA");
                            JSONObject object = array.getJSONObject(i);
                            Integer product_id = object.getInt("product_id");
                            String product_name = object.getString("product_name");
                            Double product_price = object.getDouble("product_price");
                            Integer product_stock = object.getInt("product_stock");
                            String product_details = object.getString("product_details");
                            String product_code = object.getString("product_code");
                            String product_picture = object.getString("product_picture");



                            ProductData product = new ProductData(product_id,product_name,product_price,product_stock,product_details,product_code,product_picture);
                            listproducts.add(product);
                        }

                        Log.d("te"," " + listproducts.size());
                        mAdapter = new ProductAdapter(Recyclerpage.this,listproducts);
                        recyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                }
            }
        }
        else if (data == 2)
        {
            String[] field = new String[1];
            field[0] = "productClass_id"; // Fields in the database
            String[] data = new String[1];
            data[0] = String.valueOf(2);

            PutData putData = new PutData(getSpecificDrinks_URL, "POST", field, data);
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    Log.e("php",result);
    //                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

                    try {
                        JSONArray array = new JSONArray(result);

                        for(int i = 0; i<array.length();i++)
                        {
                            Log.d("tag", "PUMASOK NAAA");
                            JSONObject object = array.getJSONObject(i);
                            Integer product_id = object.getInt("product_id");
                            String product_name = object.getString("product_name");
                            Double product_price = object.getDouble("product_price");
                            Integer product_stock = object.getInt("product_stock");
                            String product_details = object.getString("product_details");
                            String product_code = object.getString("product_code");
                            String product_picture = object.getString("product_picture");



                            ProductData product = new ProductData(product_id,product_name,product_price,product_stock,product_details,product_code,product_picture);
                            listproducts.add(product);
                        }

                        Log.d("te"," " + listproducts.size());
                        mAdapter = new ProductAdapter(Recyclerpage.this,listproducts);
                        recyclerView.setAdapter(mAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        else if (data == 3)
        {
            String[] field = new String[1];
            field[0] = "productClass_id"; // Fields in the database
            String[] data = new String[1];
            data[0] = String.valueOf(3);

            PutData putData = new PutData(getSpecificDrinks_URL, "POST", field, data);
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    Log.e("php",result);
   //                 Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

                    try {
                        JSONArray array = new JSONArray(result);

                        for(int i = 0; i<array.length();i++)
                        {
                            Log.d("tag", "PUMASOK NAAA");
                            JSONObject object = array.getJSONObject(i);
                            Integer product_id = object.getInt("product_id");
                            String product_name = object.getString("product_name");
                            Double product_price = object.getDouble("product_price");
                            Integer product_stock = object.getInt("product_stock");
                            String product_details = object.getString("product_details");
                            String product_code = object.getString("product_code");
                            String product_picture = object.getString("product_picture");

                            ProductData product = new ProductData(product_id,product_name,product_price,product_stock,product_details,product_code,product_picture);
                            listproducts.add(product);
                        }

                        Log.d("te"," " + listproducts.size());
                        mAdapter = new ProductAdapter(Recyclerpage.this,listproducts);
                        recyclerView.setAdapter(mAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        else if (data == 4)
        {
            String[] field = new String[1];
            field[0] = "productClass_id"; // Fields in the database
            String[] data = new String[1];
            data[0] = String.valueOf(4);

            PutData putData = new PutData(getSpecificDrinks_URL, "POST", field, data);
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    Log.e("php",result);
  //                  Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

                    try {
                        JSONArray array = new JSONArray(result);

                        for(int i = 0; i<array.length();i++)
                        {
                            Log.d("tag", "PUMASOK NAAA");
                            JSONObject object = array.getJSONObject(i);
                            Integer product_id = object.getInt("product_id");
                            String product_name = object.getString("product_name");
                            Double product_price = object.getDouble("product_price");
                            Integer product_stock = object.getInt("product_stock");
                            String product_details = object.getString("product_details");
                            String product_code = object.getString("product_code");
                            String product_picture = object.getString("product_picture");



                            ProductData product = new ProductData(product_id,product_name,product_price,product_stock,product_details,product_code,product_picture);
                            listproducts.add(product);
                        }

                        Log.d("te"," " + listproducts.size());
                        mAdapter = new ProductAdapter(Recyclerpage.this,listproducts);
                        recyclerView.setAdapter(mAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    public void init()
    {

        profile_tv = (TextView) findViewById(R.id.profile_tv);
        home_tv = (TextView) findViewById(R.id.home_tv);
        floatingButton = (FloatingActionButton) findViewById(R.id.floatingButton);
    }
}