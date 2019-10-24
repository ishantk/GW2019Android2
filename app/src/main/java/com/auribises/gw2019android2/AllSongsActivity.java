package com.auribises.gw2019android2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.EnvironmentCompat;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

public class AllSongsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    ArrayAdapter<String> adapter;

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_songs);

        getSupportActionBar().setTitle("All Songs");

        mediaPlayer = new MediaPlayer();

        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        // To extract Path of SD Card
        String path = Environment.getExternalStorageDirectory().getPath();
        //String path = Environment.getDataDirectory().getAbsolutePath();
        //Toast.makeText(this, "Path is: "+path, Toast.LENGTH_SHORT).show();

        //String path = "//mnt/sdcard";
        File file = new File(path);

        String[] files = file.list();

        /*
        File[] fileArr = file.listFiles();
        for(File f : fileArr){
            if(f.isDirectory()){

            }else{

            }
        }
        */


        if(files!=null) {
            for (String name : files) {
                if(name.endsWith(".mp3")) {
                    adapter.add(name);
                }
            }
        }else{
            Toast.makeText(this, "No Files Found at "+path, Toast.LENGTH_SHORT).show();
        }

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        String songName = adapter.getItem(i);
        String songPath = Environment.getExternalStorageDirectory().getPath()+"/"+songName;

        String songURL = "https://firebasestorage.googleapis.com/v0/b/nimble-perigee-501.appspot.com/o/Jugni.mp3?alt=media&token=6d08bf51-a745-45c3-a2dc-9f43bec344d7";

        try {
            //mediaPlayer.setDataSource(songPath);

            mediaPlayer.setDataSource(this, Uri.parse(songURL));

            mediaPlayer.prepare();
            mediaPlayer.start();
            //mediaPlayer.pause();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
