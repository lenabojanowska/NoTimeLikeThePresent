package com.example.notimelikethepresent.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notimelikethepresent.Entities.Task;
import com.example.notimelikethepresent.R;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {
    private List<Task> tasks = new ArrayList<>();

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View itemView = LayoutInflater.from(parent.getContext())
              .inflate(R.layout.task_item, parent, false);
        return new TaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {

        Task currentTask = tasks.get(position);

        holder.textViewTitle.setText(String.valueOf(currentTask.getTitle()));
        holder.textViewDetail.setText(String.valueOf(currentTask.getDetails()));
        holder.textViewPriority.setText(String.valueOf(currentTask.getPriority()));


    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setTasks(List<Task> tasks){
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    class TaskHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle,  textViewDetail, textViewPriority;

        public TaskHolder(View itemView){
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDetail = itemView.findViewById(R.id.textViewDetails);
            textViewPriority = itemView.findViewById(R.id.textViewPriority);



        }
    }
}
