package com.example.mlabsystem2.setup_2;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> taskNames = new ArrayList<>();
    private ArrayList<String> taskDates = new ArrayList<>();
    private ArrayList<String> taskTimes = new ArrayList<>();
    private ArrayList<String> taskFID = new ArrayList<>();

    private Context mContext;
    private RecyclerViewAdapter adapter = this;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public RecyclerViewAdapter(ArrayList<String> taskNames,ArrayList<String> taskDates,ArrayList<String> taskTimes,ArrayList<String> taskFID, Context mContext) {
        DbHelper dbHelper = new DbHelper(mContext);
        this.taskNames = taskNames;
        this.taskDates = taskDates;
        this.taskTimes = taskTimes;
        this.taskFID = taskFID;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.taskname.setText(taskNames.get(position));
        holder.date.setText(taskDates.get(position));
        holder.time.setText(taskTimes.get(position));


        //Task Deletion

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String removable = taskNames.get(position);
                String FID = taskFID.get(position);
                adapter.notifyDataSetChanged();
                taskNames.remove(position);
                taskDates.remove(position);
                taskTimes.remove(position);
                taskFID.remove(position);
                DbHelper dbHelper = new DbHelper(mContext);
                dbHelper.deleteTask(removable,FID);
            }
        });
    }


    @Override
    public int getItemCount() {
        return taskNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView taskname;
        TextView date;
        TextView time;
        Button button;
        RelativeLayout parentlayout;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            taskname = itemView.findViewById(R.id.task_name);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            button  = itemView.findViewById(R.id.delete);
            parentlayout = itemView.findViewById(R.id.parent_layout);
            int pos = getAdapterPosition();

        }

    }
}
