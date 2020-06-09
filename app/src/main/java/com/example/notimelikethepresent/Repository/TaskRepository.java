package com.example.notimelikethepresent.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.notimelikethepresent.Entities.Task;
import com.example.notimelikethepresent.LocalStorage.TaskDAO;
import com.example.notimelikethepresent.LocalStorage.TaskDatabase;

import java.util.List;

public class TaskRepository {
    private static TaskRepository instance;
    private TaskDAO taskDAO;
    private LiveData<List<Task>> allTasks;

    public TaskRepository(Application application){
        TaskDatabase taskDatabase = TaskDatabase.getInstance(application);
        taskDAO = taskDatabase.taskDAO();
        allTasks = taskDAO.getAllTasks();
    }

    public void insertTask(Task task){
        new InsertTaskAsync(taskDAO).execute(task);
    }

    public void updateTask(Task task){
        new UpdateTaskAsync(taskDAO).execute(task);
    }

    public void deleteTask(Task task){
        new DeleteTaskAsync(taskDAO).execute(task);
    }

    public void deleteAllTasks(){
        new DeleteAllTasksAsync(taskDAO).execute();
    }

    public LiveData<List<Task>> getAllTasks(){
       return allTasks;
    }

    private static class InsertTaskAsync extends AsyncTask<Task, Void, Void> {
        private TaskDAO taskDAO;

        private InsertTaskAsync(TaskDAO taskDAO) {
            this.taskDAO = taskDAO;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDAO.insertTask(tasks[0]);
            return null;
        }
    }

    private static class UpdateTaskAsync extends AsyncTask<Task, Void, Void> {
        private TaskDAO taskDAO;

        private UpdateTaskAsync(TaskDAO taskDAO) {
            this.taskDAO = taskDAO;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDAO.updateTask(tasks[0]);
            return null;
        }
    }

    private static class DeleteTaskAsync extends AsyncTask<Task, Void, Void> {
        private TaskDAO taskDAO;

        private DeleteTaskAsync(TaskDAO taskDAO) {
            this.taskDAO = taskDAO;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDAO.deleteTask(tasks[0]);
            return null;
        }
    }

    private static class DeleteAllTasksAsync extends AsyncTask<Void, Void, Void> {
        private TaskDAO taskDAO;

        private DeleteAllTasksAsync(TaskDAO taskDAO) {
            this.taskDAO = taskDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDAO.deleteAllTasks();
            return null;
        }
    }




}
