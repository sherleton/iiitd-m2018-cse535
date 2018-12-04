package com.example.nikhi.project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

//import com.example.nikhi.sos.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Doctor_view_patient_history extends Fragment
{
    RecyclerView recyclerView;
    HistoryAdapter historyAdapter;
    static ArrayList<History> list;
    DatabaseReference dbapp;
    private Spinner spinner;
    private Button button;
    private String roll;
    private EditText editText;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbapp = FirebaseDatabase.getInstance().getReference("appointments");
        getActivity().setTitle("Patient History");
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        button = view.findViewById(R.id.button2);
        editText = view.findViewById(R.id.editText6);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roll = String.valueOf(editText.getText());
                list = new ArrayList<>();
                dbapp.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            if (ds.child("roll").getValue().toString().equals(roll) && (ds.child("accept").getValue().toString().equals("complete")))
                                list.add(new History(1, ds.child("description").getValue().toString(),ds.child("time").getValue().toString(), ds.child("date").getValue().toString()));
                        }
                        historyAdapter = new HistoryAdapter(getContext(), list);
                        recyclerView.setAdapter(historyAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                //Database search based on roll number and get info
                //recyclerView.setAlpha(1);
            }
        });


        //addd objects to list
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_patient_history,container,false);
    }
}