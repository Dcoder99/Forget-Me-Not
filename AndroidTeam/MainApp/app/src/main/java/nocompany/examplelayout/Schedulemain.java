package nocompany.examplelayout;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Schedulemain extends AppCompatActivity {

    private static final String TAG = "Schedulemain";

    private ArrayList<String> tasks = new ArrayList<>();
    private ArrayList<String> dates = new ArrayList<>();
    private ArrayList<String> times = new ArrayList<>();
    private ArrayList<String> taskList = new ArrayList<>();
    private ArrayList<String> dateList = new ArrayList<>();
    private ArrayList<String> timeList = new ArrayList<>();
    DbHelper dbHelper;
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();

    private int notificationId = 1;

    FloatingActionButton fab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedulemain);

        dbHelper = new DbHelper(this);
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
                Button set = (Button)mView.findViewById(R.id.set);
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
                        display.setText(formatDateTime.format(dateTime.getTime()));
                    }
                });

                settime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateTime();
                        display.setText(formatDateTime.format(dateTime.getTime()));
                    }
                });

                set.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        display.setText(formatDateTime.format(dateTime.getTime()));
                        if(addtask.getText().toString().isEmpty()) {
                            Toast.makeText(Schedulemain.this, "Insert Something", Toast.LENGTH_SHORT).show();
                        }else {

                            int i = 0;
                            Intent intent = new Intent(Schedulemain.this, AlarmReceiver.class);
                            intent.putExtra("task", addtask.getText().toString());
                            intent.putExtra("notificationId", notificationId);

                            PendingIntent alarmintent = PendingIntent.getBroadcast(Schedulemain.this, 0, intent, 0);
                            AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

                            if(myspinner.getSelectedItem().toString().equals("Every minute")) {

                                alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, dateTime.getTimeInMillis(), 1000 * 1 * 60, alarmintent);

                                Toast.makeText(Schedulemain.this, "Done!", Toast.LENGTH_SHORT).show();
                            }
                            if(myspinner.getSelectedItem().toString().equals("Every two minutes") ){
                                alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP,dateTime.getTimeInMillis(),1000*2*60, alarmintent);

                                Toast.makeText(Schedulemain.this, "Done!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                alarm.set(AlarmManager.RTC_WAKEUP,dateTime.getTimeInMillis(),alarmintent);

                                Toast.makeText(Schedulemain.this, "Done!", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

               display.setText(formatDateTime.format(dateTime.getTime()));

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
                            dbHelper.insertnewTask(task,formatedDate,formatedTime);
                            adapter = new RecyclerViewAdapter(tasks,dates,times, Schedulemain.this);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }
        });

      //  initRecyclerView();

    }
    


    private void loadTaskList() {
        taskList = dbHelper.getTaskList();
        dateList = dbHelper.getDateList();
        timeList = dbHelper.getTimeList();
        if (adapter == null) {
            recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
            layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new RecyclerViewAdapter(taskList,dateList,timeList,this);
            recyclerView.setAdapter(adapter);
            }
            else{
            tasks.addAll(taskList);
            dates.addAll(dateList);
            times.addAll(timeList);
            adapter.notifyDataSetChanged();
        }
    }

    public void deleteTask (String removable){
        dbHelper.deleteTask(removable);
        }

    private void updateDate(){
        new DatePickerDialog(this,d,dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void updateTime(){
        new TimePickerDialog(this,t,dateTime.get(Calendar.HOUR_OF_DAY),dateTime.get(Calendar.MINUTE),false).show();
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


/*   private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(tasks,this);
        recyclerView.setAdapter(adapter);

    } */

}
