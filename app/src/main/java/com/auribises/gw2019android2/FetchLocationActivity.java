package com.auribises.gw2019android2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.auribises.gw2019android2.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.security.Permission;
import java.security.Permissions;
import java.util.List;

public class FetchLocationActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtLocation;
    Button btnFetch;

    FusedLocationProviderClient fusedLocationProviderClient;

    LocationRequest locationRequest;
    LocationCallback locationCallback;


    void initViews(){
        txtLocation = findViewById(R.id.textViewLocation);
        btnFetch = findViewById(R.id.buttonFetch);

        btnFetch.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_location);
        initViews();
    }

    void fetchLocations(){

        locationRequest = new LocationRequest();
        locationRequest.setInterval(2000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback(){
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }

//                for (Location location : locationResult.getLocations()) {
//
//                }
                Location location = locationResult.getLocations().get(0);
                float speed = location.getSpeed(); // mtr/sec
                txtLocation.setText("Location: "+location.getLatitude()+" : "+location.getLongitude()+" : "+speed);

            };
        };

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    void fetchLastLocation(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(this, new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if(task.isComplete()){
                    Location location = task.getResult();
                    StringBuffer buffer = new StringBuffer();
                    if(location!=null){

                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();

                        try{

                            Geocoder geocoder = new Geocoder(FetchLocationActivity.this);
                            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

                            if(addresses!=null && addresses.size()>0) {
                                Address address = addresses.get(0);


                                for(int i=0;i<=address.getMaxAddressLineIndex();i++){
                                    buffer.append(address.getAddressLine(i)+"\n");
                                }

                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }


                        txtLocation.setText("Location: "+latitude+" : "+longitude+"\n"+buffer.toString());
                    }else{
                        txtLocation.setText("No Location Found");
                    }





                }
            }
        });
    }


    @Override
    public void onClick(View view) {

        //fetchLastLocation();
        fetchLocations();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(fusedLocationProviderClient!=null) {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        }
    }
}
