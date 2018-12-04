package com.example.nikhi.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.nikhi.myapplication.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Doctor_view_medicine extends Fragment
{
    private Spinner spinner, spinner1;
    private String medicine = "Paracetamol";
    private String amount = "1";
    private Button button;
    private HashMap<String, Medicine> list;
    private TextView textView, textView1, textView2;
    private Medicine temp;
    DatabaseReference dbstock;
    private static ArrayList<String> medicinelist;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Medicines");
        dbstock = FirebaseDatabase.getInstance().getReference("stock");
        list = new HashMap<String, Medicine>();
        textView = view.findViewById(R.id.textview);
        textView1 = view.findViewById(R.id.textview1);
        // textView2 = view.findViewById(R.id.textview2);
        spinner = view.findViewById(R.id.spinner);
        dbstock.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                medicinelist = new ArrayList<String>();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    medicinelist.add(ds.getKey().toString());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, medicinelist);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        medicine = parent.getItemAtPosition(position).toString();
                        textView.setText(medicine);
                        textView1.setText(dataSnapshot.child(medicine).child("qty").getValue().toString());
                        // textView2.setText(temp.expiry);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //database retrieve medicine list

        spinner1 = view.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.quantity, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                amount = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button = view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
//                Uri uri = Uri.parse("sfdf");
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.putExtra("amount", Integer.parseInt(amount));
//                intent.putExtra("medicine", medicine);
//
//                startActivity(intent);
                Log.d("hell",amount);
                dbstock.child(textView.getText().toString()).child("qty").setValue(Integer.parseInt(textView1.getText().toString())+Integer.parseInt(amount));
                Toast.makeText(getContext(),"Order placed.",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_medicine,container,false);
    }

}
