package com.example.nikhi.registrationportal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Show_A1_2016061 extends AppCompatActivity {

    String gs = "Start";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Log", "State of Activity Edit changed from " + gs + " to Create");
        Toast.makeText(Show_A1_2016061.this, "State of Activity Edit changed from " + gs + " to Create", Toast.LENGTH_SHORT ).show();
        gs = "Create";
        setContentView(R.layout.activity_show__a1_2016061);

        TextView tv = (TextView) findViewById(R.id.textView);
        TextView tv1 = (TextView) findViewById(R.id.textView1);
        TextView tv2 = (TextView) findViewById(R.id.textView2);
        TextView tv3 = (TextView) findViewById(R.id.textView4);
        TextView tv4 = (TextView) findViewById(R.id.textView5);
        TextView tv5 = (TextView) findViewById(R.id.textView6);
        TextView tv6 = (TextView) findViewById(R.id.textView7);

        tv.setText("Name - " + getIntent().getStringExtra("name"));
        tv1.setText("Roll No. - " + getIntent().getStringExtra("roll"));
        tv2.setText("Branch - " + getIntent().getStringExtra("branch"));
        tv3.setText("Course 1 - " + getIntent().getStringExtra("course1"));
        tv4.setText("Course 2 - " + getIntent().getStringExtra("course2"));
        tv5.setText("Course 3 - " + getIntent().getStringExtra("course3"));
        tv6.setText("Course 4 - " + getIntent().getStringExtra("course4"));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Log", "State of Activity Edit changed from " + gs + " to Restart");
        Toast.makeText(Show_A1_2016061.this, "State of Activity Edit changed from " + gs + " to Restart", Toast.LENGTH_SHORT ).show();
        gs = "Restart";
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Log", "State of Activity Edit changed from " + gs + " to Pause");
        Toast.makeText(Show_A1_2016061.this, "State of Activity Edit changed from " + gs + " to Pause", Toast.LENGTH_SHORT ).show();
        gs = "Pause";
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Log", "State of Activity Edit changed from " + gs + " to Start");
        Toast.makeText(Show_A1_2016061.this, "State of Activity Edit changed from " + gs + " to Start", Toast.LENGTH_SHORT ).show();
        gs = "Start";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Log", "State of Activity Edit changed from " + gs + " to Destroy");
        Toast.makeText(Show_A1_2016061.this, "State of Activity Edit changed from " + gs + " to Destroy", Toast.LENGTH_SHORT ).show();
        gs = "Destroy";
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Log", "State of Activity Edit changed from " + gs + " to Resume");
        Toast.makeText(Show_A1_2016061.this, "State of Activity Edit changed from " + gs + " to Resume", Toast.LENGTH_SHORT ).show();
        gs = "Resume";
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Log", "State of Activity Edit changed from " + gs + " to Stop");
        Toast.makeText(Show_A1_2016061.this, "State of Activity Edit changed from " + gs + " to Stop", Toast.LENGTH_SHORT ).show();
        gs = "Stop";
    }
}
