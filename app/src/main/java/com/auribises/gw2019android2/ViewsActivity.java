package com.auribises.gw2019android2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class ViewsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    CheckBox cbC, cbJava, cbPython;
    RadioButton rbMale, rbFemale;

    Spinner spinner;
    ArrayAdapter<String> adapter;

    EditText eTxtName;

    RatingBar ratingBar;

    Button btnSubmit;

    Student student;


    // initViews can be any name of your choice
    void initViews(){

        student = new Student();

        cbC = findViewById(R.id.checkBoxC);
        cbJava = findViewById(R.id.checkBoxJava);
        cbPython = findViewById(R.id.checkBoxPython);

        rbMale = findViewById(R.id.radioButtonMale);
        rbFemale = findViewById(R.id.radioButtonFemale);

        spinner = findViewById(R.id.spinnerCity);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        adapter.add("==Select City=="); //0
        adapter.add("Ludhiana");
        adapter.add("Chandigarh");
        adapter.add("Delhi");
        adapter.add("Bangalore");
        adapter.add("Chennai");         // n-1

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0) {
                    student.city = adapter.getItem(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ratingBar = findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                student.rating = v;
            }
        });

        eTxtName = findViewById(R.id.editTextName);

        btnSubmit = findViewById(R.id.buttonSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student.name = eTxtName.getText().toString();


                if(student.validate()) {
                    Toast.makeText(ViewsActivity.this, "Student: " + student, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ViewsActivity.this, "Enter Details First", Toast.LENGTH_LONG).show();
                }

            }
        });

        cbC.setOnCheckedChangeListener(this);
        cbJava.setOnCheckedChangeListener(this);
        cbPython.setOnCheckedChangeListener(this);
        rbMale.setOnCheckedChangeListener(this);
        rbFemale.setOnCheckedChangeListener(this);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_views);

        initViews();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        int id = compoundButton.getId();

        switch (id){
            case R.id.checkBoxC:
                student.ccpp = cbC.isChecked();
                break;

            case R.id.checkBoxJava:
                student.java = cbJava.isChecked();
                break;

            case R.id.checkBoxPython:
                student.python = cbPython.isChecked();
                break;

            case R.id.radioButtonMale:
                student.gender = "Male";
                break;

            case R.id.radioButtonFemale:
                student.gender = "Female";
                break;

        }

    }
}
