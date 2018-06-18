package nocompany.schedule;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Schedulemain extends AppCompatActivity {
    private ArrayList<String> mNames = new ArrayList<>();

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedulemain);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Schedulemain.this);
                View mView = getLayoutInflater().inflate(R.layout.customdialog,null);
                EditText addtask = (EditText)mView.findViewById(R.id.Addtask);
                Button setdate = (Button)mView.findViewById(R.id.setdate);
                Button settime = (Button)mView.findViewById(R.id.settime);
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }
        });

        initImageBitmaps();

    }

    private void initImageBitmaps(){
        mNames.add("Hello");
        mNames.add("hi");
        mNames.add("hi");
        mNames.add("hi");
        mNames.add("hi");
        mNames.add("hi");
        mNames.add("hi");
        mNames.add("hi");
        mNames.add("hi");
        mNames.add("hi");
        mNames.add("hi");
        mNames.add("hi");
        mNames.add("hi");
        mNames.add("hi");

        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerview = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames,this);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }
}
