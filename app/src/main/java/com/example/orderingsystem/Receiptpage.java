package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Receiptpage extends AppCompatActivity {

    Button b_close;
    TextView Rinvoice_number;
    TextView Rcustomer_address;
    TextView Rcustomer_name;
    TextView Rcustomer_number;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_receipt);

        init();

        Rinvoice_number.setText(UserData.getCustomer_id().toString());
        Rcustomer_name.setText(UserData.getCustomer_name().toString());
        Rcustomer_address.setText(UserData.getCustomer_address().toString());
        Rcustomer_number.setText(UserData.getCustomer_contactNo().toString());

    }
    public void init()
    {
        b_close = (Button) findViewById(R.id.b_close);
        Rcustomer_address = (TextView) findViewById(R.id.Rcustomer_address);
        Rcustomer_name = (TextView) findViewById(R.id.Rcustomer_name);
        Rcustomer_number = (TextView) findViewById(R.id.Rcustomer_number);
        Rinvoice_number = (TextView) findViewById(R.id.Rinvoice_number);

    }


}