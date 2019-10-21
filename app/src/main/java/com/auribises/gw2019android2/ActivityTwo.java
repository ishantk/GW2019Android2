package com.auribises.gw2019android2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity implements View.OnClickListener {

    // Reference Variables to Views in our UI. name can be any name of your choice.
    EditText eTxtName;
    Button btnGoBack;

    TextView txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two); // Binding Layout

        getSupportActionBar().setTitle("Activity Two");

        eTxtName = findViewById(R.id.editText2);
        txtData =  findViewById(R.id.textViewData);

        btnGoBack = findViewById(R.id.button4);
        btnGoBack.setOnClickListener(this);


        //Intent rcv = getIntent();
//        String name = rcv.getStringExtra("keyName");
//        String age = rcv.getStringExtra("keyAge");

//        Bundle rcvBun = rcv.getBundleExtra("keyBundle");
//        String name = rcvBun.getString(Util.KEY_NAME);
//        String age = rcvBun.getString("keyAge");
//        int salary = rcvBun.getInt("keySalary");

        //User user = (User)rcv.getSerializableExtra(Util.KEY_USER);

        //eTxtName.setText(user.name);
        //txtData.setText(user.age+" | "+user.salary);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button4){

            String name = eTxtName.getText().toString();

            Intent data = new Intent(); // Contains no source, no destination
            data.putExtra("keyName", name);

            setResult(201, data);


            finish(); // Kill the current Activity
        }
    }
}
