package com.example.orderingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class cartpage extends AppCompatActivity {

    TextView profile_tv,home_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartpage);

        init();
        profile_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cartpage.this ,Profile.class);
                startActivity(intent);
            }
        });
        home_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cartpage.this ,Homepage.class);
                startActivity(intent);
            }
        });
        home_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cartpage.this ,Homepage.class);
                startActivity(intent);
            }
        });


    }
    public void init()
    {

        profile_tv = (TextView) findViewById(R.id.profile_tv);
        home_tv = (TextView) findViewById(R.id.home_tv);

    }
}