package com.example.mlabsystem2.setup_2;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class PatientProfileNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_profile_new);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myToolbar.setBackgroundColor(getResources().getColor(R.color.darkBlue));
        myToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.White), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void launchTrackLocation(View view) {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }

    public void launchScheduleActivity(View view) {
        Intent intent = new Intent(this, ScheduleMain.class);
        startActivity(intent);
    }

    public void launchEmergencyContacts(View view) {
        Intent intent = new Intent(this, EditContacts.class);
        startActivity(intent);
    }

    public void launchNewsFeedActivity(View view) {
        Intent newfeedi = new Intent(this, EditInterests.class);
        startActivity(newfeedi);
    }

    public void launchMusicPrefsActivity(View view) {
//        Intent newfeedi = new Intent(this, MusicPreferences.class);
//        startActivity(newfeedi);
    }
}
