package com.auribises.gw2019android2;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// Refer : https://developer.android.com/guide/topics/sensors/sensors_overview

public class MySensorActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {

    // UI : TextView and Button

    TextView txtData;
    Button btnActivate;

    SensorManager sensorManager;
    Sensor sensor;

    void initSenor(){
        // Check if sensor is available or not :)
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    void initViews(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sensor);

        initViews();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float[] values = sensorEvent.values;

        float x = values[0];
        float y = values[1];
        float z = values[2];

        txtData.setText(x+" : "+y+" : "+z);

        float cal = ((x*x) + (y*y) + (z*z)) / (SensorManager.GRAVITY_EARTH *  SensorManager.GRAVITY_EARTH);

        if(cal > 3){
            txtData.setText(x+" : "+y+" : "+z+"\nDevice Shaken");
            sensorManager.unregisterListener(this);

            // Fetch Location

            // Once you will get the location -> Send it via SMS to your favourite Phone Number
        }

    }

    void sendSms(){
        SmsManager smsManager = SmsManager.getDefault();
        String phone = "+91 99155 71177";
        String message = "This is Message which will have location";
        smsManager.sendTextMessage(phone,null,message,null,null);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onClick(View view) {
        initSenor();
    }
}
