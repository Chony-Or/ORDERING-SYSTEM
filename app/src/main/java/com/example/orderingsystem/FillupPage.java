package com.example.orderingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;

// for registering as a IRREGULAR CUSTOMER
public class FillupPage extends AppCompatActivity {


    EditText guest_fullname, guest_number, guest_address;
    Button submit_bt;
    private static final String putguest_Url = "http://" + Constants.IP_ADDRESS + "/db_conn/fillup.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillup_page);

        init();

        submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = String.valueOf(guest_fullname.getText());
                String address = String.valueOf(guest_address.getText());
                String Number = String.valueOf(guest_number.getText());

                if (!fullname.equals("") && !address.equals("")) {

                    int len = 0;
                    len = Number.length();

                    if(len == 11)
                    {

                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                String[] field = new String[3];
                                field[0] = "customer_name"; // Fields in the database
                                field[1] = "customer_address";
                                field[2] = "customer_contactNo";

                                String[] data = new String[3];
                                data[0] = fullname;
                                data[1] = address;
                                data[2] = Number;

                                PutData putData = new PutData(putguest_Url, "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        Log.e("CHECKING", "PUMASOK DITO");

                                        if (!result.equals("")) {

                                            Log.e("CHECKING IDDD", result);
                                            UserData userData = new UserData(Integer.valueOf(result), fullname, address, Number, 2);


                                            Toast.makeText(getApplicationContext(), "Submission Success", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(FillupPage.this, Homepage.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        //End ProgressBar (Set visibility to GONE)
                                        Log.i("PutData", result);
                                    }
                                }
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Please check your number", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void init()
    {
        guest_fullname = (EditText) findViewById(R.id.guest_fullname);
        guest_number = (EditText) findViewById(R.id.guest_number);
        guest_address = (EditText) findViewById(R.id.guest_address);
        submit_bt = (Button) findViewById(R.id.submit_bt);

    }
}