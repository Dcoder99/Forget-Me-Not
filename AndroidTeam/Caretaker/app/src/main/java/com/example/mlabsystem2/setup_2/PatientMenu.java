package com.example.mlabsystem2.setup_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientMenu extends AppCompatActivity {

    FirebaseFirestore db;
    String uid, patient_uid, p_name;

    LinearLayout ll;
    Button add_patient;
    TextView patient_name;

    int SCAN_BARCODE = 1021;
    String TAG = "MEEEE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientmenu);

        ll = findViewById(R.id.patient_1);
        add_patient = findViewById(R.id.add_patient);
        patient_name = findViewById(R.id.patient_name);

        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        uid = prefs.getString("patient_uid", "");
        patient_uid = prefs.getString("patient_uid", "");
        p_name = prefs.getString("patient_name", "");

        if (patient_uid.equals("")) {
            ll.setVisibility(ll.GONE);
            add_patient.setText(R.string.add_patient);
            addPatient();
        } else {
            ll.setVisibility(ll.VISIBLE);
            patient_name.setText(p_name);
            add_patient.setText(R.string.change_patient);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }

    public void SelectProfile(View view) {

        Intent intent = new Intent(getApplicationContext(), PatientProfile.class);
        startActivity(intent);
    }

    private void addPatient() {
        Intent bc_scanner = new Intent(this, BarCodeScanner.class);
        startActivityForResult(bc_scanner, SCAN_BARCODE);
    }

    public void addPatient(View view) {
        addPatient();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data.getBooleanExtra("isModified", false)) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }
}
