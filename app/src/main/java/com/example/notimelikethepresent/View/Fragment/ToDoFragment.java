package com.example.notimelikethepresent.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notimelikethepresent.Adapter.TaskAdapter;
import com.example.notimelikethepresent.Entities.Task;
import com.example.notimelikethepresent.R;
import com.example.notimelikethepresent.ViewModel.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ToDoFragment extends Fragment {
    public static final int ADD_NOTE_REQUEST = 1;

    private TaskViewModel taskViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do, container, false);

        FloatingActionButton addTaskButton = view.findViewById(R.id.addTaskButton);
        //addTaskButton.setOnClickListener((View.OnClickListener) this);
        addTaskButton.setOnClickListener(v-> getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new TaskFragment(), null).addToBackStack(null).commit());
       // addTaskButton.setOnClickListener(new View.OnClickListener() {


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        TaskAdapter taskAdapter = new TaskAdapter();
        recyclerView.setAdapter(taskAdapter);

        taskViewModel = ViewModelProviders.of(getActivity()).get(TaskViewModel.class);
        taskViewModel.getAllTasks().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {

                taskAdapter.setTasks(tasks);

                //Toast.makeText(getActivity(), "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                taskViewModel.deleteTask(taskAdapter.getTaskAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "You deleted a task!", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);

        return view;
    }






}
