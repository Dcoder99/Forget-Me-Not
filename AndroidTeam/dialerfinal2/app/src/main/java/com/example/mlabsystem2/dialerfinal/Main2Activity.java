package com.example.mlabsystem2.dialerfinal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
CardView cardfeeds,cardmaps,cardschedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        cardfeeds=(CardView)findViewById(R.id.cardfeeds);
        cardmaps=(CardView)findViewById(R.id.cardmaps);
        cardschedule=(CardView)findViewById(R.id.schedule);
        cardfeeds.setOnClickListener(this);
        cardmaps.setOnClickListener(this);
        cardschedule.setOnClickListener(this);

    }



   public void LaunchMap(View view) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=PESIT+Research+center, Hosur+Karnataka&mode=w");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cardfeeds:{
                Intent feed=new Intent(this,FeedActivity.class);
                startActivity(feed);
            }break;
            case R.id.cardmaps:{
                LaunchMap(v);
            }break;
            case R.id.schedule:{
                Intent schedule=new Intent(this,Schedulemain.class);
                startActivity(schedule);
            }
        }

    }
}