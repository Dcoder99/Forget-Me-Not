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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Schedulemain extends AppCompatActivity {

    private static final String TAG = "Schedulemain";

    private ArrayList<String> tasks = new ArrayList<>();
    private ArrayList<String> taskList = new ArrayList<>();
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

                            Intent intent = new Intent(Schedulemain.this, AlarmReceiver.class);
                            intent.putExtra("task", addtask.getText().toString());
                            intent.putExtra("notificationId", notificationId);

                            PendingIntent alarmintent = PendingIntent.getBroadcast(Schedulemain.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

                            alarm.set(AlarmManager.RTC_WAKEUP, dateTime.getTimeInMillis(), alarmintent);

                            Toast.makeText(Schedulemain.this, "Done!", Toast.LENGTH_SHORT).show();
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
                            String task = String.valueOf(addtask.getText());
                            tasks.add(task);
                            dbHelper.insertnewTask(task);
                            adapter = new RecyclerViewAdapter(tasks, Schedulemain.this);
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
        if (adapter == null) {
            recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
            layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new RecyclerViewAdapter(taskList,this);
            recyclerView.setAdapter(adapter);
            }
            else{
            tasks.addAll(taskList);
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
        new TimePickerDialog(this,t,dateTime.get(Calendar.HOUR_OF_DAY),dateTime.get(Calendar.MINUTE),true).show();
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
