package com.example.orderingsystem;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class cartpage extends AppCompatActivity {

    TextView profile_tv,home_tv;
    public RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    public ArrayList<CartData> cartListProducts = new ArrayList<>();
    private static final String getCustomer_Order = "http:/" + Constants.IP_ADDRESS + "/test_conn/getholdOrders.php";
    private static final String addCustomer_Order = "http:/" + Constants.IP_ADDRESS + "/db_conn/addPendingOrder.php";
    private final String BASE_URL ="http:/"+Constants.IP_ADDRESS+"/test_conn/getProducts.php";
    // Database version
    private static final int DATABASE_VERSION = 1;
    Double holdTotal = 0.00;
    // Database name
    FloatingActionButton floatingButton;
    TextView total_tv;
    Button checkout_bt;
    TextView totalreceipt_tv;
    CheckBox selectAll_cb;
    String product_name;
    DecimalFormat formatter = new DecimalFormat("#,###,###.##");
    public int min = 100000000;
    public int max = 900000000;
//    public long random_num = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;



    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartpage);

        SQLiteDatabase db = openOrCreateDatabase("Hold_Order", MODE_PRIVATE, null);
        init();


        recyclerView = findViewById(R.id.cartProductsRView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        String[] field = new String[1];
        field[0] = "customer_id"; // Fields in the database
        String[] data = new String[1];
        data[0] = String.valueOf(UserData.getCustomer_id());

        PutData putData = new PutData(getCustomer_Order, "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                String result = putData.getResult();
                Log.e("php", result);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

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

                        holdTotal += Double.valueOf(quantity * amount);


                        CartData cartData = new CartData(product_id, customer_id, product_name, quantity, amount, product_picture);
                        cartListProducts.add(cartData);
                    }
                    cartAdapter = new CartAdapter(cartpage.this, cartListProducts);
                    recyclerView.setAdapter(cartAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            total_tv.setText("₱ " + formatter.format(holdTotal));

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

        // for check out putting orders to database
        checkout_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int random_num =(int)(Math.random()*max-min+1)+min;


                String[] field = new String[1];
                field[0] = "customer_id"; // Fields in the database
                String[] data = new String[1];
                data[0] = String.valueOf(UserData.getCustomer_id());

                PutData putData = new PutData(getCustomer_Order, "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.e("php", result);
 //                       Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

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

                                Handler handler = new Handler();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        String[] field = new String[7];
                                        field[0] = "product_id"; // Fields in the database
                                        field[1] = "product_name";
                                        field[2] = "order_number";
                                        field[3] = "customer_id";
                                        field[4] = "quantity";
                                        field[5] = "amount";
                                        field[6] = "is_active";


                                        String[] data = new String[7];
                                        data[0] = product_id.toString();
                                        data[1] = product_name;
                                        data[2] = String.valueOf(random_num);
                                        data[3] = customer_id.toString();
                                        data[4] = quantity.toString();
                                        data[5] = amount.toString();
                                        data[6] = "1";

                                        PutData putData = new PutData(addCustomer_Order, "POST", field, data);
                                        if (putData.startPut()) {
                                            if (putData.onComplete()) {
                                                String result = putData.getResult();
                                                Log.e("ADDING ORDER TO PENDING", "PUMASOK DITO");

                                                if (result.equals("Add Order Success")) {
                                                    Log.e("ADDING ORDER TO PENDING", "PUMASOK SUCCESS");

//                                                    Toast.makeText(getApplicationContext(), "ASA DATABASE NA ORDER", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(cartpage.this, Receiptpage.class);
                                                    startActivity(intent);
                                                    finish();
                                                }

                                                //End ProgressBar (Set visibility to GONE)
                                                Log.i("PutData", result);

                                            }
                                        }
                                    }
                                });

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }

        });

    }

    public void init() {

        profile_tv = (TextView) findViewById(R.id.profile_tv);
        home_tv = (TextView) findViewById(R.id.home_tv);
        total_tv = findViewById(R.id.total_tv);
        checkout_bt = findViewById(R.id.checkout_bt);
        totalreceipt_tv= (TextView) findViewById(R.id.totalreceipt_tv);

    }
    public void reload()
    {
        finish();
        startActivity(getIntent());
        cartAdapter.notifyDataSetChanged();

    }
}