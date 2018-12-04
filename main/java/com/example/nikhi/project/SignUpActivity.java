package com.example.nikhi.project;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    EditText user_name;
    EditText user_roll;
    EditText user_phone;
    EditText user_email;
    EditText user_pass;
    EditText user_repass;
    Activity act = this;
    static DatabaseReference dbuse;
    //Switch simpleSwitch;

    static String uname, uroll, uphone, uemail, upass, urepass;

    public Button button, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        dbuse = FirebaseDatabase.getInstance().getReference("users");
        user_pass = (EditText)findViewById(R.id.editpass);
        user_repass = (EditText)findViewById(R.id.editrepass);
        user_name = (EditText)findViewById(R.id.editname);
        user_roll = (EditText)findViewById(R.id.editroll);
        user_phone = (EditText)findViewById(R.id.editphone);
        user_email = (EditText)findViewById(R.id.editemail);
        uname= user_name.getText().toString();
        uroll = user_roll.getText().toString();
        uphone = user_phone.getText().toString();
        uemail = user_email.getText().toString();
        upass = user_pass.getText().toString();
        urepass = user_repass.getText().toString();
        //simpleSwitch = findViewById(R.id.switch2);
        button = (Button) findViewById(R.id.buttonlol2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname= user_name.getText().toString();
                uroll = user_roll.getText().toString();
                uphone = user_phone.getText().toString();
                uemail = user_email.getText().toString();
                upass = user_pass.getText().toString();
                urepass = user_repass.getText().toString();
                signup();
            }
        });
        button3 = (Button)findViewById(R.id.button3);


       // Boolean switchState = simpleSwitch.isChecked();

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                login();
            }
        });
    }

    public boolean check()
    {
        boolean flag = true;

        if(!upass.equals(urepass))
        {
           flag = false;
            Toast.makeText(this, "Please check password !", Toast.LENGTH_SHORT).show();
        }
        return flag;
    }

    // check current state of a Switch (true or false).
    public void signup()
    {

        boolean proceed = check();
        if(proceed)
        {
            dbuse.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        if (uemail.equalsIgnoreCase(ds.getKey())) {
                            user_email.setText("");
                            Toast.makeText(act, "Username already exists!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    dbuse.child(uemail).setValue(new user("false",uname,upass,uphone,uroll,"student"));
                    Toast.makeText(act, "Registration successful!", Toast.LENGTH_SHORT).show();
                    login();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            Log.d("check",uname+uroll+uphone+uemail+upass+urepass);
        }
        else
        {
            user_pass.setText("");
            user_repass.setText("");

        }

    }
    public void login()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
