package com.example.nikhi.project;

import android.app.Notification;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Doctor_medicine_requests extends Fragment
{
    RecyclerView recyclerView;
    MedicineAdapter medicineAdapter;
    static ArrayList<Medicine> list;
    DatabaseReference db;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Medicine Requests");
        db = FirebaseDatabase.getInstance().getReference();
        list = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                Log.d("here","");
                for (DataSnapshot ds : dataSnapshot.child("medreq").getChildren()){
                    String qty = dataSnapshot.child("stock").child(ds.child("medicine").getValue().toString()).child("qty").getValue().toString();
                    Toast.makeText(getContext(),ds.child("accept").getValue().toString(),Toast.LENGTH_SHORT);
                    if (ds.child("accept").getValue().toString().equals("false")) {
                        list.add(new Medicine(Integer.parseInt(ds.getKey().toString()), ds.child("name").getValue().toString(), ds.child("medicine").getValue().toString(), Integer.parseInt(ds.child("roll").getValue().toString()), Integer.parseInt(ds.child("qty").getValue().toString()),ds.child("user").getValue().toString(),qty));
                    }
                }
                medicineAdapter = new MedicineAdapter(getContext(), list);
                recyclerView.setAdapter(medicineAdapter);

                medicineAdapter.setOnItemClickListener(new MedicineAdapter.OnItemClickListener() {
                    @Override
                    public void clickToDelete(int pos) {
                        db.child("medreq").child(""+list.get(pos).getId()).child("accept").setValue("true");
                        String s = db.child("notification").push().getKey();
                        db.child("stock").child(list.get(pos).getMed()).child("qty").setValue(Integer.parseInt(list.get(pos).oquant)-list.get(pos).getQuant());
                        db.child("notification").child(s).setValue(new com.example.nikhi.project.Notification(LoginActivity.name+" has accepted your request for "+list.get(pos).getQuant()+" pack(s) of "+list.get(pos).getMed()+".","Medicine request accepted!",list.get(pos).uname));
                        itemremove(pos);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Cancelled",Toast.LENGTH_SHORT);
            }
        });




    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_medicine_requests,container,false);
    }

    public void itemremove(int pos){
        list.remove(pos);
        medicineAdapter.notifyItemRemoved(pos);
    }
}
