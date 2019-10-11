package com.auribises.gw2019android2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

// Object of Activity : Android System creates it
// We have no main function here
// Android System will execute the app for us
public class MainActivity extends AppCompatActivity { //Inheritance

    String TAG = "MainActivity";
    //String TAG = getClass().getSimpleName();

    // is executed automatically when object of activity is created
    // onCreate in Activity acts like Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Binding or Linking Layout of Activity
        setContentView(R.layout.activity_main);

        //System.out.println("==MainActivity onCreate=="); // Core Java

        Log.i(TAG,"==onCreate==");     // Logging

        //Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show(); // Message
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"==onStart==");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"==onResume==");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"==onPause==");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"==onStop==");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"==onDestroy==");
    }

    // clickHandler or view can be any name of Your Choice
    public void clickHandler(View view){
        // Navigating from One Activity to Other
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
