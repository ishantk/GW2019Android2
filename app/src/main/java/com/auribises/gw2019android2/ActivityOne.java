package com.auribises.gw2019android2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityOne extends AppCompatActivity implements View.OnClickListener {

    // Reference Variables to Views in our UI. name can be any name of your choice.
    EditText eTxtName, eTxtAge;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        getSupportActionBar().setTitle("Activity One");

        // findViewById will create Object and return its reference
        eTxtName = findViewById(R.id.editText);
        eTxtAge  = findViewById(R.id.editTextAge);

        btnSubmit = findViewById(R.id.buttonSubmit);

        btnSubmit.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Explcit Menu

        /*
        menu.add(1, 101, 0, "All Songs");
        menu.add(1, 201, 0, "Artists").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menu.add(1, 301, 0, "Favourites").setIcon(R.drawable.ic_app);*/

        // Implicit Menu
        getMenuInflater().inflate(R.menu.menu_activity_one, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        /*if(id == 101){
            Toast.makeText(this, "All Songs", Toast.LENGTH_SHORT).show();
        }*/

        if(id == R.id.allSongs){
            Toast.makeText(this, "All Songs", Toast.LENGTH_SHORT).show();

            // Explicit Intent
            //Intent intent = new Intent(ActivityOne.this, AllSongsActivity.class);

            // Implicit Intent
            Intent intent = new Intent("com.auribises.gw2019android2.allsongsactivity");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.buttonSubmit){

//            String name = eTxtName.getText().toString();
//            String age = eTxtAge.getText().toString();

            //int iAge = Integer.parseInt(age);

            // 1. Forward Passing -> We pass data directly in Intent

            // Intent is an Object which holds information of source to destination
//            Intent intent = new Intent(ActivityOne.this, ActivityTwo.class);
//            intent.putExtra("keyName", name);
//            intent.putExtra("keyAge", age);
//            startActivity(intent);

            // 2. Forward Passing -> We pass data in Bundle and Bundle in Intent

//            Intent intent = new Intent(ActivityOne.this, ActivityTwo.class);
//
//            Bundle bundle = new Bundle();
//            bundle.putString(Util.KEY_NAME, name);
//            bundle.putString("keyAge", age);
//            bundle.putInt("keySalary", 20000);
//
//            intent.putExtra("keyBundle", bundle);
//
//            startActivity(intent);


            // 3. Forward Passing -> We pass data in User Object which must be Serializable
//            User user = new User();
//            user.name = eTxtName.getText().toString();
//            user.age = Integer.parseInt(eTxtAge.getText().toString());
//            user.salary = 30000;
//
//            Intent intent = new Intent(ActivityOne.this, ActivityTwo.class);
//            intent.putExtra(Util.KEY_USER,user);
//            startActivity(intent);


            Intent intent = new Intent(ActivityOne.this, ActivityTwo.class);
            startActivityForResult(intent, 101);


        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101 && resultCode == 201) {

            String name = data.getStringExtra("keyName");

            String[] strArr = name.split(" ");

            eTxtName.setText(strArr[0]);
            eTxtAge.setText(strArr[1]);

        }
    }
}
