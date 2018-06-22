package com.example.mlabsystem2.dialerfinal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CardView cd, callcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cd = (CardView) findViewById(R.id.card3);
        callcard = (CardView) findViewById(R.id.callcard);
        cd.setOnClickListener(this);
        callcard.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.card3: {
                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
            }
            case R.id.callcard: {
                Intent intent1 = new Intent(this, Dialerfinal.class);
                startActivity(intent1);
            }

        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
