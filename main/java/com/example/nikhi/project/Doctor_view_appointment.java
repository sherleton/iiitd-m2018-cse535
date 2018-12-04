package com.example.nikhi.project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/*import com.example.nikhi.sos.Appointment;
import com.example.nikhi.sos.AppointmentAdapter;
import com.example.nikhi.sos.R;*/

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Doctor_view_appointment extends Fragment
{
    RecyclerView recyclerView;
    AppointmentAdapter appointmentAdapter;
    static ArrayList<Appointment> list;
    DatabaseReference db;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("View Appointments");
        db = FirebaseDatabase.getInstance().getReference();
        list = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                Log.d("here","");
                for (DataSnapshot ds : dataSnapshot.child("appointments").getChildren()){
                    //Toast.makeText(getContext(),ds.child("accept").getValue().toString(),Toast.LENGTH_SHORT);
                    if (!ds.child("accept").getValue().toString().equals("complete"))
                        list.add(new Appointment(0,ds.getKey().toString(), ds.child("name").getValue().toString(), ds.child("description").getValue().toString(), ds.child("time").getValue().toString(), ds.child("date").getValue().toString(),ds.child("accept").getValue().toString()));
                }
                appointmentAdapter = new AppointmentAdapter(getContext(), list);
                recyclerView.setAdapter(appointmentAdapter);

                appointmentAdapter.setOnItemClickListener(new AppointmentAdapter.OnItemClickListener() {
                    @Override
                    public void clickToDelete(int pos, String s, String s1) {
                        if(s1.equals("ACCEPT"))
                        {
                            String id = db.child("notification").push().getKey();
                            db.child("notification").child(id).setValue(new Notification(LoginActivity.name+" has accepted your request for the slot "+list.get(pos).getTime()+", "+list.get(pos).getDate()+".","Appointment request accepted!",list.get(pos).uname));
                            db.child("appointments").child(list.get(pos).uname.toString()).child("accept").setValue("true");
                        }
                        else{
                            db.child("appointments").child(list.get(pos).uname.toString()).child("accept").setValue("complete");
                            itemremove(pos);
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Cancelled",Toast.LENGTH_SHORT);
            }
        });

        //addd objects to list


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_appointment,container,false);
    }

    public void itemremove(int pos){
        list.remove(pos);
        appointmentAdapter.notifyItemRemoved(pos);
    }
}
