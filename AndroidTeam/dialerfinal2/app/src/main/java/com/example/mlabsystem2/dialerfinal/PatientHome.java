package com.example.mlabsystem2.dialerfinal;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.ArrayList;
import java.util.Map;

public class PatientHome extends AppCompatActivity implements View.OnClickListener {
    CardView cd, callcard;
    FirebaseFirestore db;
    TextView patientName;
    String uid;
    ArrayList<String>addresses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        patientName =findViewById(R.id.patient_name);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        }
        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        uid = prefs.getString("uid", "");
        final DocumentReference docRef = db.collection("Patients").document(uid);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {


            public static final String TAG ="Mee" ;

            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, "Current data: " + snapshot.getData());
                    addresses = new ArrayList<>();
                    Map<String, Object> details = (Map<String, Object>) snapshot.get("Details");
                    Log.d("MEEEE", "onComplete: " + details);
                    if (details != null) {
                        addresses.add(details.get("Name").toString());
                        patientName.setText(addresses.get(0));
                    }

                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });





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
            }break;
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

    public void generateQRcode(View view) {
        Intent intent = new Intent(this,QRcode.class);
        startActivity(intent);
    }
}
