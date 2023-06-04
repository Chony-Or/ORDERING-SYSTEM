package com.example.orderingsystem;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;

import java.util.Objects;

public class Notification extends AppCompatActivity {

    private static final String putProduct_Url = "http://" + Constants.IP_ADDRESS + "/db_conn/addHoldOrders.php";
    TextView notif_id,notif_subject,notif_context,notif_date;
    Button ok_bt;
    Integer customer_id, int_notif_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the upper part
        setContentView(R.layout.activity_notification);

        init();

        Intent intent = getIntent();

        int intent_id = intent.getIntExtra("notif_id", 0);
        int intent_customer_id = intent.getIntExtra("customer_id", 0);
        String intent_subject = intent.getStringExtra("notif_subject");
        String intent_context = intent.getStringExtra("notif_context");
        String intent_date = intent.getStringExtra("notif_date");

        if (intent != null) {

            notif_subject.setText(intent_subject);
            notif_context.setText(intent_context);
            notif_date.setText(intent_date);

        }
    }
    public void init()
    {
        notif_subject = (TextView) findViewById(R.id.notif_subject);
        notif_context = (TextView) findViewById(R.id.notif_context);
        notif_date = (TextView) findViewById(R.id.notif_date);
    }

}