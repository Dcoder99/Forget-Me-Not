package com.example.mlabsystem2.setup_2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterInterests extends RecyclerView.Adapter<RecyclerViewAdapterInterests.ViewHolder> {
    @NonNull
    ArrayList<String> interests = new ArrayList<>();
    Context mContext;

    public RecyclerViewAdapterInterests(ArrayList<String> interests, Context mContext)
    {
        this.interests = interests;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewAdapterInterests.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interest_layout, parent, false);
        RecyclerViewAdapterInterests.ViewHolder vh = new RecyclerViewAdapterInterests.ViewHolder(view, mContext , interests);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.interest1.setText(interests.get(position));

    }



    @Override
    public int getItemCount() {
        return interests.size();
    }

    public void setItems(ArrayList<String> interests) {
        this.interests = interests;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Context ctx;
        TextView interest1;
        ArrayList<String> interests;
        Button Delete;

        public ViewHolder(View itemView, Context ctx1 , ArrayList<String> mInterests) {
            super(itemView);
            this.ctx = ctx1;

            this.interests = mInterests;

            interest1 =(TextView)itemView.findViewById(R.id.interest_name);

            Delete = itemView.findViewById(R.id.delete);

            Delete.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {


            int position = getAdapterPosition();
            interests.remove(position);

        }
    }
}


