package com.example.nikhi.quizeria;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity_2016061 extends AppCompatActivity implements View.OnClickListener {

    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2016061);

        start = findViewById(R.id.button);

        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, Load_A3_2016061.class);
        startActivity(i);
    }
}
