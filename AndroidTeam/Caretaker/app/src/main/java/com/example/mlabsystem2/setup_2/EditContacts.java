package com.example.mlabsystem2.setup_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.HashMap;
import java.util.Map;

public class EditContacts extends AppCompatActivity {

    FirebaseFirestore db;

    private EditText num1, num2, num3, name1, name2, name3;
    private ImageButton edit, save;
    public String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contacts);

        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        num1 = findViewById(R.id.Phone_number1);
        num2 = findViewById(R.id.Phone_number2);
        num3 = findViewById(R.id.Phone_number3);
        name1 = findViewById(R.id.Name1);
        name2 = findViewById(R.id.Name2);
        name3 = findViewById(R.id.Name3);
        edit = findViewById(R.id.edit);
        save = findViewById(R.id.save);

        num1.setEnabled(false);
        num1.setCursorVisible(false);
        num2.setEnabled(false);
        num2.setCursorVisible(false);
        num3.setEnabled(false);
        num3.setCursorVisible(false);
        name1.setEnabled(false);
        name1.setCursorVisible(false);
        name2.setEnabled(false);
        name2.setCursorVisible(false);
        name3.setEnabled(false);
        name3.setCursorVisible(false);

        edit.setVisibility(edit.VISIBLE);
        save.setVisibility(save.GONE);

        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        uid = prefs.getString("uid", "");
        Log.d("MEEEEEEEE", uid);

        loadContacts();

        }

    public void editContacts(View view) {
        num1.setEnabled(true);
        num1.setCursorVisible(true);
        num2.setEnabled(true);
        num2.setCursorVisible(true);
        num3.setEnabled(true);
        num3.setCursorVisible(true);
        name1.setEnabled(true);
        name1.setCursorVisible(true);
        name2.setEnabled(true);
        name2.setCursorVisible(true);
        name3.setEnabled(true);
        name3.setCursorVisible(true);

        edit.setVisibility(edit.GONE);
        save.setVisibility(save.VISIBLE);
    }

    public void saveContacts(View view) {
        num1.setEnabled(false);
        num1.setCursorVisible(false);
        num2.setEnabled(false);
        num2.setCursorVisible(false);
        num3.setEnabled(false);
        num3.setCursorVisible(false);
        name1.setEnabled(false);
        name1.setCursorVisible(false);
        name2.setEnabled(false);
        name2.setCursorVisible(false);
        name3.setEnabled(false);
        name3.setCursorVisible(false);

        edit.setVisibility(edit.VISIBLE);
        save.setVisibility(save.GONE);


        //Updating emergency contacts on Firebase
        Map<String, Object> user = new HashMap<>();
        user.put("name1", String.valueOf(name1.getText()));
        user.put("num1", String.valueOf(num1.getText()));
        user.put("name2", String.valueOf(name2.getText()));
        user.put("num2", String.valueOf(num2.getText()));
        user.put("name3", String.valueOf(name3.getText()));
        user.put("num3", String.valueOf(num3.getText()));

        // Add a new document with a generated ID
        db.collection("Users")
                .document(uid)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void avoid) {
                        Log.d("MEEEEEEEEEEEEEEE", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("MEEE", "Error adding document", e);
                    }
                });
    }


    public void loadContacts(){
        loadContacts(true);
    }

    public void loadContacts(final boolean fromCache){

        final Source source = (fromCache) ? Source.CACHE : Source.DEFAULT;

        DocumentReference docRef = db.collection("Users").document(uid);

        docRef.get(source).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("MEEEEEE", "DocumentSnapshot data: " + document.getData());
                        name1.setText(document.getString("name1"));
                        num1.setText(document.getString("num1"));
                        name2.setText(document.getString("name2"));
                        num2.setText(document.getString("num2"));
                        name3.setText(document.getString("name3"));
                        num3.setText(document.getString("num3"));
                    } else {
                        if(fromCache)
                        {
                            loadContacts(false);
                        }
                        Log.d("MEEEEEE", "No such document");

                        num1.setEnabled(true);
                        num1.setCursorVisible(true);
                        num2.setEnabled(true);
                        num2.setCursorVisible(true);
                        num3.setEnabled(true);
                        num3.setCursorVisible(true);
                        name1.setEnabled(true);
                        name1.setCursorVisible(true);
                        name2.setEnabled(true);
                        name2.setCursorVisible(true);
                        name3.setEnabled(true);
                        name3.setCursorVisible(true);

                        save.setVisibility(save.VISIBLE);
                        edit.setVisibility(edit.GONE);

                    }
                } else {
                    Log.d("MEEEEEE", "get failed with ", task.getException());
                }
            }
        });


    }
}

