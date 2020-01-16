package com.auribises.gw2019android2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.auribises.gw2019android2.model.Customer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddCustomerActivity extends AppCompatActivity implements View.OnClickListener {


    TextView txtTitle;

    EditText eTxtName;
    EditText eTxtPhone;
    EditText eTxtEmail;

    Button btnAddCustomer;

    Customer customer;
    FirebaseFirestore db;

    ProgressDialog progressDialog;

    boolean updateMode;

    void initViews(){

        txtTitle = findViewById(R.id.textViewTitle);

        eTxtName = findViewById(R.id.editTextName);
        eTxtPhone = findViewById(R.id.editTextPhone);
        eTxtEmail = findViewById(R.id.editTextEmail);

        btnAddCustomer = findViewById(R.id.buttonAdd);
        btnAddCustomer.setOnClickListener(this);

        customer = new Customer();
        db = FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        Intent rcv = getIntent();
        updateMode = rcv.hasExtra("keyCustomer");

        if(updateMode){

            customer = (Customer)rcv.getSerializableExtra("keyCustomer");

            txtTitle.setText("Update Customer Details");
            btnAddCustomer.setText("Update Customer");
            eTxtName.setText(customer.name);
            eTxtPhone.setText(customer.phone);
            eTxtEmail.setText(customer.email);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(!updateMode) {
            menu.add(1, 101, 0, "All Customers");
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==101){
            Intent intent = new Intent(AddCustomerActivity.this, AllCustomersActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    void clearFields(){
        eTxtName.setText("");
        eTxtPhone.setText("");
        eTxtEmail.setText("");
    }

    @Override
    public void onClick(View view) {

        progressDialog.show();

        customer.name = eTxtName.getText().toString();
        customer.phone = eTxtPhone.getText().toString();
        customer.email = eTxtEmail.getText().toString();
        //Toast.makeText(this, customer.toString(), Toast.LENGTH_SHORT).show();


        db.collection("customers")
                .document(customer.email)
                .set(customer)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isComplete()){
                            if(!updateMode) {
                                Toast.makeText(AddCustomerActivity.this, "Customer Saved", Toast.LENGTH_SHORT).show();
                                clearFields();
                            }else{
                                Toast.makeText(AddCustomerActivity.this, "Customer Updated", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            progressDialog.dismiss();
                        }
                    }
                });

    }
}
