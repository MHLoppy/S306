package com.markheath.taskmanagerapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.RecViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList taskId, taskTitle, taskDescription, taskDuedate;

    RecViewAdapter(Activity activity, Context context, ArrayList taskId, ArrayList taskTitle,
                   ArrayList taskDescription, ArrayList taskDuedate) {
        this.activity = activity;
        this.context = context;
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.taskDuedate = taskDuedate;
    }

    @NonNull
    @Override
    public RecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rec_view_row, parent, false);
        return new RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecViewHolder holder, int position) {
        holder.taskIdTv.setText(String.valueOf(taskId.get(position)));
        holder.taskTitleTv.setText(String.valueOf(taskTitle.get(position)));
        holder.taskDescriptionTv.setText(String.valueOf(taskDescription.get(position)));
        holder.taskDuedateTv.setText(String.valueOf(taskDuedate.get(position)));
        holder.tasksLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TaskEditorActivity.class);
                intent.putExtra("id", String.valueOf(taskId.get(position)));
                intent.putExtra("title", String.valueOf(taskTitle.get(position)));
                intent.putExtra("description", String.valueOf(taskDescription.get(position)));
                intent.putExtra("duedate", String.valueOf(taskDuedate.get(position)));
                intent.putExtra("button", "Save changes");
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskId.size();
    }

    public class RecViewHolder extends RecyclerView.ViewHolder{

        TextView taskIdTv, taskTitleTv, taskDescriptionTv, taskDuedateTv;
        ConstraintLayout tasksLayout;

        public RecViewHolder(@NonNull View itemView) {
            super(itemView);
            taskIdTv = itemView.findViewById(R.id.taskIdTv);
            taskTitleTv = itemView.findViewById(R.id.taskTitleTv);
            taskDescriptionTv = itemView.findViewById(R.id.taskDescriptionTv);
            taskDuedateTv = itemView.findViewById(R.id.taskDuedateTv);
            tasksLayout = itemView.findViewById(R.id.tasksLayout);
        }
    }
}
