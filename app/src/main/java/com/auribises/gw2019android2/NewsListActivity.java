package com.auribises.gw2019android2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NewsListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ListView listView; // listView is a Reference Variable
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        getSupportActionBar().setTitle("News List");

        // Ref Var listView now points to the ListView i.e. UI
        // IOC : Inversion of Control. Android (findViewById) will create objects for us !!
        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adapter.add("Zee News"); //0
        adapter.add("Aaj Tak");  //1
        adapter.add("Republic"); //2
        adapter.add("BBC");      //3
        adapter.add("CNN IBN");  //4
        adapter.add("NDTV");     //5

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // i is position
        String newsTitle = adapter.getItem(i);
        Toast.makeText(this, "You Selected:"+newsTitle, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(NewsListActivity.this, NewsActivity.class);
        startActivity(intent);
    }
}


