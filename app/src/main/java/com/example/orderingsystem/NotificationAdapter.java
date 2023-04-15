package com.example.orderingsystem;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.getIntent;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ImageViewTargetFactory;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private NotificationAdapter notificationAdapter;
    ArrayList<NotificationData> notificationDataList ;
    Context context;

    public NotificationAdapter(Context context, ArrayList<NotificationData> notificationDataList) {
        this.notificationDataList = notificationDataList;
        this.context = context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView notif_subject;
        private TextView notif_context;
        private TextView notif_date;
        private LinearLayout notifLinearLayout;

        public ViewHolder(@NonNull View view) {
            super(view);


            notif_subject = view.findViewById(R.id.notif_subjecttv);
            notif_context = view.findViewById(R.id.notif_contexttv);
            notif_date = view.findViewById(R.id.notif_datetv);
            notifLinearLayout = view.findViewById(R.id.notifLinearLayout);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.notification_item_list,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        NotificationData notificationData = notificationDataList.get(position);
        holder.notif_subject.setText(notificationData.getNotif_subject());
        holder.notif_context.setText(notificationData.getNotif_context());
        holder.notif_date.setText(notificationData.getNotif_date());
        Log.e("NAG CLICK NG NOTIF", String.valueOf(notificationData.getNotif_subject()));
        Log.e("notification Subject", String.valueOf(notificationData.getNotif_subject()));
        Log.e("notifcation Context", String.valueOf(notificationData.getNotif_context()));

        holder.notifLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, notificationData.getNotif_subject(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,Notification.class);

                intent.putExtra("notif_subject",notificationData.getNotif_subject());
                intent.putExtra("notif_context",notificationData.getNotif_context());
                intent.putExtra("notif_date",notificationData.getNotif_date());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return notificationDataList.size();
    }
    /////////////////////////
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}