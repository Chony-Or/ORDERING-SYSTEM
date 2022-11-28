package com.example.orderingsystem;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class Productpage extends AppCompatActivity {

    TextView productDetails_name,productDetails_price,productDetails_stocks, counter;
    ImageView productDetails_picture;
    Button b_addcart, b_checkout, decrement_bt, increment_bt;
    Integer customer_id, product_id,quantity,stocks;
    String product_picture, product_name;
    Double amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productpage);

        SQLiteDatabase db = openOrCreateDatabase("Hold_Order",MODE_PRIVATE,null);
        init();
        database();

        Intent intent = getIntent();

        String intent_name = intent.getStringExtra("product_name");
        Double intent_price = intent.getDoubleExtra("product_price",0);
        int intent_stocks =  intent.getIntExtra("product_stock",0);
        stocks = intent.getIntExtra("product_stock",0);
        String intent_picture = intent.getStringExtra("product_picture");
        product_id = intent.getIntExtra("product_id", 0);

        if(intent!=null)
        {

            productDetails_name.setText(intent_name);
            productDetails_price.setText(String.valueOf(intent_price));
            productDetails_stocks.setText(String.valueOf(intent_stocks));


            counter.setText("1");
            Glide.with(Productpage.this).load(Constants.get_image+intent_picture).into(productDetails_picture);

            //pass data to local sqlite

        }

        b_addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                product_id = intent.getIntExtra("product_id", 0);
                customer_id = UserData.getCustomer_id();
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
        counter = (TextView) findViewById(R.id.counter);
        increment_bt = (Button) findViewById(R.id.increment_bt);
        decrement_bt = (Button) findViewById(R.id.decrement_bt);

    }

    public void database()
    {
        SQLiteDatabase db = openOrCreateDatabase("Hold_Order",MODE_PRIVATE,null);
        db.execSQL(("CREATE TABLE IF NOT EXISTS ProductOrder_tbl(" +
                "product_id INTEGER," +
                "customer_id INTEGER," +
                "product_name TEXT," +
                "quantity INTEGER," +
                "amount DOUBLE," +
                "product_picture TEXT" +
                ")"));
    }

    public void passdata()
    {
        SQLiteDatabase db = openOrCreateDatabase("Hold_Order",MODE_PRIVATE,null);

        Cursor cursor = db.rawQuery("SELECT * FROM ProductOrder_tbl WHERE product_id like '"+product_id+"'", null);

        Log.e("cursor tooo", String.valueOf(cursor));
        if (cursor.moveToFirst()) {
            Log.e("IFFFFFFFFFFF", "PASS DITO");
            ContentValues data = new ContentValues();
            data.put("product_id",product_id);
            data.put("customer_id",customer_id );
            data.put("product_name",product_name);
            data.put("quantity ",quantity);
            data.put("amount ",amount);
            data.put("product_picture", product_picture);

            db.update("ProductOrder_tbl",data, "product_id = ?", new String[]{String.valueOf(product_id)});
        }

        else
        {
            Log.e("ELSEEEEE", "PASS DITO");
            ContentValues data = new ContentValues();
            data.put("product_id",product_id);
            data.put("customer_id",customer_id );
            data.put("product_name",product_name);
            data.put("quantity ",quantity);
            data.put("amount ",amount);
            data.put("product_picture", product_picture);

            db.insert("ProductOrder_tbl",null,data);
        }


    }
}