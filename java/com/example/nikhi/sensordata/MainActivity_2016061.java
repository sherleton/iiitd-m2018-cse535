package com.example.nikhi.sensordata;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity_2016061 extends AppCompatActivity implements SensorEventListener, View.OnClickListener {

    private SensorManager sensorManager;
    private Sensor gyroscope, accelerometer, proximity, magneto;
    private TextView ax, ay, az, gx, gy, gz, px, ox, onx, sd, lt, lg, oy, oz;
    private float Gx = 0f, Gy = 0f, Gz = 0f, Px = 0f, Lt = 0f, Lg = 0f, Ox = 0f, Oy = 0f, Oz = 0f;
    private String Sd = "NA", Onx = "False";
    private float afx = 0f, afy = 0f, afz = 0f;
    private Button click;
    private int flag = 0;
    private LocationManager locationManager;
    private float limit = 10f;
    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private float mx = 0f, my = 0f, mz = 0f;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2016061);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if(flag == 1) {
                    lt.setText("LT:" + location.getLatitude());
                    lg.setText("LG:" + location.getLongitude());
                    Lt = (float) location.getLatitude();
                    Lg = (float) location.getLongitude();

                    insertRecord("GPS", new float[]{Lt, Lg});
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

        click = findViewById(R.id.button);
        click.setOnClickListener(this);

        ax = findViewById(R.id.ax);
        ay = findViewById(R.id.ay);
        az = findViewById(R.id.az);
        gx = findViewById(R.id.gx);
        gy = findViewById(R.id.gy);
        gz = findViewById(R.id.gz);
        px = findViewById(R.id.px);
        ox = findViewById(R.id.ox);
        onx = findViewById(R.id.onx);
        sd = findViewById(R.id.sd);
        lt = findViewById(R.id.lt);
        lg = findViewById(R.id.lg);
        oy = findViewById(R.id.oy);
        oz = findViewById(R.id.oz);
        onx.setText("False");

        sqLiteOpenHelper = new Database_2016061(this);
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();

        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (gyroscope == null) {
            Toast.makeText(this, "No Gyroscope", Toast.LENGTH_SHORT).show();
            gx.setText("NA");
            gy.setText("NA");
            gz.setText("NA");
        }

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer == null) {
            Toast.makeText(this, "No Accelerometer", Toast.LENGTH_SHORT).show();
            ax.setText("NA");
            ay.setText("NA");
            az.setText("NA");
            sd.setText("No Shake");
        }

        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (proximity == null) {
            Toast.makeText(this, "No Proximity Sensor", Toast.LENGTH_SHORT).show();
            px.setText("NA");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}, 196);
                return;
            }
        }

        magneto = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE)
        {
            gx.setText("x:" + event.values[0]);
            gy.setText("y:" + event.values[1]);
            gz.setText("z:" + event.values[2]);

            Gx = event.values[0];
            Gy = event.values[1];
            Gz = event.values[2];

            float[] lf = new float[]{Gx, Gy, Gz};

            insertRecord("GYROSCOPE", lf);
        }
        else if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            ax.setText("x:" + event.values[0]);
            ay.setText("y:" + event.values[1]);
            az.setText("z:" + event.values[2]);

            if((event.values[0] - afx > limit && event.values[1] - afy > limit) || (event.values[0] - afx > limit && event.values[2] - afz > limit) || (event.values[1] - afy > limit && event.values[2] - afz > limit ))
            {
                sd.setText("Shake Detected");

                insertRecord("SHAKE_DETECTION", new float[]{0f});

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {

                    }
                }, 3000);
            }

            afx = event.values[0];
            afy = event.values[1];
            afz = event.values[2];

            sd.setText("NA");

            float[] lf = new float[]{afx, afy, afz};

            insertRecord("ACCELEROMETER", lf);
        }
        else if(event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            px.setText("" + event.values[0]);

            if(event.values[0] == 0) {
                onx.setText("True");

                float[] rm = new float[9];

                if(SensorManager.getRotationMatrix(rm, null, new float[]{afx, afy, afz}, new float[]{mx, my, mz})){
                    float[] bo = new float[3];
                    SensorManager.getOrientation(rm, bo);

                    if(bo[0] != 0f) {
                        insertRecord("NEAR_FACE", bo);
                        ox.setText("A:" + bo[0]);
                        oy.setText("P:" + bo[1]);
                        oz.setText("R:" + bo[2]);
                    }
                }
            }
            else
                onx.setText("False");

            float[] lf = new float[]{Px};
            Px = event.values[0];

            insertRecord("PROXIMITY", lf);
        }

        if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
        {
            mx = event.values[0];
            my = event.values[1];
            mz = event.values[2];
        }

        float[] rm = new float[9];

        if(SensorManager.getRotationMatrix(rm, null, new float[]{afx, afy, afz}, new float[]{mx, my, mz})){
            float[] bo = new float[3];
            SensorManager.getOrientation(rm, bo);

            insertRecord("ORIENTATION", bo);
            ox.setText("A:" + bo[0]);
            oy.setText("P:" + bo[1]);
            oz.setText("R:" + bo[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onClick(View v) {

        click.setText("Stop Collecting");

        if(flag == 0) {
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
            flag = 1;

            locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
        }
        else {
            sensorManager.unregisterListener(this);
            flag = 0;
            click.setText("Start Collecting");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case 196: if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        return;
                        else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}, 196);
                        return;
                    }
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putFloat("ax", afx);
        outState.putFloat("ay", afy);
        outState.putFloat("az", afz);
        outState.putFloat("gx", Gx);
        outState.putFloat("gy", Gy);
        outState.putFloat("gz", Gz);
        outState.putFloat("px", Px);
        outState.putFloat("lt", Lt);
        outState.putFloat("lg", Lg);
        outState.putFloat("ox", Ox);
        outState.putFloat("oy", Oy);
        outState.putFloat("oz", Oz);
        outState.putString("onx", Onx);
        outState.putString("sd", Sd);
        outState.putInt("flag", flag);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        afx = savedInstanceState.getFloat("ax");
        afy = savedInstanceState.getFloat("ay");
        afz = savedInstanceState.getFloat("az");
        Gx = savedInstanceState.getFloat("gx");
        Gy = savedInstanceState.getFloat("gy");
        Gz = savedInstanceState.getFloat("gz");
        Px = savedInstanceState.getFloat("px");
        Lt = savedInstanceState.getFloat("lt");
        Lg = savedInstanceState.getFloat("lg");
        Ox = savedInstanceState.getFloat("ox");
        Oy = savedInstanceState.getFloat("oy");
        Oz = savedInstanceState.getFloat("oz");
        Onx = savedInstanceState.getString("onx");
        Sd = savedInstanceState.getString("sd");
        flag = savedInstanceState.getInt("flag");

        gx.setText("x:" + Gx);
        gy.setText("y:" + Gy);
        gz.setText("z:" + Gz);
        ax.setText("x:" + afx);
        ay.setText("y:" + afy);
        az.setText("z:" + afz);
        px.setText("" + Px);
        lt.setText("LT:" + Lt);
        lg.setText("LG:" + Lg);
        ox.setText("A:" + Ox);
        oy.setText("P:" + Oy);
        oz.setText("R:" + Oz);
        onx.setText(Onx);
        sd.setText(Sd);

        if(flag == 1)
        {
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
            click.setText("Stop Collecting");
        }
        else
            click.setText("Start Collecting");
    }

    public void insertRecord(String TABLE, float[] lf)
    {
        ContentValues contentValues = new ContentValues();

        if(lf.length > 0)
        {
            if(lf.length == 1)
                contentValues.put("VALUE", lf[0]);
            else if(lf.length == 2)
            {
                contentValues.put("LATITUDE", lf[0]);
                contentValues.put("LONGITUDE", lf[1]);
            }
            else
            {
                if(TABLE.equals("ORIENTATION") || TABLE.equals("NEAR_FACE"))
                {
                    contentValues.put("AZIMUTH", lf[0]);
                    contentValues.put("PITCH", lf[1]);
                    contentValues.put("ROLL", lf[2]);
                }
                else {
                    contentValues.put("X_VALUE", lf[0]);
                    contentValues.put("Y_VALUE", lf[1]);
                    contentValues.put("Z_VALUE", lf[2]);
                }
            }
        }

        sqLiteDatabase.insert(TABLE, null, contentValues);
    }
}