package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Receiptpage extends AppCompatActivity {

    Button b_close;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_receipt);

        init();

        b_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Receiptpage.this, Homepage.class);
                startActivity(intent);

            }

        });
    }
    public void init()
    {
        b_close = (Button) findViewById(R.id.b_close);
    }


}
