package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;


public class Landingpage extends AppCompatActivity {

    ImageButton b_lp;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_landingpage);

        init();

        b_lp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Landingpage.this, Homepage.class);
                startActivity(intent);

            }

        });
    }
    public void init()
    {
        b_lp = (ImageButton) findViewById(R.id.b_lp);
    }


}
