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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button b_login;
    EditText customer_name;
    EditText customer_password;
    public ArrayList<UserData> mUserData = new ArrayList<>();
    private static final String getCustomer_Data = "http:/" + Constants.IP_ADDRESS + "/test_conn/getCustomer.php";
    private static final String getUser_Url = "http://" + Constants.IP_ADDRESS + "/db_conn/login.php";
    public String c_name, c_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the upper part
        setContentView(R.layout.activity_main);

        init();

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                c_name = String.valueOf(customer_name.getText());
                c_password = String.valueOf(customer_password.getText());

                if (!c_name.equals("") && !c_password.equals("")) {
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "testt", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), c_name, Toast.LENGTH_SHORT).show();

                            String[] field = new String[2];
                            field[0] = "customer_name"; // Fields in the database
                            field[1] = "customer_password";

                            String[] data = new String[2];
                            data[0] = c_name;
                            data[1] = c_password;

                            PutData putData = new PutData(getUser_Url, "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();

                                    if (result.equals("Login Success")) {
                                        try {
                                            getUserdata();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this, Homepage.class);
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
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void init() {

        b_login = (Button) findViewById(R.id.b_login);
        customer_name = (EditText) findViewById(R.id.customer_name);
        customer_password = (EditText) findViewById(R.id.customer_password);
    }

    //method getting for customer data
    public void getUserdata() throws JSONException {
//
        String[] field = new String[1];
        field[0] = "customer_name"; // Fields in the database
        String[] data = new String[1];
        data[0] = c_name;

        PutData putData = new PutData(getCustomer_Data, "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                String result = putData.getResult();
                Log.e("php",result);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

                JSONArray array = new JSONArray(result);
                for (int i = 0; i < array.length(); i++) {
                    Log.d("tag", "Get USER data na");

                    JSONObject object = array.getJSONObject(i);
                    Integer customer_id = object.getInt("customer_id");
                    String customer_name = object.getString("customer_name");
                    String customer_address = object.getString("customer_address");
                    String customer_contactNo = object.getString("customer_contactNo");
                    Integer customerClass_id = object.getInt("customerClass_id");

                    UserData userData = new UserData(customer_id, customer_name, customer_address, customer_contactNo, customerClass_id);
                    mUserData.add(userData);
                }
            }
        }
    }
}