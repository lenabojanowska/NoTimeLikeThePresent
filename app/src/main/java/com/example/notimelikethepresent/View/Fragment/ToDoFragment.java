package com.example.notimelikethepresent.View.Fragment;

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

import com.example.notimelikethepresent.Entities.Task;
import com.example.notimelikethepresent.R;
import com.example.notimelikethepresent.ViewModel.TaskViewModel;

import java.util.List;

public class ToDoFragment extends Fragment {
    private TaskViewModel taskViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do, container, false);

        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        taskViewModel.getAllTasks().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {

                Toast.makeText(getActivity(), "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
