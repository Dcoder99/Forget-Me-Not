package com.example.mlabsystem2.dialerfinal;

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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Schedulemain extends AppCompatActivity {

    private static final String TAG = "Schedulemain";

    private ArrayList<String> taskNames = new ArrayList<>();
    private ArrayList<String> taskDates = new ArrayList<>();
    private ArrayList<String> taskTimes = new ArrayList<>();
    private ArrayList<String> taskFID = new ArrayList<>();
    public FirebaseFirestore db;
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private static int reqcode = 0;
    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();

    private int notificationId = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedulemain);
        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(taskFID, this);
        recyclerView.setAdapter(adapter);


        loadTaskList();
    }

         private void loadTaskList() {
              loadTaskList(true);

        }

         public void loadTaskList(Boolean fromCache) {

        Log.d("MEEE", "Before loading");

        taskNames = new ArrayList<>();
        taskDates = new ArrayList<>();
        taskTimes = new ArrayList<>();
        taskFID = new ArrayList<>();


//
//        // TODO: Add "where patient_id = patientID" to the query




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
                                    taskNames.add(document.getString("Task"));
                                    taskDates.add(document.getString("Date"));
                                    taskTimes.add(document.getString("Time"));
                                    Intent intent = new Intent(Schedulemain.this, AlarmReceiver.class);
                                    intent.putExtra("task",document.getString("Task"));
                                    intent.putExtra("notificationId", notificationId);

                                    PendingIntent alarmintent = PendingIntent.getBroadcast(Schedulemain.this, reqcode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                                    AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
                                    String d=String.valueOf(dateTime.getTimeInMillis());
                                    alarm.set(AlarmManager.RTC_WAKEUP, dateTime.getTimeInMillis(), alarmintent);

                                    //adapter.setItems(taskNames,taskDates,taskTimes,taskFID);

                                    break;
                                case MODIFIED:
                                    Log.d("MEEE", "MODIFIED : " + dc.getDocument().getData());
                                    break;
                                case REMOVED: {
                                    Log.d("MEEE", "REMOVED : " + dc.getDocument().getData());
                                    int x=taskFID.indexOf(document.getId());

                                    deleteTask(x);
                                    Log.d("MEEE", "REMOVED FID : " + taskFID);
                                }

                            }
                            adapter.setItems(taskNames,taskDates,taskTimes,taskFID);
                            adapter.notifyDataSetChanged();
                        }

                    }
                });



    }









    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateTime.set(Calendar.YEAR,year);
            dateTime.set(Calendar.MONTH,month);
            dateTime.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        }
    };

    TimePickerDialog.OnTimeSetListener t =new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY,hourOfDay);
            dateTime.set(Calendar.MINUTE,minute);
            dateTime.set(Calendar.SECOND,0);

        }
    };
    private void deleteTask(final int firebase_id) {

        Log.d("MEEEE", "firebase id" + firebase_id + "...");
        db.collection("Tasks").document(taskFID.get(firebase_id))
                .delete();


                        Log.d("MEEEEEEEEEEEEE", "DocumentSnapshot successfully deleted!");
                        taskNames.remove(firebase_id);
                        taskFID.remove(firebase_id);
                        taskTimes.remove(firebase_id);
                        taskDates.remove(firebase_id);
                        adapter.setItems(taskNames,taskDates,taskTimes,taskFID);


                    }

}


