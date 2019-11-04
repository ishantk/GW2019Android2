package com.auribises.gw2019android2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyContactsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //ListView listView;
    GridView listView;
    EditText eTxtSearch;

    ArrayList<Contact> contacts;

    ContactsAdapter contactsAdapter;

    void initViews(){
        listView = findViewById(R.id.listView);
        eTxtSearch = findViewById(R.id.editTextSearch);

        eTxtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String data = charSequence.toString();
                //Toast.makeText(MyContactsActivity.this, "Data: "+data, Toast.LENGTH_LONG).show();
                contactsAdapter.filter(data);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        contacts = new ArrayList<>();

        Contact c1 = new Contact(R.drawable.category, "John", "+91 99999 12888");
        Contact c2 = new Contact(R.drawable.contact, "Fionna", "+91 98765 83488");
        Contact c3 = new Contact(R.drawable.pb, "Mike", "+91 88754 88812");
        Contact c4 = new Contact(R.drawable.music, "Leo", "+91 76784 12888");
        Contact c5 = new Contact(R.drawable.todo, "Kim", "+91 67890 34588");
        Contact c6 = new Contact(R.drawable.pb, "Mike", "+91 88754 88812");
        Contact c7 = new Contact(R.drawable.music, "Leo", "+91 76784 12888");
        Contact c8 = new Contact(R.drawable.todo, "Kim", "+91 67890 34588");

        contacts.add(c1); // 0
        contacts.add(c2);
        contacts.add(c3);
        contacts.add(c4);
        contacts.add(c5); // 4
        contacts.add(c6);
        contacts.add(c7);
        contacts.add(c8);

        //contactsAdapter = new ContactsAdapter(this, R.layout.list_item, contacts);
        contactsAdapter = new ContactsAdapter(this, R.layout.grid_item, contacts);
        listView.setAdapter(contactsAdapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contacts);

        getSupportActionBar().setTitle("My Contacts");

        initViews();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Contact contact = contacts.get(position);

        Toast.makeText(this, "You Clicked on "+contact.name, Toast.LENGTH_SHORT).show();
    }
}
