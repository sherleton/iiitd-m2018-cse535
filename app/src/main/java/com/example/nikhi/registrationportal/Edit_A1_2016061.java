package com.example.nikhi.registrationportal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

public class Edit_A1_2016061 extends AppCompatActivity{

    String gs = "Start";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Log", "State of Activity Edit changed from " + gs + " to Create");
        Toast.makeText(this, "State of Activity Edit changed from " + gs + " to Create", Toast.LENGTH_SHORT ).show();
        gs = "Create";
        setContentView(R.layout.activity_edit__a1_2016061);

        Button submit = (Button) findViewById(R.id.button);
        final EditText name = (EditText) findViewById(R.id.editText);
        final EditText roll = (EditText) findViewById(R.id.editText1);
        final EditText branch = (EditText) findViewById(R.id.editText2);
        final EditText c1 = (EditText) findViewById(R.id.editText3);
        final EditText c2 = (EditText) findViewById(R.id.editText4);
        final EditText c3 = (EditText) findViewById(R.id.editText5);
        final EditText c4 = (EditText) findViewById(R.id.editText6);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent starti = new Intent(getApplicationContext(), Show_A1_2016061.class);

                String nvalue = name.getText().toString();
                starti.putExtra("name", nvalue);
                nvalue = roll.getText().toString();
                starti.putExtra("roll", nvalue);
                nvalue = branch.getText().toString();
                starti.putExtra("branch", nvalue);
                nvalue = c1.getText().toString();
                starti.putExtra("course1", nvalue);
                nvalue = c2.getText().toString();
                starti.putExtra("course2", nvalue);
                nvalue = c3.getText().toString();
                starti.putExtra("course3", nvalue);
                nvalue = c4.getText().toString();
                starti.putExtra("course4", nvalue);

                startActivity(starti);
            }
        });

        Button clear = (Button) findViewById(R.id.button2);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Edit_A1_2016061.this.onDestroy();
                Intent starti = new Intent(getApplicationContext(), Edit_A1_2016061.class);
                startActivity(starti);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Log", "State of Activity Edit changed from " + gs + " to Restart");
        Toast.makeText(Edit_A1_2016061.this, "State of Activity Edit changed from " + gs + " to Restart", Toast.LENGTH_SHORT ).show();
        gs = "Restart";
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Log", "State of Activity Edit changed from " + gs + " to Pause");
        Toast.makeText(Edit_A1_2016061.this, "State of Activity Edit changed from " + gs + " to Pause", Toast.LENGTH_SHORT ).show();
        gs = "Pause";
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Log", "State of Activity Edit changed from " + gs + " to Start");
        Toast.makeText(Edit_A1_2016061.this, "State of Activity Edit changed from " + gs + " to Start", Toast.LENGTH_SHORT ).show();
        gs = "Start";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Log", "State of Activity Edit changed from " + gs + " to Destroy");
        Toast.makeText(Edit_A1_2016061.this, "State of Activity Edit changed from " + gs + " to Destroy", Toast.LENGTH_SHORT ).show();
        gs = "Destroy";
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Log", "State of Activity Edit changed from " + gs + " to Resume");
        Toast.makeText(Edit_A1_2016061.this, "State of Activity Edit changed from " + gs + " to Resume", Toast.LENGTH_SHORT ).show();
        gs = "Resume";
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Log", "State of Activity Edit changed from " + gs + " to Stop");
        Toast.makeText(Edit_A1_2016061.this, "State of Activity Edit changed from " + gs + " to Stop", Toast.LENGTH_SHORT ).show();
        gs = "Stop";
    }
}
