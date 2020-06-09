package com.example.notimelikethepresent.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notimelikethepresent.Entities.Task;
import com.example.notimelikethepresent.Repository.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository taskRepository;
    private LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
        allTasks = taskRepository.getAllTasks();
    }

    public void insertTask(Task task){
        taskRepository.insertTask(task);
    }

    public void updateTask(Task task){
        taskRepository.updateTask(task);
    }

    public void deleteTask(Task task){
        taskRepository.deleteTask(task);
    }

    public void deleteAllTasks(){
        taskRepository.deleteAllTasks();
    }

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }

}
