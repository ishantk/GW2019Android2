package com.auribises.gw2019android2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityTwo extends AppCompatActivity implements View.OnClickListener {

    // Reference Variables to Views in our UI. name can be any name of your choice.
    EditText eTxtName;
    Button btnGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two); // Binding Layout

        getSupportActionBar().setTitle("Activity Two");

        eTxtName = findViewById(R.id.editText2);
        btnGoBack = findViewById(R.id.button4);
        btnGoBack.setOnClickListener(this);


        Intent rcv = getIntent();
        String name = rcv.getStringExtra("keyName");
        eTxtName.setText(name);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button4){
            finish(); // Kill the current Activity
        }
    }
}
