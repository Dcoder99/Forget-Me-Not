package com.example.mlabsystem2.setup_2;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleMain extends AppCompatActivity {

    private static final String TAG = "ScheduleMain";

    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();

    private static int notificationId = 0;
    private static int reqcode = 0;

    private ArrayList<String> taskFID = new ArrayList<>();

    public String formatedDate, formatedTime;

    FloatingActionButton fab;
    public static String firebase_id;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedulemain);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);



        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);


        recyclerView =  findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(taskFID, this);
        recyclerView.setAdapter(adapter);

        loadTaskList();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ScheduleMain.this);
                View mView = getLayoutInflater().inflate(R.layout.customdialog, null);
                final EditText addtask = (EditText) mView.findViewById(R.id.Addtask);
                final EditText addDes = mView.findViewById(R.id.AddDescription);
                Button setdate = (Button) mView.findViewById(R.id.setdate);
                Button settime = (Button) mView.findViewById(R.id.settime);
                final TextView display = (TextView) mView.findViewById(R.id.display);
                final Spinner myspinner = (Spinner) mView.findViewById(R.id.spinner);

                ArrayAdapter<String> myadapter = new ArrayAdapter<String>(ScheduleMain.this,
                        android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinnernames));
                myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                myspinner.setAdapter(myadapter);


                setdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateDate();
                    }
                });

                settime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateTime();
                    }
                });

        /*        set.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        display.setText(formatDateTime.format(dateTime.getTime()));
                        if(addtask.getText().toString().isEmpty()) {
                            Toast.makeText(ScheduleMain.this, "Insert Something", Toast.LENGTH_SHORT).show();
                        }else {


                        }

                    }
                });*/

                display.setText(formatDateTime.format(dateTime.getTime()));

                //Add a Task
                mBuilder.setView(mView);
                mBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if (addtask.getText().toString().isEmpty()) {
                            Toast.makeText(ScheduleMain.this, "Task Name cannot be empty.", Toast.LENGTH_SHORT).show();
                        } else {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                            formatedDate = sdf.format(dateTime.getTime());
                            SimpleDateFormat sdft = new SimpleDateFormat("HH:mm a");
                            formatedTime = sdft.format(dateTime.getTime());
                            String task = String.valueOf(addtask.getText());
                            String descrption = String.valueOf(addDes.getText());


                            // Storing Task information on Firebase
                            Map<String, Object> user = new HashMap<>();
                            user.put("Task", task);
                            user.put("Date", formatedDate);
                            user.put("Time", formatedTime);
                            user.put("Description", descrption);
                            // TODO: Change this to uuid or firebase generated id
                            firebase_id = String.valueOf(System.currentTimeMillis());
//                            interestsMap.put("ID", firebase_id);


                            // Add a new document with a generated ID
                            db.collection("Tasks")
                                    .document(firebase_id)
                                    .set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void avoid) {
                                            Log.d("MEEEEEEEEEEEEEEE", "DocumentSnapshot successfully written!");
//                                            loadTaskList();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("MEEE", "Error adding document", e);
                                        }
                                    });

//                            loadTaskList();
                            //End of Updating Info on FireBase


                            Intent intent = new Intent(ScheduleMain.this, AlarmReceiver.class);
                            intent.putExtra("task", addtask.getText().toString());
                            intent.putExtra("notificationId", notificationId);

                            PendingIntent alarmintent = PendingIntent.getBroadcast(ScheduleMain.this, reqcode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

                            if (myspinner.getSelectedItem().toString().equals("Every minute")) {

                                alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, dateTime.getTimeInMillis(), 1000 * 1 * 60, alarmintent);

                                Toast.makeText(ScheduleMain.this, "Done!", Toast.LENGTH_SHORT).show();
                            } else if (myspinner.getSelectedItem().toString().equals("Every two minutes")) {
                                alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, dateTime.getTimeInMillis(), 1000 * 2 * 60, alarmintent);

                                Toast.makeText(ScheduleMain.this, "Done!", Toast.LENGTH_SHORT).show();
                            } else {
                                alarm.set(AlarmManager.RTC_WAKEUP, dateTime.getTimeInMillis(), alarmintent);

                                Toast.makeText(ScheduleMain.this, "Done!", Toast.LENGTH_SHORT).show();
                            }

                            reqcode++;
                            notificationId++;


                        }
                    }
                });

                mBuilder.setNegativeButton("Cancel", null);

                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }

    private void loadTaskList() {
        loadTaskList(true);

    }

    public void loadTaskList(final boolean fromCache) {

        Log.d("MEEE", "Before loading");

        taskFID = new ArrayList<>();

        // Refer to firebase docs for patient_id condition
        db.collection("Tasks")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "listen:error", e);
                            return;
                        }

                        for (DocumentChange dc : snapshots.getDocumentChanges()) {
                            QueryDocumentSnapshot document = dc.getDocument();
                            switch (dc.getType()) {
                                case ADDED:
                                    Log.d("MEEEE", "NEW " + dc.getDocument().getData());
                                    taskFID.add(document.getId());
                                    break;
                                case MODIFIED:
                                    Log.d("MEEE", "MODIFIED : " + dc.getDocument().getData());
                                    break;
                                case REMOVED:
                                    Log.d("MEEE", "REMOVED : " + dc.getDocument().getData());
                                    taskFID.remove(document.getId());
                                    Log.d("MEEE", "REMOVED FID : " + taskFID);

                                    break;
                            }
                            adapter.setItems(taskFID);
                            adapter.notifyDataSetChanged();
                        }

                    }
                });

//
//        // TODO: Add "where patient_id = patientID" to the query
//        db.collection("Tasks")
//                .get(source)
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
////                                Log.d("MEEEE", document.getId() + " => " + document.getData());
//                                taskNames.add(document.getString("Task"));
//                                taskDates.add(document.getString("Date"));
//                                taskTimes.add(document.getString("Time"));
//                                taskFID.add(document.getId());
//                            }
//
////                            Log.d("MEEEE", "Loaded data");
//
//                            if (taskNames.size() == 0 && fromCache) {
//                                loadTaskList(false);
//
//                            }
//                            adapter.setItems(taskNames, taskDates, taskTimes, taskFID);
//                            adapter.notifyDataSetChanged();
//
//                        } else {
//                            Log.d(TAG, "Error getting documents: ", task.getException());
//                        }
//                    }
//                });
    }

    private void updateDate() {
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH))
                .show();

    }

    private void updateTime() {
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE),
                false).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, month);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            dateTime.set(Calendar.SECOND, 0);
        }
    };

}
