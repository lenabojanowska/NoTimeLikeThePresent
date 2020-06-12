package com.example.notimelikethepresent.View.Fragment;


import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.allyants.notifyme.NotifyMe;
import com.example.notimelikethepresent.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.w3c.dom.Text;

import java.util.Calendar;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;


public class CalendarFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private EditText titleEditText, subtitleEditText;
    private TimePickerDialog timePickerDialog;
    private DatePickerDialog datePickerDialog;
    private FragmentManager fm;
    private Button cancelButton, setNotificationButton;

    Calendar now = Calendar.getInstance();

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        titleEditText = (EditText) view.findViewById(R.id.titleEdtiView);
        subtitleEditText = (EditText) view.findViewById(R.id.subtitleEditView);
        cancelButton = (Button) view.findViewById(R.id.cancelButton);
        setNotificationButton = (Button) view.findViewById(R.id.saveNotificationButton);

        datePickerDialog = DatePickerDialog.newInstance(
                CalendarFragment.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );


        timePickerDialog = TimePickerDialog.newInstance(
                CalendarFragment.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                false
        );

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotifyMe.cancel(getActivity().getApplicationContext(),"test");
            }
        });

        setNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show(getActivity().getFragmentManager(), "");
            }
        });

        return view;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        now.set(Calendar.YEAR,year);
        now.set(Calendar.MONTH,monthOfYear);
        now.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        timePickerDialog.show(getActivity().getFragmentManager(),"kjhgfd");


    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

        now.set(Calendar.HOUR_OF_DAY,hourOfDay);
        now.set(Calendar.MINUTE,minute);
        now.set(Calendar.SECOND,second);

        //--------------------------------
        //notification

        NotifyMe notifyMe = new NotifyMe.Builder(getActivity().getApplicationContext())
                .title(titleEditText.getText().toString())
                .content(subtitleEditText.getText().toString())
                .color(255,0,0,255)
                .led_color(255,255,255,255)
                .time(now)
                .addAction(new Intent(), "Snozze", false)
                .key("test")
                .addAction(new Intent(), "dismiss", true, false)
                .addAction(new Intent(), "done")
                .large_icon(R.mipmap.ic_launcher_round)
                .build();
    }


}

