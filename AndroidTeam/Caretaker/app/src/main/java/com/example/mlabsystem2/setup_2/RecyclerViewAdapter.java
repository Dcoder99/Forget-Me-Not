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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> taskNames = new ArrayList<>();
    private ArrayList<String> taskDates = new ArrayList<>();
    private ArrayList<String> taskTimes = new ArrayList<>();
    private ArrayList<String> taskFID = new ArrayList<>();
    private Context mContext;
    private RecyclerViewAdapter adapter = this;

    private FirebaseFirestore db;

    public RecyclerViewAdapter(ArrayList<String> taskNames, ArrayList<String> taskDates, ArrayList<String> taskTimes, ArrayList<String> taskFID, Context mContext) {

        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        setItems(taskNames, taskDates, taskTimes, taskFID);

        this.mContext = mContext;
    }

    private void deleteTask(String firebase_id) {

        Log.d("MEEEE", "firebase id" + firebase_id+ "...");
        db.collection("Tasks").document(firebase_id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("MEEEEEEEEEEEEE", "DocumentSnapshot successfully deleted!");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("MEEEEEEEEEEEEE", "Error deleting document", e);
                    }
                });

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
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


                deleteTask(taskFID.get(position));

                taskNames.remove(position);
                taskDates.remove(position);
                taskTimes.remove(position);
                taskFID.remove(position);

                setItems(taskNames, taskDates, taskTimes, taskFID);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void setItems(ArrayList<String> taskNames, ArrayList<String> taskDates, ArrayList<String> taskTimes, ArrayList<String> taskFID) {
        this.taskNames = taskNames;
        this.taskDates = taskDates;
        this.taskTimes = taskTimes;
        this.taskFID = taskFID;
    }

    @Override
    public int getItemCount() {
        return taskNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

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
            button = itemView.findViewById(R.id.delete);
            parentlayout = itemView.findViewById(R.id.parent_layout);
            int pos = getAdapterPosition();

        }

    }
}
