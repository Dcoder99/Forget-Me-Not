package com.example.mlabsystem2.dialerfinal;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Source;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.Intent.ACTION_CALL;
import static android.content.Intent.ACTION_DIAL;

public class Emergency extends AppCompatActivity implements View.OnClickListener {


    String TAG = "MEEEE";
    FirebaseFirestore db;
    String uid;
    public static ArrayList<String> emNumbers = new ArrayList<>();
    Button em1, em2, em3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, 1);
        }

        em1 = findViewById(R.id.em1);
        em2 = findViewById(R.id.em2);
        em3 = findViewById(R.id.em3);

        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);


        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        uid = prefs.getString("uid", "");

        Log.d(TAG, "Uid: " + uid);

        loadContactList();

        em1.setOnClickListener(this);
        em2.setOnClickListener(this);
        em3.setOnClickListener(this);
    }

    private void loadContactList() {
        final DocumentReference docRef = db.collection("Patients").document(uid);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, "Current data: " + snapshot.getData());
                    emNumbers = new ArrayList<>();
                    Map<String, Object> contacts = (Map<String, Object>) snapshot.get("EmergencyContacts");
                    Log.d("MEEEE", "onComplete: " + contacts);
                    if (contacts != null) {
                        emNumbers.add(contacts.get("num1").toString());
                        emNumbers.add(contacts.get("num2").toString());
                        emNumbers.add(contacts.get("num3").toString());
                    }

                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });
    }

//    private void loadContacts() {
//        loadContacts(true);
//    }
//
//    private void loadContacts(final boolean fromCache) {
//
//        //final Source source = (fromCache) ? Source.CACHE : Source.DEFAULT;
//
//        final DocumentReference docRef = db.collection("Patients").document(uid);
//
//        docRef.get(Source.DEFAULT)
//                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if (task.isSuccessful()) {
//                            DocumentSnapshot document = task.getResult();
//                            if (document.exists()) {
//                                Log.d("MEEEEEE", "DocumentSnapshot data: " + document.getData());
//
//                                Map<String, Object> contacts = (Map<String, Object>) document.get("EmergencyContacts");
//                                Log.d("MEEEE", "onComplete: " + contacts);
//                                //if (contacts != null) {
//                                emNumbers.add(contacts.get("num1").toString());
//                                emNumbers.add(contacts.get("num2").toString());
//                                emNumbers.add(contacts.get("num3").toString());
//                                //}
//                            } else {
//                                if (fromCache) {
//                                    loadContacts(false);
//                                }
//                                Log.d("MEEEEEE", "No such document");
//                            }
//
//                        }
//                    }
//                });
//    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: " + emNumbers + "..." + v.getId());
        switch (v.getId()) {
            case R.id.em1: {
                Log.d(TAG, emNumbers.get(0));
                Intent call1 = new Intent(Intent.ACTION_CALL);
                //call1.setAction(ACTION_CALL);
                call1.setData(Uri.parse("tel:" + emNumbers.get(0)));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, 1);
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                }
                startActivity(call1);
            }
            break;
            case R.id.em2: {
                Log.d(TAG, emNumbers.get(1));
                Intent call2 = new Intent(Intent.ACTION_CALL);
                //Intent call1 = new Intent();
                call2.setAction(ACTION_CALL);
                call2.setData(Uri.parse("tel:" + emNumbers.get(1)));
                startActivity(call2);
            }
            break;
            case R.id.em3: {
                Log.d(TAG, emNumbers.get(2));
                Intent call3 = new Intent(Intent.ACTION_CALL);
                //Intent call1 = new Intent();
                call3.setAction(ACTION_CALL);
                call3.setData(Uri.parse("tel:" + emNumbers.get(2)));
                startActivity(call3);
            }
            break;
        }

    }
}