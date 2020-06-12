package com.example.notimelikethepresent.View.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.notimelikethepresent.Connection.Entities.Main;
import com.example.notimelikethepresent.Entities.Task;
import com.example.notimelikethepresent.LocalStorage.TaskDatabase;
import com.example.notimelikethepresent.MainActivity;
import com.example.notimelikethepresent.R;
import com.example.notimelikethepresent.ViewModel.TaskViewModel;

import static android.app.Activity.RESULT_OK;
import static com.example.notimelikethepresent.View.Fragment.ToDoFragment.ADD_NOTE_REQUEST;

public class TaskFragment extends Fragment  {

    public static final String EXTRA_TITLE = "com.example.notimelikethepresent.TITLE";
    public static final String EXTRA_DETAILS = "com.example.notimelikethepresent.EXTRA_DETAILS";
    public static final String EXTRA_PRIORITY = "com.example.notimelikethepresent.EXTRA_PRIORITY";


    private EditText editTextTitle, editTextDetails;

    private Button saveTaskButton;
    private NumberPicker priorityPicker;

    private TaskViewModel taskViewModel;

    @Nullable
    @Override
//    public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.fragment_task);
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);

       editTextTitle = (EditText)view.findViewById(R.id.editTextTitle);
       editTextDetails = (EditText)view.findViewById(R.id.editTextDetails);
       //spinner = (Spinner)viewview.findViewById(R.id.spinner);
        priorityPicker = (NumberPicker)view.findViewById(R.id.priorityPicker);
        saveTaskButton = (Button)view.findViewById(R.id.saveTaskButton);


        priorityPicker.setMinValue(1);
        priorityPicker.setMaxValue(5);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        saveTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task(
                        editTextTitle.getText().toString(),
                        editTextDetails.getText().toString(),
                        priorityPicker.getValue());
//        task.setTitle(title);
//        task.setDetails(details);
//        task.setPriority(priority);

                taskViewModel.insertTask(task);

                Toast.makeText(getActivity(),"added",Toast.LENGTH_SHORT).show();

                editTextTitle.setText("");
                editTextDetails.setText("");
                //onBackPressed();
               // taskViewModel.getAllTasks();
               // beginTransaction().replace(R.id.fragment_container, new MainFragment(),null).commit();

                Fragment newFragment = new MainFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);

// Commit the transaction
                transaction.commit();

            }
        });

    return  view;
    }
}
