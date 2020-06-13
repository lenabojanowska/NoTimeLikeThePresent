package com.example.notimelikethepresent.View.Fragment;

import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.notimelikethepresent.R;
import com.example.notimelikethepresent.StepCounter.StepDetector;
import com.example.notimelikethepresent.StepCounter.StepListener;

import static android.content.Context.SENSOR_SERVICE;

public class HealthFragment extends Fragment implements SensorEventListener {
      private ImageView bottleImage;
//    private TextView counterView;
//    private StepDetector simpleStepDetector;
//    private SensorManager sensorManager;
//    private Sensor accel;
//    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
//    private int numSteps;
      AnimationDrawable bottleAnimation, bottleAnimation2, bottleAnimation3, bottleAnimation4;

    private TextView counterView;
    SensorManager sensorManager;
    private boolean running = false;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health, container, false);

        counterView = (TextView) view.findViewById(R.id.counterView);

        sensorManager = (SensorManager) getActivity().getSystemService(getActivity().getApplicationContext().SENSOR_SERVICE);

        ImageView imageView = (ImageView) view.findViewById(R.id.bottleImageView);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.bottleImageView2);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.bottleImageView3);
        ImageView imageView4 = (ImageView) view.findViewById(R.id.bottleImageView4);

        imageView.setBackgroundResource(R.drawable.animation_bottle_filling);
        bottleAnimation = (AnimationDrawable) imageView.getBackground();

        imageView2.setBackgroundResource(R.drawable.animation_filling_bottle2);
        bottleAnimation2 = (AnimationDrawable) imageView2.getBackground();

        imageView3.setBackgroundResource(R.drawable.animation_bottle_filling);
        bottleAnimation3 = (AnimationDrawable) imageView3.getBackground();

        imageView4.setBackgroundResource(R.drawable.animation_bottle_filling);
        bottleAnimation4 = (AnimationDrawable) imageView4.getBackground();

//------------------------------------
        //animation

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { bottleAnimation.start(); }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottleAnimation2.start();
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottleAnimation3.start();
                         }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottleAnimation4.start();
            }
        });

        return view;
    }


//------------------------------------
    //Sensor, step counter

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running){
            counterView.setText(String.valueOf(event.values[0]));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null){
            sensorManager.registerListener(this,countSensor, SensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(getActivity(), "sensor not found", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        running = false;
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
