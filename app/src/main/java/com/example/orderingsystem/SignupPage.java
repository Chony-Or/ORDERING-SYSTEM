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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

// for registering as a REGULAR CUSTOMER
public class SignupPage extends AppCompatActivity {


    EditText cust_firstname, cust_lastname, cust_number, cust_houseno, cust_street, cust_city,cust_brgy, cust_username, cust_password, cust_confirm;
    Button signup_bt;
    public ArrayList<UserData> mUserData = new ArrayList<>();
    private static final String putguest_Url = "http://" + Constants.IP_ADDRESS + "/db_conn/fillup.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the upper part
        setContentView(R.layout.activity_signup_page);

        init();

        signup_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = String.valueOf(cust_firstname.getText());
                String lastname = String.valueOf(cust_lastname.getText());
                String houseno = String.valueOf(cust_houseno.getText());
                String street = String.valueOf(cust_street.getText());
                String city = String.valueOf(cust_city.getText());
                String brgy = String.valueOf(cust_brgy.getText());
                String username = String.valueOf(cust_username.getText());
                String password = String.valueOf(cust_password.getText());
                String confirmpass = String.valueOf(cust_confirm);
                String number = String.valueOf(cust_number.getText());

                if (!firstname.equals("") && !lastname.equals("")&& !houseno.equals("")&& !street.equals("")&& !city.equals("")&& !brgy.equals("")&& !username.equals("")&& !password.equals("")&& !confirmpass.equals("")&& !number.equals(""))
                {

                    int len = 0;
                    len = number.length();
                    if(len == 11)
                    {
                        String[] field = new String[9];
                        field[0] = "customer_firstname"; // Fields in the database
                        field[1] = "customer_lastname";
                        field[2] = "customer_contactNo";
                        field[3] = "customer_houseno";
                        field[4] = "customer_street";
                        field[5] = "customer_city";
                        field[6] = "customer_brgy";
                        field[7] = "customer_username";
                        field[8] = "customer_password";

                        String[] data = new String[9];
                        data[0] = firstname; // Fields in the database
                        data[1] = lastname;
                        data[2] = number;
                        data[3] = houseno;
                        data[4] = street;
                        data[5] = city;
                        data[6] = brgy;
                        data[7] = username;
                        data[8] = password;

                        PutData putData = new PutData(putguest_Url, "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                Log.e("php",result);
                                //                   Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

                                try {
                                    JSONArray array = new JSONArray(result);

                                    for(int i = 0; i<array.length();i++)
                                    {
                                        Log.d("tag", "PUMASOK NAAA");
                                        JSONObject object = array.getJSONObject(i);
                                        Integer cust_id = object.getInt("customer_id");
                                        Integer custClass_id = object.getInt("customerClass_id");


                                        UserData userData = new UserData(cust_id, custClass_id, number, firstname, lastname, houseno, street, city,brgy);
                                        mUserData.add(userData);

                                        Toast.makeText(getApplicationContext(), "Submission Success", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignupPage.this, Homepage.class);
                                        startActivity(intent);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

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
        cust_firstname = (EditText) findViewById(R.id.cust_firstname);
        cust_lastname = (EditText) findViewById(R.id.cust_lastname);
        cust_number = (EditText) findViewById(R.id.cust_number);
        cust_houseno = (EditText) findViewById(R.id.cust_houseno);
        cust_street = (EditText) findViewById(R.id.cust_street);
        cust_city = (EditText) findViewById(R.id.cust_city);
        cust_brgy = (EditText) findViewById(R.id.cust_brgy);
        cust_username = (EditText) findViewById(R.id.cust_username);
        cust_password = (EditText) findViewById(R.id.cust_password);
        cust_confirm = (EditText) findViewById(R.id.cust_confirmpass);
        signup_bt = (Button) findViewById(R.id.signup_bt);

    }
}