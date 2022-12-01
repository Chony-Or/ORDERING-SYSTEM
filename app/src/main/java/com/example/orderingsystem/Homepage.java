package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class Homepage extends AppCompatActivity {

    ImageButton softdrinks_bt ,juice_bt ,water_bt ,alcohol_bt;
    TextView profile_tv,home_tv,seeAll_bt;
    FloatingActionButton floatingButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.new_homepage);

        init();

        profile_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this ,Profile.class);
                startActivity(intent);
            }
        });

        seeAll_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, Recyclerpage.class);
                intent.putExtra("data",5);
                startActivity(intent);
            }
        });

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, cartpage.class);
                startActivity(intent);
            }
        });

        softdrinks_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Homepage.this,Recyclerpage.class);
                intent.putExtra("data",1);

                startActivity(intent);
            }
        });
        water_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Homepage.this,Recyclerpage.class);
                intent.putExtra("data",3);

                startActivity(intent);
            }
        });
        juice_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Homepage.this,Recyclerpage.class);
                intent.putExtra("data",2);

                startActivity(intent);
            }
        });
        alcohol_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Homepage.this,Recyclerpage.class);
                intent.putExtra("data",4);

                startActivity(intent);
            }
        });



    }

    public void init()
    {

        profile_tv = (TextView) findViewById(R.id.profile_tv);
        home_tv = (TextView) findViewById(R.id.home_tv);
        seeAll_bt =(TextView) findViewById(R.id.seeAll_bt);
        floatingButton = (FloatingActionButton) findViewById(R.id.floatingButton);
        softdrinks_bt = (ImageButton) findViewById(R.id.softdrinks_bt);
        juice_bt = (ImageButton) findViewById(R.id.juice_bt);
        alcohol_bt = (ImageButton) findViewById(R.id.alcohol_bt);
        water_bt = (ImageButton) findViewById(R.id.water_bt);



    }


}