package com.example.orderingsystem;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class cart_item_list extends AppCompatActivity {
    TextView IncDec_tv;
    Button increment, decrement;
    int incdec_Value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_item_list);
    }

    public void init()
    {
        increment = (Button) findViewById(R.id.increment_btn);
        decrement = (Button) findViewById(R.id.decrement_btn);
        IncDec_tv = findViewById(R.id.incdec_tv);

    }

    public void IncDec(){
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incdec_Value++;
                IncDec_tv.setText("" + incdec_Value);

            }
        });

        increment.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                if (incdec_Value <= 0)
                    incdec_Value = 0;
                else
                    incdec_Value--;
                IncDec_tv.setText("" + incdec_Value);
            }
        });

    }// end of IncDec

}