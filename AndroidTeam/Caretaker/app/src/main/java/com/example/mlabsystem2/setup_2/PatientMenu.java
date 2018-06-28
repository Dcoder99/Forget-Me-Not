package com.example.mlabsystem2.setup_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PatientMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientmenu);
    }

    public void SelectProfile(View view) {
        //func is executed on clicking the layout
        //this works only for one patient as of 28th June 2018
        Intent intent=new Intent(getApplicationContext(),PatientProfileActivity.class);
        startActivity(intent);

    }
}
