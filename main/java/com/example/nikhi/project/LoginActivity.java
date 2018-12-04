package com.example.nikhi.project;

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

public class LoginActivity extends AppCompatActivity {
    public DatabaseReference dbuser;
    public Button login;
    public EditText login_edit;
    public EditText pass;
    public static String user;
    public static String type;
    public static String name;
    public static String roll;
    public static String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("start","start");
        dbuser = FirebaseDatabase.getInstance().getReference("users");
        setContentView(R.layout.activity_login);
        login_edit = (EditText) findViewById(R.id.editText2);
        pass = (EditText) findViewById(R.id.editText5);
        login = (Button) findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                login();
            }
        });
//        dbuser.addValueEventListener(new ValueEventListener() {
//         @Override
//         public void onDataChange(DataSnapshot dataSnapshot) {
//             for (DataSnapshot ds : dataSnapshot.getChildren()){
//                 if (emailid.equalsIgnoreCase(ds.getValue().toString())){
//                     Log.d("email exist","");
//                     if (password.equals(ds.child("password").getValue().toString())){
//                         Log.d("pass correct","");
//                         if("doctor".equals(ds.child("type").getValue().toString()))
//                         {
//                             Log.d("student","");
//                             Intent intent = new Intent(getApplicationContext(), Doctor_profile.class);
//                             startActivity(intent);
//                         }
//                         else
//                         {
//                             Log.d("doctor","");
//                             Intent intent = new Intent(getApplicationContext(), Profile.class);
//                             startActivity(intent);
//                         }
//                     }else{
//                         //Password is wrong
//                         Log.d("pass wrong","");
//                         return;
//                     }
//                 }
//                 Log.d("email not exist","");
//                 //UserID doesnt exist
//             }
//         }
//        ArrayList<String> users = new ArrayList<String>();
//        ArrayList<String> password = new ArrayList<String>();
//        dbuser = FirebaseDatabase.getInstance().getReference("users");
//        login = (Button) findViewById(R.id.button);
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("button press","");
//                login(v);
//            }
//        });
        //            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                //Error dialogue pop up
//            }
//        });

    }
    public void signup(View view)
    {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void login(){
        Log.d("in login","");
        final String emailid = login_edit.getText().toString();
        final String password = pass.getText().toString();

        dbuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean trig = true;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if (ds.getKey().toString().equalsIgnoreCase(emailid)){
                        if (ds.child("password").getValue().toString().equals(password)){
                            user = ds.getKey().toString();
                            type = ds.child("type").getValue().toString();
                            name = ds.child("name").getValue().toString();
                            roll = ds.child("roll").getValue().toString();
                            phone = ds.child("").getValue().toString();
                            if (ds.child("type").getValue().toString().equals("doctor")){
                                Toast.makeText(getApplicationContext(),"Welcome doctor!",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Doctor_profile.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(),"Welcome student!",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Profile.class);
                                startActivity(intent);
                                finish();
                            }
                            trig = false;
                            break;
                        }else{
                            Toast.makeText(getApplicationContext(),"Wrong password!",Toast.LENGTH_SHORT).show();
                            trig = false;
                            break;
                        }
                    }
                }
                if(trig)
                    Toast.makeText(getApplicationContext(),"Username doesn't exist..!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Cancelled",Toast.LENGTH_SHORT);
            }
        });
}

}
