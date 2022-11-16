package com.example.orderingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Recyclerpage extends AppCompatActivity {

    TextView profile_tv,home_tv;
    FloatingActionButton floatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerpage);

        init();
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

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ProductData[] productData = new ProductData[]
                {
                        new ProductData("TestName 2", "TestPrice 2",R.drawable.img_1),
                        new ProductData("TestName 3", "TestPrice 3",R.drawable.img_2),
                        new ProductData("TestName 4", "TestPrice 4",R.drawable.img_3),

                };

        ProductAdapter productAdapter = new ProductAdapter(productData,Recyclerpage.this);
        recyclerView.setAdapter(productAdapter);
    }
    public void init()
    {

        profile_tv = (TextView) findViewById(R.id.profile_tv);
        home_tv = (TextView) findViewById(R.id.home_tv);
        floatingButton = (FloatingActionButton) findViewById(R.id.floatingButton);
    }
}