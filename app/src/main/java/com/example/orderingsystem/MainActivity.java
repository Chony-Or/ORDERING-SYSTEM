package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button b_login;
    EditText customer_name;
    EditText customer_password;
    private static final String BASE_URL ="http:/192.168.8.100/db_conn/getProducts.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the upper part
        setContentView(R.layout.activity_main);

        init();

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String c_name,c_password;

                c_name = String.valueOf(customer_name.getText());
                c_password = String.valueOf(customer_password.getText());

                if(!c_name.equals("") && !c_password.equals(""))
                {
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"testt",Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),c_name,Toast.LENGTH_SHORT).show();

                            String[]field = new String[2];
                            field[0] = "customer_name"; // Fields in the database
                            field[1] = "customer_password";

                            String[]data = new String[2];
                            data[0] = c_name;
                            data[1] = c_password;

                            PutData putData = new PutData("http://192.168.8.100/db_conn/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();

                                    if(result.equals("Login Success"))
                                    {
                                        Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this ,Homepage.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                    //End ProgressBar (Set visibility to GONE)
                                    Log.i("PutData", result);
                                }
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public void init()
    {

        b_login = (Button) findViewById(R.id.b_login);
        customer_name = (EditText) findViewById(R.id.customer_name);
        customer_password = (EditText) findViewById(R.id.customer_password);
    }

    public void login()
    {





    }

}