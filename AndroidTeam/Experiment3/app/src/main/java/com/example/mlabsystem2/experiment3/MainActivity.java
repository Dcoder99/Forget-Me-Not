package com.example.mlabsystem2.experiment3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonStart, bindser;
    private TextView res;
    public Intent serviceIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart=(Button)findViewById(R.id.startservicee);
        bindser= (Button)findViewById(R.id.bind);
        res=(TextView)findViewById(R.id.result);


        serviceIntent=new Intent(MainActivity.this,MyService.class);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(serviceIntent);
            }
        });
    }











}
