package com.example.mlabsystem2.setup_2;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Schedulemain extends AppCompatActivity {

    private static final String TAG = "Schedulemain";

    private ArrayList<String> tasks = new ArrayList<>();
    private ArrayList<String> dates = new ArrayList<>();
    private ArrayList<String> times = new ArrayList<>();
    private ArrayList<String> ids = new ArrayList<>();
    private ArrayList<String> taskList = new ArrayList<>();
    private ArrayList<String> dateList = new ArrayList<>();
    private ArrayList<String> timeList = new ArrayList<>();
    private ArrayList<String> firebaseIDList = new ArrayList<>();

    DbHelper dbHelper;
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();

    private static int notificationId = 0;
    private static int reqcode = 0;


    FloatingActionButton fab;
    public static String firebase_id;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedulemain);

        dbHelper = new DbHelper(this);

        getFBdata();

        loadTaskList();


        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Schedulemain.this);
                View mView = getLayoutInflater().inflate(R.layout.customdialog,null);
                final EditText addtask = (EditText)mView.findViewById(R.id.Addtask);
                Button setdate = (Button)mView.findViewById(R.id.setdate);
                Button settime = (Button)mView.findViewById(R.id.settime);
                final TextView display = (TextView)mView.findViewById(R.id.display);
                final Spinner myspinner = (Spinner)mView.findViewById(R.id.spinner);

                ArrayAdapter<String> myadapter = new ArrayAdapter<String>(Schedulemain.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinnernames));
                myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                myspinner.setAdapter(myadapter);


                //final String text = myspinner.getSelectedItem().toString();


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
                            Toast.makeText(Schedulemain.this, "Insert Something", Toast.LENGTH_SHORT).show();
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


                        if(addtask.getText().toString().isEmpty()){
                            Toast.makeText(Schedulemain.this, "Insert Something", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                            String formatedDate = sdf.format(dateTime.getTime());
                            SimpleDateFormat sdft = new SimpleDateFormat("HH:mm a");
                            String formatedTime = sdft.format(dateTime.getTime());
                            String task = String.valueOf(addtask.getText());
                            tasks.add(task);
                            dates.add(formatedDate);
                            times.add(formatedTime);

//-----------------------
// Storing Task information on Firebase
                            Map<String,Object> user = new HashMap<>();
                            user.put("Task",task);
                            user.put("Date",formatedDate);
                            user.put("Time",formatedTime);

                            firebase_id = String.valueOf(System.currentTimeMillis());
                            user.put("ID",firebase_id);

                            //getFBdata();

                            // Add a new document with a generated ID
                            db.collection("Tasks")
                                    .document(firebase_id)
                                    .set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void avoid) {
                                                Log.d(TAG, "DocumentSnapshot successfully written!");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error adding document", e);
                                        }
                                    });
//End of Updating Info on FireBase
// ------------------


                            dbHelper.insertnewTask(task,formatedDate,formatedTime,firebase_id);
                            adapter = new RecyclerViewAdapter(tasks,dates,times,firebaseIDList, Schedulemain.this);
                            recyclerView.setAdapter(adapter);



                            Intent intent = new Intent(Schedulemain.this, AlarmReceiver.class);
                            intent.putExtra("task", addtask.getText().toString());
                            intent.putExtra("notificationId", notificationId);

                            PendingIntent alarmintent = PendingIntent.getBroadcast(Schedulemain.this, reqcode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

                            if(myspinner.getSelectedItem().toString().equals("Every minute")) {

                                alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, dateTime.getTimeInMillis(), 1000 * 1 * 60, alarmintent);

                                Toast.makeText(Schedulemain.this, "Done!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            if(myspinner.getSelectedItem().toString().equals("Every two minutes") ){
                                alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP,dateTime.getTimeInMillis(), 1000 * 2 * 60, alarmintent);

                                Toast.makeText(Schedulemain.this, "Done!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                alarm.set(AlarmManager.RTC_WAKEUP,dateTime.getTimeInMillis(),alarmintent);

                                Toast.makeText(Schedulemain.this, "Done!", Toast.LENGTH_SHORT).show();
                            }

                            reqcode++;
                            notificationId++;



                        }
                    }
                });

                mBuilder.setNegativeButton("Cancel",null);

                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
}
    


    private void loadTaskList() {


        taskList = dbHelper.getTaskList();
        dateList = dbHelper.getDateList();
        timeList = dbHelper.getTimeList();
        firebaseIDList = dbHelper.getFirebaseIDList();


        if (adapter == null) {
            recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
            layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new RecyclerViewAdapter(taskList,dateList,timeList,firebaseIDList,this);
            recyclerView.setAdapter(adapter);
            }
            else{
            tasks.addAll(taskList);
            dates.addAll(dateList);
            times.addAll(timeList);
            ids.addAll(firebaseIDList);

            adapter.notifyDataSetChanged();

        }
    }

    private void updateDate(){
        new DatePickerDialog(this,d,dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH))
                .show();

    }

    private void updateTime(){
        new TimePickerDialog(this,t,dateTime.get(Calendar.HOUR_OF_DAY),dateTime.get(Calendar.MINUTE),
                false).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateTime.set(Calendar.YEAR,year);
            dateTime.set(Calendar.MONTH,month);
            dateTime.set(Calendar.DAY_OF_MONTH,dayOfMonth);
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


//Reading Data From FireBase (On Start)

    public void getFBdata(){
        //Reading from Firebase
        db.collection("Tasks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                taskList.add(document.getString("Task"));
                                dateList.add(document.getString("Date"));
                                timeList.add(document.getString("Time"));
                                firebaseIDList.add(document.getString("ID"));

                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
