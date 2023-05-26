package com.example.orderingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Profile extends AppCompatActivity {

    TextView profile_name, profile_address,profile_number;
    Button logout_bt, history_bt;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();


        profile_name.setText(UserData.getCust_name());
        profile_address.setText(UserData.getCust_address());
        profile_number.setText(UserData.getCust_number());



        history_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"UNDER MAINTENACE",Toast.LENGTH_SHORT).show();
            }
        });

        logout_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
    public void init()
    {

        profile_name = (TextView) findViewById(R.id.profile_name);
        profile_address = (TextView) findViewById(R.id.profile_address);
        profile_number = (TextView) findViewById(R.id.profile_number);
        logout_bt = (Button) findViewById(R.id.logout_bt);
        history_bt = (Button) findViewById(R.id.history_bt);

    }
}
