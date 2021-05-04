package com.garden.taskgarden;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Task> taskList;
    Context context;
    View.OnClickListener listener;

    public RecyclerViewAdapter(ArrayList<Task> inAL, Application application){
        this.taskList = inAL;
        this.context = application;
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater lInflater = LayoutInflater.from(context);
        View v = lInflater.inflate(R.layout.row_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int index = holder.getAdapterPosition();
        holder.title.setText(taskList.get(position).getTitle());
        holder.description.setText(taskList.get(position).getDescription());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(holder.view);
            }
        });
    }


    public int getItemCount() {
        return taskList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView description;
        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.Tasktitle);
            this.description = (TextView) itemView.findViewById(R.id.Taskdescription);
            this.view = itemView;
        }
    }
}
