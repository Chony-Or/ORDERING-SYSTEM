package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class Productpage extends AppCompatActivity {

    TextView productDetails_name,productDetails_price,productDetails_stocks;
    ImageView productDetails_picture;
    Button b_addcart, b_checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productpage);

        init();

        Intent intent = getIntent();
        String intent_name = intent.getStringExtra("product_name");
        Double intent_price = intent.getDoubleExtra("product_price",0);
        int intent_stocks =  intent.getIntExtra("product_stock",0);
        String intent_picture = intent.getStringExtra("product_picture");

        if(intent!=null)
        {
            productDetails_name.setText(intent_name);
            productDetails_price.setText(String.valueOf(intent_price));
            productDetails_stocks.setText(String.valueOf(intent_stocks));
            Glide.with(Productpage.this).load(Constants.get_image+intent_picture).into(productDetails_picture);
        }

        b_addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Productpage.this,Receiptpage.class);
                startActivity(intent);

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

    }

}