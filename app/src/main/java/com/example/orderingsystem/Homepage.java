package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Homepage extends AppCompatActivity {

    ImageButton softdrinks_bt ,juice_bt ,water_bt ,alcohol_bt, coke_bt, sevenup_bt, royal_bt, zesto1_bt, zesto_bt, notif_bt;
    TextView profile_tv,home_tv,seeAll_bt;
    FloatingActionButton floatingButton;
    public ArrayList<ProductData> listproducts = new ArrayList<>();
    private final String getNotification_URL ="http:/"+Constants.IP_ADDRESS+"/test_conn/getNotification.php";
    private final String BASE_URL ="http:/"+Constants.IP_ADDRESS+"/test_conn/getProducts.php";
    private final String getDrink_URL ="http:/"+Constants.IP_ADDRESS+"/test_conn/getDrink.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.new_homepage);

        init();

        profile_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(UserData.getCustomer_id()==null)
                {
                    Toast.makeText(getApplicationContext(),"Please login your Account first",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Homepage.this ,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Homepage.this ,Profile.class);
                    startActivity(intent);
                }

            }
        });

        seeAll_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, Recyclerpage.class);
                intent.putExtra("data",5);
                startActivity(intent);
            }
        });

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, cartpage.class);
                startActivity(intent);
            }
        });

        softdrinks_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Homepage.this,Recyclerpage.class);
                intent.putExtra("data",1);

                startActivity(intent);
            }
        });
        water_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Homepage.this,Recyclerpage.class);
                intent.putExtra("data",3);

                startActivity(intent);
            }
        });
        juice_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Homepage.this,Recyclerpage.class);
                intent.putExtra("data",2);

                startActivity(intent);
            }
        });
        alcohol_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Homepage.this,Recyclerpage.class);
                intent.putExtra("data",4);

                startActivity(intent);
            }
        });

        coke_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id = 12;
                getdrink(id);

            }
        });
        sevenup_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id = 6;
                getdrink(id);
            }
        });

        royal_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = 31;
                getdrink(id);
            }
        });

        zesto1_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = 62;
                getdrink(id);
            }
        });

        zesto_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = 61;
                getdrink(id);
            }
        });

        notif_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, notification_recycler.class);
                startActivity(intent);
            }
        });

    }

    public void init()
    {

        profile_tv = (TextView) findViewById(R.id.profile_tv);
        home_tv = (TextView) findViewById(R.id.home_tv);
        seeAll_bt =(TextView) findViewById(R.id.seeAll_bt);
        floatingButton = (FloatingActionButton) findViewById(R.id.floatingButton);
        softdrinks_bt = (ImageButton) findViewById(R.id.softdrinks_bt);
        juice_bt = (ImageButton) findViewById(R.id.juice_bt);
        alcohol_bt = (ImageButton) findViewById(R.id.alcohol_bt);
        water_bt = (ImageButton) findViewById(R.id.water_bt);
        coke_bt =(ImageButton) findViewById(R.id.coke_bt);
        sevenup_bt = (ImageButton) findViewById(R.id.sevenup_bt);
        royal_bt = (ImageButton) findViewById(R.id.royal_bt);
        zesto1_bt = (ImageButton) findViewById(R.id.zesto1_bt);
        zesto_bt = (ImageButton) findViewById(R.id.zesto_bt);
        notif_bt = (ImageButton) findViewById(R.id.notif_bt);

    }


//    public void getNotification(int id)
//    {
//        String[] field = new String[1];
//        field[0] = "customer_id"; // Fields in the database
//        String[] data = new String[1];
//        data[0] = String.valueOf(id);
//
//        PutData putData = new PutData(getNotification_URL, "POST", field, data);
//        if (putData.startPut()) {
//            if (putData.onComplete()) {
//                String result = putData.getResult();
//                Log.e("php",result);
////                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
//
//                try {
//                    JSONArray array = new JSONArray(result);
//
//                    for(int i = 0; i<array.length();i++)
//                    {
//                        Log.d("tag", "PUMASOK NAAA");
//                        JSONObject object = array.getJSONObject(i);
//                        Integer notif_id = object.getInt("notif_id");
//                        Integer customer_id = object.getInt("customer_id");
//                        String notif_subject = object.getString("notif_subject");
//                        String notif_context = object.getString("notif_context");
//
//                        Intent intent = new Intent(this,Productpage.class);
//                        this.startActivity(intent);
//
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }



    public void getdrink(int id)
    {
        String[] field = new String[1];
        field[0] = "product_id"; // Fields in the database
        String[] data = new String[1];
        data[0] = String.valueOf(id);

        PutData putData = new PutData(getDrink_URL, "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                String result = putData.getResult();
                Log.e("php",result);
//                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

                try {
                    JSONArray array = new JSONArray(result);

                    for(int i = 0; i<array.length();i++)
                    {
                        Log.d("tag", "PUMASOK NAAA");
                        JSONObject object = array.getJSONObject(i);
                        Integer product_id = object.getInt("product_id");
                        String product_name = object.getString("product_name");
                        Double product_price = object.getDouble("product_price");
                        Integer product_stock = object.getInt("product_stock");
                        String product_details = object.getString("product_details");
                        String product_code = object.getString("product_code");
                        String product_picture = object.getString("product_picture");

                        Intent intent = new Intent(this,Productpage.class);

                        intent.putExtra("product_id",product_id);
                        intent.putExtra("product_name",product_name);
                        intent.putExtra("product_price",product_price);
                        intent.putExtra("product_stock",product_stock);
                        intent.putExtra("product_details",product_details);
                        intent.putExtra("product_picture",product_picture);
                        this.startActivity(intent);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}