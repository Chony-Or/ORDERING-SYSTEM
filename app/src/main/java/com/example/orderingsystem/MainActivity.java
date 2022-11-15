package com.example.orderingsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button b_login;
    TextView tv_forgot;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_main);

        b_login = (Button) findViewById(R.id.b_login);
//        tv_forgot = (TextView) findViewById(R.id.tv_forgot);

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Homepage.class);
                startActivity(intent);
            }
        });


    }
    public void init()
    {
        b_login = (Button) findViewById(R.id.b_login);
    }
}