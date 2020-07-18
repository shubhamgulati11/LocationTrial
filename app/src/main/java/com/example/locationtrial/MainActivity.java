package com.example.locationtrial;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    Button btn,btn2;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Location mCurrentLocation;
//    private FusedLocationProviderClient mFusedLocationClient;
    private static final long INTERVAL = 1000 * 10;
    double wayLatitude = 0.0, wayLongitude = 0.0;
    private static final long FASTEST_INTERVAL = 1000 * 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("TAG","ON Create");
        btn=findViewById(R.id.btn);
        btn2=findViewById(R.id.btn2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    Log.e("TAG","Permission");
                }
                Log.e("TAG","IN Button");
                FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
                fusedLocationProviderClient.getLastLocation();
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        task.addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if(location!=null) {
                                    Log.e("TAG", "" + location.getLatitude());
                                    Toast.makeText(MainActivity.this, ""+location.getLatitude(), Toast.LENGTH_SHORT).show();

                                }
                            }

                        });
                    }
                });
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });

    }




}
