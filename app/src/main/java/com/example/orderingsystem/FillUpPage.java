package com.example.orderingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FillUpPage extends AppCompatActivity {


    EditText guest_fullname, guest_number, guest_address;
    Button submit_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_up_page);

        init();

        submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = String.valueOf(guest_fullname.getText());
                String address = String.valueOf(guest_address.getText());
                String Number = String.valueOf(guest_number.getText());



            }
        });









    }

    public void init()
    {
        guest_fullname = (EditText) findViewById(R.id.guest_fullname);
        guest_number = (EditText) findViewById(R.id.guest_number);
        guest_address = (EditText) findViewById(R.id.guest_number);
        submit_bt = (Button) findViewById(R.id.submit_bt);

    }
}