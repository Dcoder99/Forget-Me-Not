package com.example.mlabsystem2.setup_2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditDetailsActivity extends AppCompatActivity {

    public EditText Name, PhNumber,Address;
    private String getname,getphnumber,getaddress;

    FloatingActionButton fabSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        fabSubmit = (FloatingActionButton) findViewById(R.id.fabSubmit);

        Name = (EditText) findViewById(R.id.editName);
        PhNumber = (EditText) findViewById(R.id.editPhNumber);
        Address = (EditText) findViewById(R.id.editAddress);


    }

    @Override
    protected void onStart() {
        super.onStart();
        fabSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getname=Name.getText().toString();
                Toast.makeText(getApplicationContext(),getname,Toast.LENGTH_SHORT).show();
                getphnumber=PhNumber.getText().toString();
                getaddress=Address.getText().toString();

                // To save the data
                SharedPreferences prefs= getSharedPreferences("MyData",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=prefs.edit();
                editor.putString("name",getname);
                editor.putString("phnumber", getphnumber);
                editor.putString("address",getaddress);
                editor.commit();
//                Intent intent= new Intent(getApplicationContext(),PatientProfileActivity.class);
//                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Saved Changes",Toast.LENGTH_LONG).show();
            }
        });

    }
}
