package com.example.notimelikethepresent.LocalStorage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notimelikethepresent.Entities.Task;

import java.util.List;

@Dao
public interface TaskDAO  {

    @Insert
    void insertTask(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Query("DELETE FROM task_table")
    void deleteAllTasks();

    @Query("SELECT*FROM task_table ORDER BY priority DESC" )
    LiveData<List<Task>> getAllTasks();

}
