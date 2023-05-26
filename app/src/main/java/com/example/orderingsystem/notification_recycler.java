package com.example.orderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class notification_recycler extends AppCompatActivity {

    TextView profile_tv,home_tv;
    public RecyclerView recyclerView;
    private NotificationAdapter mAdapter;
    public ArrayList<NotificationData> listnotif = new ArrayList<>();
    private final String BASE_URL ="http:/"+Constants.IP_ADDRESS+"/test_conn/getNotification.php";
    private final String getNotification_URL ="http:/"+Constants.IP_ADDRESS+"/test_conn/getSpecificDrinks.php";

    private Integer data;
    FloatingActionButton floatingButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_recycler);

        init();

        recyclerView = findViewById(R.id.notif_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        mAdapter = new NotificationAdapter(this,listnotif);
        recyclerView.setAdapter(mAdapter);



        profile_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notification_recycler.this ,Profile.class);
                startActivity(intent);
            }
        });
        home_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notification_recycler.this ,Homepage.class);
                startActivity(intent);
            }
        });

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notification_recycler.this, cartpage.class);
                startActivity(intent);
            }
        });
        Log.e("Customer ID: ", String.valueOf(UserData.getCust_id()));
        getNotification(UserData.getCust_id());

    }

    public void getNotification(int id)
    {
        String[] field = new String[1];
        field[0] = "customer_id"; // Fields in the database
        String[] data = new String[1];
        data[0] = String.valueOf(id);

        Log.e("Pumasok sa Function: ", "pumasok sa function");

        PutData putData = new PutData(BASE_URL, "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                String result = putData.getResult();
                Log.e("Pumasok sa CONDITION: ", "pumasok sa CONDITION");
                Log.e("get Notif",result);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

                try {
                    JSONArray array = new JSONArray(result);

                    for(int i = 0; i<array.length();i++)
                    {
                        Log.d("tag", "PUMASOK NAAA");
                        JSONObject object = array.getJSONObject(i);
                        Integer notif_id = object.getInt("notif_id");
                        Integer customer_id = object.getInt("customer_id");
                        String notif_subject = object.getString("notif_subject");
                        String notif_context = object.getString("notif_context");
                        String notif_date = object.getString("notif_date");

                        NotificationData notif_data = new NotificationData(notif_id,customer_id,notif_subject,notif_context, notif_date);
                        listnotif.add(notif_data);
                    }

                    Log.d("te"," " + listnotif.size());
                    mAdapter = new NotificationAdapter(notification_recycler.this,listnotif);
                    recyclerView.setAdapter(mAdapter);

                }catch(Exception e)
                {
                    Log.d("test", e.toString());
                }
            }
        }
    }

    public void init()
    {

        profile_tv = (TextView) findViewById(R.id.profile_tv);
        home_tv = (TextView) findViewById(R.id.home_tv);
        floatingButton = (FloatingActionButton) findViewById(R.id.floatingButton);
    }
}