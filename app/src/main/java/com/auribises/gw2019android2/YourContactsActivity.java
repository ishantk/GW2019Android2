package com.auribises.gw2019android2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

// https://developer.android.com/topic/libraries/support-library/packages

public class YourContactsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Contact> contacts;

    YourContactsAdapter adapter;


    void initViews(){

        recyclerView = findViewById(R.id.recyclerView);

        contacts = new ArrayList<>();

        Contact c1 = new Contact(R.drawable.category, "John", "+91 99999 12888");
        Contact c2 = new Contact(R.drawable.contact, "Fionna", "+91 98765 83488");
        Contact c3 = new Contact(R.drawable.pb, "Mike", "+91 88754 88812");
        Contact c4 = new Contact(R.drawable.music, "Leo", "+91 76784 12888");
        Contact c5 = new Contact(R.drawable.todo, "Kim", "+91 67890 34588");

        contacts.add(c1); // 0
        contacts.add(c2);
        contacts.add(c3);
        contacts.add(c4);
        contacts.add(c5); // 4

        adapter = new YourContactsAdapter(this, R.layout.list_item, contacts);

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(linearLayoutManager); // ListView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager); // GridView

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_contacts);
        initViews();
    }
}
