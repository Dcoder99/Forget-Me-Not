package nocompany.schedule;

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

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> taskNames = new ArrayList<>();
    private Context mContext;
    private RecyclerViewAdapter adapter = this;


    public RecyclerViewAdapter(ArrayList<String> taskNames, Context mContext) {
        DbHelper dbHelper = new DbHelper(mContext);
        this.taskNames = dbHelper.getTaskList() ;
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
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String removable = taskNames.get(position);
                adapter.notifyDataSetChanged();
                taskNames.remove(position);
                DbHelper dbHelper = new DbHelper(mContext);
                dbHelper.deleteTask(removable);
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
        Button button;
        RelativeLayout parentlayout;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            taskname = itemView.findViewById(R.id.task_name);
            button  = itemView.findViewById(R.id.delete);
            parentlayout = itemView.findViewById(R.id.parent_layout);
            int pos = getAdapterPosition();

        }

    }
}
