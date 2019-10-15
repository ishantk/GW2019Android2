package com.auribises.gw2019android2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityOne extends AppCompatActivity implements View.OnClickListener {

    // Reference Variables to Views in our UI. name can be any name of your choice.
    EditText eTxtName;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        getSupportActionBar().setTitle("Activity One");

        // findViewById will create Object and return its reference
        eTxtName = findViewById(R.id.editText);
        btnSubmit = findViewById(R.id.buttonSubmit);

        btnSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.buttonSubmit){

            String name = eTxtName.getText().toString();

            Intent intent = new Intent(ActivityOne.this, ActivityTwo.class);
            intent.putExtra("keyName", name);
            startActivity(intent);
        }

    }
}
