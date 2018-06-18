package com.example.mlabsystem2.maptest2;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOGUE_REQUEST=9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isServicesOK()){
            init();
        }



    }

    private void init(){
        Button bmap=(Button)findViewById(R.id.btnMap);
        bmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });


    }





    //to check if user has correct version of GPlayServices
    public boolean isServicesOK(){
        Log.d(TAG,"isServicesOK:checking google sevices version");
        int available= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available== ConnectionResult.SUCCESS){
            Log.d(TAG,"isServicesOK:works");
            return true;
        }

        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.d(TAG,"isServicesOK:error has ocured but we can fix it");
            Dialog dialog=GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,available,ERROR_DIALOGUE_REQUEST);
            dialog.show();
        }

        else{
            Toast.makeText(this,"You cant make map requests",Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}
