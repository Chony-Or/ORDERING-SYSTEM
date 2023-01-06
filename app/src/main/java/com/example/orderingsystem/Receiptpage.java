package com.example.orderingsystem;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class Receiptpage extends AppCompatActivity {

    Button b_close;
    TextView Rinvoice_number;
    TextView Rcustomer_address;
    TextView Rcustomer_name;
    TextView Rcustomer_number;
    TextView totalreceipt_tv;
    Double holdTotal = 0.00;
    DecimalFormat formatter = new DecimalFormat("#,###,###.##");
    public RecyclerView recyclerView;
    private ReceiptAdapter mAdapter;
    public ArrayList<ReceiptData> ReceiptList = new ArrayList<>();
    private final String getReceiptData_URL ="http:/"+Constants.IP_ADDRESS+"/test_conn/getReceiptDetails.php";
    private Bundle savedInstanceState;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_receipt);



        init();


        recyclerView = findViewById(R.id.receiptRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        mAdapter = new ReceiptAdapter(this,ReceiptList);
        recyclerView.setAdapter(mAdapter);


        Rcustomer_name.setText(UserData.getCustomer_name());
        Rcustomer_address.setText(UserData.getCustomer_address());
        Rcustomer_number.setText(UserData.getCustomer_contactNo());


        Integer customer_id = UserData.getCustomer_id();


            String[] field = new String[1];
            field[0] = "customer_id"; // Fields in the database
            String[] data = new String[1];
            data[0] = String.valueOf(customer_id);
            Log.e("customer id",String.valueOf(customer_id));

            PutData putData = new PutData(getReceiptData_URL, "POST", field, data);
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    Log.e("php",result);
                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

                    try {
                        JSONArray array = new JSONArray(result);

                        for(int i = 0; i<array.length();i++)
                        {
                            Log.d("tag", "PUMASOK NAAA");
                            JSONObject object = array.getJSONObject(i);
                            Integer product_id = object.getInt("product_id");
                            String product_name = object.getString("product_name");
                            Double amount = object.getDouble("amount");
                            Integer order_number = object.getInt("order_number");
                            Integer quantity = object.getInt("quantity");

                            holdTotal += Double.valueOf(quantity * amount);


                            Rinvoice_number.setText(order_number.toString());
                            ReceiptData rData = new ReceiptData(product_id, product_name, order_number, quantity, amount);
                            ReceiptList.add(rData);
                        }

                        Log.d("te"," " + ReceiptList.size());
                        mAdapter = new ReceiptAdapter(Receiptpage.this,ReceiptList);
                        recyclerView.setAdapter(mAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    totalreceipt_tv.setText("₱ " + formatter.format(holdTotal));
                }

            }


    }
    public void showAlertDialog(View view)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Reminder!");
        alert.setMessage("Please wait for a call from the management to confirm your order/s. Thank you!");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Intent intent = new Intent(Receiptpage.this, Homepage.class);
                startActivity(intent);
            }

        });
        alert.create().show();
    }

    public void init()
    {
        b_close = (Button) findViewById(R.id.b_close);
        Rcustomer_address = (TextView) findViewById(R.id.Rcustomer_address);
        Rcustomer_name = (TextView) findViewById(R.id.Rcustomer_name);
        Rcustomer_number = (TextView) findViewById(R.id.Rcustomer_number);
        Rinvoice_number = (TextView) findViewById(R.id.Rinvoice_number);
        totalreceipt_tv= (TextView) findViewById(R.id.totalreceipt_tv);


    }




}