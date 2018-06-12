package com.example.mlabsystem2.maptest2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class MapActivity extends AppCompatActivity {
    private static final String TAG = "MapActivity";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static float DEFAULT_ZOOM = 15f;

    private Boolean myLocationPermissionGranted = false;
    private GoogleMap myMap;
    private FusedLocationProviderClient myFusedLocationProviderClient;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getLocationPermission();
    }


    private void getDeviceLocation() {
        Log.d(TAG, "getting the device's current location");
        myFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (myLocationPermissionGranted) {
                final Task location = myFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: found location");
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM);


                        } else {
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(MapActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: Security exception:" + e.getMessage());
        }


    }


    private void moveCamera(LatLng latlng, float zoom) {
        Log.d(TAG, "moveCamera: lat: " + latlng.latitude + "long: " + latlng.longitude);
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, zoom));

    }

    private void initMap() {

        Log.d(TAG, "onMapReady: initialising map");
        SupportMapFragment mapfragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapfragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d(TAG, "onMapReady: map is ready");
                // Toast.makeText(this, "map is ready", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "map is ready", Toast.LENGTH_LONG).show();
                myMap = googleMap;
                if (myLocationPermissionGranted) {
                    getDeviceLocation();

                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    myMap.setMyLocationEnabled(true);
                }

            }
        });
    }

    private void getLocationPermission(){
        Log.d(TAG,"onMapReady: getting location permissions");

        String permissions[]={Manifest.permission.ACCESS_COARSE_LOCATION};//Manifest.permission.ACCESS_FINE_LOCATION;
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                myLocationPermissionGranted= true;
                initMap();
            }

            else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        }
        else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG,"onRequestPermissionsResult: called");

        myLocationPermissionGranted=false;

        switch (requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                for(int i=0; i<grantResults.length;i++){
                    if(grantResults[i] !=PackageManager.PERMISSION_GRANTED){
                        Log.d(TAG,"onRequestPermissionsResult: initialising map");

                        myLocationPermissionGranted=false;
                        return;

                    }
                }
                myLocationPermissionGranted =true;
                Log.d(TAG,"onRequestPermissionsResult: permission granted");

                initMap();

                }
            }
        }
    }


