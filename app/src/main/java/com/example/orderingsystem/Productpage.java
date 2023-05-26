package com.example.orderingsystem;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;

public class Productpage extends AppCompatActivity {

    private static final String putProduct_Url = "http://" + Constants.IP_ADDRESS + "/db_conn/addHoldOrders.php";
    TextView productDetails_name,productDetails_price,productDetails_stocks,productDetails_details, counter;
    ImageView productDetails_picture;
    Button b_addcart, b_checkout, decrement_bt, increment_bt;
    Integer customer_id, product_id,quantity,stocks;
    String product_picture, product_name;
    Double amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productpage);

        init();

        Intent intent = getIntent();

        String intent_name = intent.getStringExtra("product_name");
        Double intent_price = intent.getDoubleExtra("product_price",0);
        int intent_stocks =  intent.getIntExtra("product_stock",0);
        String intent_details = intent.getStringExtra("product_details");
        stocks = intent.getIntExtra("product_stock",0);
        String intent_picture = intent.getStringExtra("product_picture");
        product_id = intent.getIntExtra("product_id", 0);

        if(intent!=null)
        {

            productDetails_name.setText(intent_name);
            productDetails_price.setText(String.valueOf(intent_price));
            productDetails_stocks.setText(String.valueOf(intent_stocks));
            productDetails_details.setText(intent_details);

            counter.setText("1");
            Glide.with(Productpage.this).load(Constants.get_image+intent_picture).into(productDetails_picture);


        }

        b_addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                product_id = intent.getIntExtra("product_id", 0);
                customer_id = UserData.getCust_id();
                product_name = String.valueOf(intent_name);
                amount = Double.valueOf(intent_price);
                quantity = Integer.valueOf(String.valueOf(counter.getText()));
                stocks = intent.getIntExtra("product_stock",0);
                product_picture = String.valueOf(intent_picture);

                Log.e("cart id SA PRODUCT PAGE", String.valueOf(product_id));
                Log.e("cart customer id", String.valueOf(customer_id));
                Log.e("cart Product name", String.valueOf(product_name));
                Log.e("cart quantity", String.valueOf(quantity));
                Log.e("cart amount", String.valueOf(amount));
                Log.e("cart product_picture", String.valueOf(product_picture));

                passdata();

                Intent intent = new Intent(Productpage.this,cartpage.class);
                startActivity(intent);

            }
        });
        increment_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hold = Integer.valueOf(String.valueOf(counter.getText()));

                if(hold<=stocks)
                {
                    hold++;
                    counter.setText(String.valueOf(hold));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Not enough Stocks",Toast.LENGTH_SHORT).show();
                }
                quantity = Integer.valueOf(String.valueOf(counter.getText()));
            }
        });
        decrement_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hold = Integer.valueOf(String.valueOf(counter.getText()));

                if(hold<=stocks && hold>1)
                {
                    hold--;
                    counter.setText(String.valueOf(hold));
                }
                quantity = Integer.valueOf(String.valueOf(counter.getText()));
            }
        });
    }

    public void init()
    {
        b_addcart = (Button) findViewById(R.id.b_addcart);
        b_checkout = (Button) findViewById(R.id.b_checkout);
        productDetails_name = (TextView) findViewById(R.id.productDetails_name);
        productDetails_price = (TextView) findViewById(R.id.productDetails_price);
        productDetails_stocks = (TextView) findViewById(R.id.productDetails_stocks);
        productDetails_picture = (ImageView) findViewById(R.id.productDetails_picture);
        productDetails_details = (TextView) findViewById(R.id.product_details_tv);
        counter = (TextView) findViewById(R.id.counter);
        increment_bt = (Button) findViewById(R.id.increment_bt);
        decrement_bt = (Button) findViewById(R.id.decrement_bt);

    }

    public void passdata()
    {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                String[] field = new String[6];
                field[0] = "product_id"; // Fields in the database
                field[1] = "customer_id";
                field[2] = "product_name";
                field[3] = "quantity";
                field[4] = "amount";
                field[5] = "product_picture";

                String[] data = new String[6];
                data[0] = String.valueOf(product_id);
                data[1] = String.valueOf(customer_id);
                data[2] = product_name;
                data[3] = String.valueOf(quantity);
                data[4] = String.valueOf(amount);
                data[5] = product_picture;

                PutData putData = new PutData(putProduct_Url, "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();

                        if (result.equals("Add Order Success")) {

                            Toast.makeText(getApplicationContext(), "Add Order Success", Toast.LENGTH_SHORT).show();
                        }

                        Log.i("PutData", result);
                    }
                }
            }
        });

    }
}