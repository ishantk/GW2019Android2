package com.auribises.gw2019android2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.auribises.gw2019android2.model.Customer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddCustomerActivity extends AppCompatActivity implements View.OnClickListener {


    EditText eTxtName;
    EditText eTxtPhone;
    EditText eTxtEmail;

    Button btnAddCustomer;

    Customer customer;
    FirebaseFirestore db;

    ProgressDialog progressDialog;

    void initViews(){
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        initViews();
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
                            Toast.makeText(AddCustomerActivity.this, "Customer Saved", Toast.LENGTH_SHORT).show();
                            clearFields();
                            progressDialog.dismiss();
                        }
                    }
                });

    }
}
