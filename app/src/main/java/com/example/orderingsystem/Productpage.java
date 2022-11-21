package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class Productpage extends AppCompatActivity {

    TextView productDetails_name,productDetails_price,productDetails_stocks;
    ImageView productDetails_picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productpage);

        init();

        Intent intent = getIntent();
        String intent_name = intent.getStringExtra("productDetails_name");
        float intent_price = intent.getFloatExtra("productDetails_price",0);
        int intent_stocks =  intent.getIntExtra("productDetails_stocks",0);
        String intent_picture = intent.getStringExtra("productDetails_picture");

        if(intent!=null)
        {
            productDetails_name.setText(intent_name);
            productDetails_price.setText(String.valueOf(intent_price));
            productDetails_stocks.setText(intent_stocks);
            Glide.with(Productpage.this).load(intent_picture).into(productDetails_picture);

        }
    }

    public void init()
    {
        productDetails_name = (TextView) findViewById(R.id.productDetails_name);
        productDetails_price = (TextView) findViewById(R.id.productDetails_price);
        productDetails_stocks = (TextView) findViewById(R.id.productDetails_stocks);
        productDetails_picture = (ImageView) findViewById(R.id.productDetails_picture);

    }

}