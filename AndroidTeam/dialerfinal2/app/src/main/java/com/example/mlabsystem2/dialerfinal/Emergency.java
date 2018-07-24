package com.example.mlabsystem2.dialerfinal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.awt.Button;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.text.View;

import static com.example.mlabsystem2.dialerfinal.R.*;

public class Emergency extends AppCompatActivity {
    public FirebaseFirestore db;
    ArrayList<String> emnumbers = new ArrayList();
    String uid = "rfnChvubEvalqj8i4aFCevYmdBo1";
    Button em1,em2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        loadContacts();


    }

    public void loadContacts() {
        loadContacts(true);
    }

    public void loadContacts(final boolean fromCache) {

        final Source source = (fromCache) ? Source.CACHE : Source.DEFAULT;

        final DocumentReference docRef = db.collection("Users").document(uid);

        docRef.get(source).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("MEEEEEE", "DocumentSnapshot data: " + document.getData());

                        Map<String, Object> contacts = (Map<String, Object>) document.get("EmergencyContacts");
                        Log.d("MEEEE", "onComplete: " + contacts);
                        if (contacts != null) {

                            emnumbers.add(contacts.get("num1").toString());

                            emnumbers.add(contacts.get("num2").toString());

                            emnumbers.add(contacts.get("num3").toString());
                        }
                    }
                }
            }
        });
    }
}
