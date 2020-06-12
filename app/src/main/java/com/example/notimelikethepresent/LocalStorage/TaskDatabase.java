package com.example.notimelikethepresent.LocalStorage;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.notimelikethepresent.Entities.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {

    private static TaskDatabase instance;
    public abstract TaskDAO taskDAO();

    public static synchronized TaskDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TaskDatabase.class, "taskDatabase")
                    .fallbackToDestructiveMigration()
                    .addCallback(taskCallback)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    private static TaskDatabase.Callback taskCallback = new TaskDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private TaskDAO taskDAO;

        private PopulateDbAsyncTask(TaskDatabase db){
            taskDAO = db.taskDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDAO.insertTask(new Task("task", "task", 1));
            return null;
        }
    }
}
