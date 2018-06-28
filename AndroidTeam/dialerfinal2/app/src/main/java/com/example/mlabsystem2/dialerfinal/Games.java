package com.example.mlabsystem2.dialerfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Games extends AppCompatActivity implements View.OnClickListener {
CardView game1,game2,game3,game4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        game1=(CardView)findViewById(R.id.games1);
        game2=(CardView)findViewById(R.id.games2);
        game3=(CardView)findViewById(R.id.games3);
        game4=(CardView)findViewById(R.id.games4);
        game1.setOnClickListener(this);
        game2.setOnClickListener(this);
        game3.setOnClickListener(this);
        game4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.games1:{
                Intent games1=new Intent(this,Game1.class);
                startActivity(games1);
            }break;
            case R.id.games2:{
                Intent games2=new Intent(this,Game1.class);
                startActivity(games2);
            }break;
            case R.id.games3:{
                Intent games3=new Intent(this,Game1.class);
                startActivity(games3);
            }break;
            case R.id.games4:{
                Intent games4=new Intent(this,Game1.class);
                startActivity(games4);
            }break;
        }
    }
}
