package com.auribises.gw2019android2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MyContactsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Contact> contacts;
    ContactsAdapter contactsAdapter;

    void initViews(){
        listView = findViewById(R.id.listView);
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

        contactsAdapter = new ContactsAdapter(this, R.layout.list_item, contacts);
        listView.setAdapter(contactsAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contacts);
        initViews();
    }
}
