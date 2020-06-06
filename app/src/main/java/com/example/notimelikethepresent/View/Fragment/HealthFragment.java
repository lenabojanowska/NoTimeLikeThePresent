package com.example.notimelikethepresent.View.Fragment;

import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.notimelikethepresent.R;

import static android.content.Context.SENSOR_SERVICE;

public class HealthFragment extends Fragment {
    private ImageView bottleImage;
    private TextView counterView;
    //private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;
    private AnimationDrawable bottleAnimation;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health, container, false);
//
////        ImageView imageView = view.findViewById(R.id.bottleImage);
////        //((AnimationDrawable) bottleImage.getBackgroundResource()).start();
////        imageView.setBackgroundResource(R.drawable.animation_bottle_filling);
//
//       // bottleAnimation = (AnimationDrawable) imageView.getBackground();
//
//        counterView = new TextView(getActivity().getApplicationContext());
//        counterView.setTextSize(30);
//        // setContentView(textView);
//
//        // Get an instance of the SensorManager
//        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
//        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//       // simpleStepDetector = new StepDetector();
//        //simpleStepDetector.registerListener(this);
//
//
//        bottleImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottleAnimation.start();
//            }
//        });
//
//        //animatedSelector = view.findViewById(R.id.imageview_animated_selector);
//        // bottleImage = view .findViewById(R.id.bottleImage);
//        //counterView = view.findViewById(R.id.counterView);
//
//
//
//
////        @Override
////        public void onResume() {
////            super.onResume();
////            numSteps = 0;
////            counterView.setText(TEXT_NUM_STEPS + numSteps);
////            sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_FASTEST);
////        }
////
////        @Override
////        public void onPause() {
////            super.onPause();
////            sensorManager.unregisterListener(this);
////        }
////
////        @Override
////        public void onAccuracyChanged(Sensor sensor, int accuracy) {
////        }
////
////        @Override
////        public void onSensorChanged(SensorEvent event) {
////            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
////                simpleStepDetector.updateAccel(
////                        event.timestamp, event.values[0], event.values[1], event.values[2]);
////            }
////        }
////
////
////        public void step(long timeNs) {
////            numSteps++;
////            counterView.setText(TEXT_NUM_STEPS + numSteps);
////        }
////


        return view;
    }

}
