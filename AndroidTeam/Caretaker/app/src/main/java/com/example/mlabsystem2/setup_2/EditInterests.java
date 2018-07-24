package com.example.mlabsystem2.setup_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditInterests extends AppCompatActivity implements View.OnClickListener {
    EditText interest;
    Button setIntersts;
    ArrayList<String> interests = new ArrayList<String>();
    String singleInterest;
    FirebaseFirestore db;
    Button submit;
    String patient_uid;

    String TAG = "MEEEEEEEEEEE";

    private RecyclerViewAdapterInterests adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    Map<String, Object> interestsMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_interests);

        interest = findViewById(R.id.interest);
        setIntersts = findViewById(R.id.setinterests);
        setIntersts.setOnClickListener(this);
        submit = findViewById(R.id.submit);

        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        submit.setOnClickListener(this);

        recyclerView = findViewById(R.id.recycler_view1);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        patient_uid = prefs.getString("patient_uid", "");

        loadInterests();

        Log.d("MEEEEEEEE", patient_uid);
    }

    public void loadInterests(){
        loadInterests(true);
    }

    public void loadInterests(final boolean fromCache) {

        final Source source = (fromCache) ? Source.CACHE : Source.DEFAULT;

        DocumentReference docRef = db.collection("Patients").document(patient_uid);

        docRef.get(source).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("MEEEEEE", "DocumentSnapshot data: " + document.getData());
                        ArrayList<String> newInterests = (ArrayList<String>) document.get("Interests");
                        if(newInterests != null)
                        {
                            interests = newInterests;
                        }
                    } else {
                        if (fromCache) {
                            loadInterests(false);
                        }
                        Log.d("MEEEEEE", "No such document");
                    }
                } else {
                    Log.d("MEEEEEE", "get failed with ", task.getException());
                }
            }
        });
    }


        @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setinterests: {
                singleInterest = interest.getText().toString();
                interests.add(singleInterest);
                interest.setText(null);

                adapter = new RecyclerViewAdapterInterests(interests, this);
                recyclerView.setAdapter(adapter);

            }
            break;
            case R.id.submit: {

                interestsMap.put("Interests", interests);

                db.collection("Patients")
                        .document(patient_uid)
                        .update(interestsMap);
            }
        }
    }
}
