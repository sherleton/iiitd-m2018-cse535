package com.example.nikhi.project;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class view_Notification extends AppCompatActivity{

    RecyclerView recyclerView;
    NotificationAdapter notificationAdapter;
    ArrayList<Notification> list;
    DatabaseReference dbnot;
    ArrayList<String> notid;
    Activity activity = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_notification);
        dbnot = FirebaseDatabase.getInstance().getReference("notification");
        list = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dbnot.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notid = new ArrayList<String>();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if (ds.child("rec").getValue().toString().equalsIgnoreCase(LoginActivity.user)){
                        notid.add(ds.getKey());
                        list.add(new Notification(ds.child("display").getValue().toString(),ds.child("header").getValue().toString(),ds.child("rec").getValue().toString()));
                    }
                }

                notificationAdapter = new NotificationAdapter(activity, list);
                recyclerView.setAdapter(notificationAdapter);
                notificationAdapter.setOnItemClickListener(new NotificationAdapter.OnItemClickListener() {
                    @Override
                    public void clickToDelete(int pos) {
                        itemremove(pos);
                        String id = notid.remove(pos);
                        dbnot.child(id).removeValue();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void itemremove(int pos){
        list.remove(pos);
        notificationAdapter.notifyItemRemoved(pos);
    }

}
