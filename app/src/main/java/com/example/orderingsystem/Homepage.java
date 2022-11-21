package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class Homepage extends AppCompatActivity {

    ImageView imageView1;
    TextView profile_tv,home_tv,softdrinks_seeAll,juice_seeAll;
    FloatingActionButton floatingButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_homepage);

        init();

        profile_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this ,Profile.class);
                startActivity(intent);
            }
        });
//        home_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Homepage.this ,Homepage.class);
//                startActivity(intent);
//            }
//        });

        softdrinks_seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, Recyclerpage.class);
                startActivity(intent);
            }
        });
        juice_seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, Recyclerpage.class);
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

    }

    public void init()
    {

        profile_tv = (TextView) findViewById(R.id.profile_tv);
        home_tv = (TextView) findViewById(R.id.home_tv);
        softdrinks_seeAll =(TextView) findViewById(R.id.softdrinks_seeAll);
        juice_seeAll = (TextView) findViewById(R.id.juice_seeAll);
        floatingButton = (FloatingActionButton) findViewById(R.id.floatingButton);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
    }

    public void getimage()
    {
//        setTitle("Glide Demo");
//        Glide.with(this).load();

    }

}