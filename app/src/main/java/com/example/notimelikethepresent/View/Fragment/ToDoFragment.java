package com.example.notimelikethepresent.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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
        addTaskButton.setOnClickListener(v-> getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new TaskFragment()).commit());
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

                Toast.makeText(getActivity(), "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        //super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == ADD_NOTE_REQUEST && requestCode == RESULT_OK){
//            String title = data.getStringExtra(TaskFragment.EXTRA_TITLE);
//            String details = data.getStringExtra(TaskFragment.EXTRA_DETAILS);
//            int priority = data.getIntExtra(TaskFragment.EXTRA_PRIORITY,1);
//
//            Task task = new Task(title,details,priority);
//            taskViewModel.insertTask(task);
//
//            Toast.makeText(getActivity(),"task added", Toast.LENGTH_SHORT).show();
//
//
//        }else{
//            Toast.makeText(getActivity(),"failed, try again", Toast.LENGTH_SHORT).show();
//
//        }
//    }



}
