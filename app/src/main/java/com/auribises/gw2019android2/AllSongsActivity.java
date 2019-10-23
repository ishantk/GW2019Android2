package com.auribises.gw2019android2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.EnvironmentCompat;

import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

public class AllSongsActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_songs);

        getSupportActionBar().setTitle("All Songs");

        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        // To extract Path of SD Card
        String path = Environment.getExternalStorageDirectory().getPath();
        //String path = Environment.getDataDirectory().getAbsolutePath();
        //Toast.makeText(this, "Path is: "+path, Toast.LENGTH_SHORT).show();

        File file = new File(path);

        String[] files = file.list();

        if(files!=null) {
            for (String name : files) {
                adapter.add(name);
            }
        }else{
            Toast.makeText(this, "No Files Found at "+path, Toast.LENGTH_SHORT).show();
        }

        listView.setAdapter(adapter);
    }
}
