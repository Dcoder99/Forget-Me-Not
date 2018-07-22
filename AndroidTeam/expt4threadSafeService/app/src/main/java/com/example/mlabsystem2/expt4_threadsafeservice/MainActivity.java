package com.example.mlabsystem2.expt4_threadsafeservice;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.BatchUpdateException;

//intent filter= unique string.not used in other code.
// channel for communication between broadcast and receiver


public class MainActivity extends AppCompatActivity {

    private Button start;
    private Button stop;
    private TextView message;

    public static final String MY_FILTER= "com.my.broadcast.Receiver"; //channel name.// can be anything unique
    public static final String MSG= "_message";

    private BroadcastReceiver myReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //context= knows who has sent message
            //intent is used to get values. bundles it.
            Bundle bundle = (Bundle) intent.getExtras();
            if(bundle!=null){
                Double aVal= bundle.getDouble(MSG); //savedInstanceStateince i is double
                Double bVal= bundle.getDouble(MSG);
                message.setText(Double.toString(aVal)+Double.toString(bVal));
            };
        }
    };
    //RECEIVING IS DONE HERE.


//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(myReceiver==null){
//            myReceiver= new BroadcastReceiver() {
//                @Override
//                public void onReceive(Context context, Intent intent) {
//                    message.append("\n" + intent.getExtras().get("coordinates"));
//
//                }
//            };
//        }
//        registerReceiver(myReceiver, new IntentFilter("location_update"));
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!runtime_permissions()){
            enable_buttons();
        }











        IntentFilter intentFilter= new IntentFilter(); //creating my own channel
        intentFilter.addAction(MY_FILTER); //this is my filter message
        registerReceiver(myReceiver,intentFilter); //at this point, my BR gets registered with my activity after text view is created.
        //BR starts working here.
        Intent intent = new Intent(MainActivity.this, MyService.class);
        startService(intent);

    }

    private void enable_buttons() {
        start = (Button) findViewById(R.id.startservice);
        stop = (Button) findViewById(R.id.stopservice);
        message = (TextView) findViewById(R.id.disp);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                startService(intent);
            }
        });


        //stop.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {

              //  Intent intent=new Intent(MainActivity.this,MyService.class);
                //stopService(intent);

         //   }
      //  });

    }

    private boolean runtime_permissions() {
        if(Build.VERSION.SDK_INT >=23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED&&
        ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},1234);
            return true;
        }
        return false;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1234){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                enable_buttons();
            }else {
                runtime_permissions();
            }
        }
    }



}
