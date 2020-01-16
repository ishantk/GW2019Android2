package com.auribises.gw2019android2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.auribises.gw2019android2.model.Customer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllCustomersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    FirebaseFirestore db;

    ArrayAdapter<String> adapter;
    ArrayList<Customer> customers;

    ProgressDialog progressDialog;

    Customer customer;

    void initViews(){
        listView = findViewById(R.id.listView);
        db = FirebaseFirestore.getInstance();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        customers = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
    }

    void fetchAllCustomers(){
        progressDialog.show();
        db.collection("customers")
                .get()
                .addOnCompleteListener(this, new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isComplete()){
                            QuerySnapshot snapshots = task.getResult();
                            List<DocumentSnapshot> list = snapshots.getDocuments();

                            for(DocumentSnapshot documentSnapshot : list){
                                Customer customer = documentSnapshot.toObject(Customer.class);
                                customers.add(customer);
                                Log.i("AllCustomers",customer.toString());
                                adapter.add(customer.name+"\n"+customer.phone+"\n"+customer.email);
                            }

                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(AllCustomersActivity.this);
                            progressDialog.dismiss();

                        }
                    }
                });
    }

    void viewCustomer(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Details of "+customer.name);
        builder.setMessage(customer.toString());
        builder.setPositiveButton("Done", null);
        builder.create().show();
    }

    void deleteCustomer(){
        progressDialog.show();
        db.collection("customers")
                .document(customer.email)
                .delete()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isComplete()){
                            adapter.remove(customer.name+"\n"+customer.phone+"\n"+customer.email);
                            customers.remove(customer);
                            adapter.notifyDataSetChanged();
                            progressDialog.dismiss();
                            Toast.makeText(AllCustomersActivity.this, customer.name+" Deleted !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    void askForDeletion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+customer.name);
        builder.setMessage("Are you sure to Delete ?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteCustomer();
            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.create().show();
    }

    void askForOptions(){
        String[] options = {"View", "Update", "Delete", "Call", "Email"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                        viewCustomer();
                        break;

                    case 1:
                        Intent intent = new Intent(AllCustomersActivity.this, AddCustomerActivity.class);
                        intent.putExtra("keyCustomer",customer);
                        startActivity(intent);
                        break;

                    case 2:
                        askForDeletion();
                        break;

                    case 3:
//                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:"+customer.phone));
                        startActivity(callIntent);
                        break;

                    case 4:
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        //...
                        startActivity(emailIntent);
                        break;
                }
            }
        });

        builder.create().show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_customers);
        initViews();
        fetchAllCustomers();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        customer = customers.get(i);
        //Toast.makeText(this, customer.toString(), Toast.LENGTH_SHORT).show();
        askForOptions();
    }
}
