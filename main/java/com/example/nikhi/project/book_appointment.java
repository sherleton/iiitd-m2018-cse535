package com.example.nikhi.project;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.nikhi.myapplication.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static android.support.constraint.Constraints.TAG;

public class book_appointment extends Fragment
{
    private Spinner spinner;
    static DatabaseReference dbapp;
    private String dtime = "3:00pm - 3:10pm", ntime = "9:00am - 9:30am";
    private Button button, button1;
    private EditText editText;
    static String desc;
    private String day = "Monday";
    private int yearr, monthr, dater;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    public static String[] nurse = new String[] {"9:00am - 9:30am",
        "9:30am - 10:00am",
        "10:00am - 10:30am",
        "10:30am - 11:00am",
        "11:00am - 11:30am",
        "11:30am - 12:00pm",
        "12:00pm - 12:30pm",
        "12:30pm - 01:00pm",
        "01:00pm - 01:30pm",
        "01:30pm - 02:00pm",
        "02:00pm - 02:30pm",
        "02:30pm - 03:00pm",
        "03:00pm - 03:30pm",
        "03:30pm - 04:00pm",
        "04:00pm - 04:30pm",
        "04:30pm - 05:00pm",
        "05:00pm - 05:30pm",
        "05:30pm - 06:00pm",
        "06:00pm - 06:30pm",
        "06:30pm - 07:00pm",
        "07:00pm - 07:30pm",
        "07:30pm - 08:00pm",
        "08:00pm - 08:30pm",
        "08:30pm - 09:00pm"};
    public static ArrayList<String> doctor = new ArrayList<String>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Book Appointment");

        dbapp = FirebaseDatabase.getInstance().getReference("appointments");
        button = view.findViewById(R.id.button4);
        doctor.add("3:00pm - 3:10pm");
        doctor.add("3:10pm - 3:20pm");
        doctor.add("3:20pm - 3:30pm");
        doctor.add("3:30pm - 3:40pm");
        doctor.add("3:40pm - 3:50pm");
        doctor.add("3:50pm - 4:00pm");

        doctor.add("9:30am - 10:00am");
                doctor.add("10:00am - 10:30am");
                doctor.add("10:30am - 11:00am");
                doctor.add("11:00am - 11:30am");
                doctor.add("11:30am - 12:00pm");
                doctor.add("12:00pm - 12:30pm");
                doctor.add("12:30pm - 01:00pm");
                doctor.add("01:00pm - 01:30pm");
                doctor.add("01:30pm - 02:00pm");
                doctor.add("02:00pm - 02:30pm");

        doctor.add("02:30pm - 03:00pm");
                                doctor.add("04:00pm - 04:30pm");
                                        doctor.add("04:30pm - 05:00pm");
                                                doctor.add("05:00pm - 05:30pm");
                                                        doctor.add("05:30pm - 06:00pm");
                                                                doctor.add("06:00pm - 06:30pm");
                                                                        doctor.add("06:30pm - 07:00pm");
                                                                                doctor.add("07:00pm - 07:30pm");
                                                                                        doctor.add("07:30pm - 08:00pm");
                                                                                                doctor.add("08:00pm - 08:30pm");
                                                                                                        doctor.add("08:30pm - 09:00pm");



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int dayofmonth=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,onDateSetListener, year, month, dayofmonth);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                datePickerDialog.show();
            }
        });

        spinner = view.findViewById(R.id.spinnerD);
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar=Calendar.getInstance();
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                String currentDateString=DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                doctor.clear();
                doctor.add("3:00pm - 3:10pm");
                doctor.add("3:10pm - 3:20pm");
                doctor.add("3:20pm - 3:30pm");
                doctor.add("3:30pm - 3:40pm");
                doctor.add("3:40pm - 3:50pm");
                doctor.add("3:50pm - 4:00pm");

                doctor.add("9:30am - 10:00am");
                doctor.add("10:00am - 10:30am");
                doctor.add("10:30am - 11:00am");
                doctor.add("11:00am - 11:30am");
                doctor.add("11:30am - 12:00pm");
                doctor.add("12:00pm - 12:30pm");
                doctor.add("12:30pm - 01:00pm");
                doctor.add("01:00pm - 01:30pm");
                doctor.add("01:30pm - 02:00pm");
                doctor.add("02:00pm - 02:30pm");

                doctor.add("02:30pm - 03:00pm");
                doctor.add("04:00pm - 04:30pm");
                doctor.add("04:30pm - 05:00pm");
                doctor.add("05:00pm - 05:30pm");
                doctor.add("05:30pm - 06:00pm");
                doctor.add("06:00pm - 06:30pm");
                doctor.add("06:30pm - 07:00pm");
                doctor.add("07:00pm - 07:30pm");
                doctor.add("07:30pm - 08:00pm");
                doctor.add("08:00pm - 08:30pm");
                doctor.add("08:30pm - 09:00pm");
                day = currentDateString.split(",")[0];
                yearr = year;
                monthr = month+1;
                dater = dayOfMonth;
                dbapp.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            //Toast.makeText(getContext(),dater+"/"+monthr+"/"+yearr + " "+ds.child("date").getValue().toString(),Toast.LENGTH_SHORT).show();
                            if (ds.child("date").getValue().toString().equals(dater+"/"+monthr+"/"+yearr)&&!ds.child("accept").getValue().toString().equals("complete")){
                                doctor.remove(ds.child("time").getValue().toString());
                            }
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, doctor);
                        spinner.setAdapter(adapter);

                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                dtime = parent.getItemAtPosition(position).toString();
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
                Log.d(TAG, "onDateSet: " + yearr + monthr + dater);
            }
        };

        editText = (EditText) view.findViewById(R.id.editText);
        desc = editText.getText().toString();
        if(day.equals("Sunday") || day.equals("Saturday")) {
            doctor.clear();
        }



        button1 = view.findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                desc = editText.getText().toString();
                dbapp.child(LoginActivity.user).setValue(new appointments("false",dater+"/"+monthr+"/"+yearr,dtime,desc,LoginActivity.roll,LoginActivity.name));
                Toast.makeText(getContext(),"SENT BOOKING",Toast.LENGTH_SHORT).show();//database access here
            }
        });

    }
//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//        Calendar calendar=Calendar.getInstance();
//        calendar.set(Calendar.YEAR,year);
//        calendar.set(Calendar.MONTH,month);
//        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
//        String currentDateString=DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
//
//        day = currentDateString.split(",")[0];
//        this.year = year;
//        this.month = month;
//        this.date = dayOfMonth;
//    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book,container,false);
    }
}
