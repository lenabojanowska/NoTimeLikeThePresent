package com.example.notimelikethepresent;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;


import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toolbar;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.notimelikethepresent.Adapter.ViewPagerAdapter;
import com.example.notimelikethepresent.Connection.API;
import com.example.notimelikethepresent.LocalStorage.TaskDatabase;
import com.example.notimelikethepresent.View.Fragment.MainFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    //public static TaskDatabase taskDatabase;

    private Toolbar toolbar;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        fragmentManager = getSupportFragmentManager();

       // taskDatabase = Room.databaseBuilder(getApplicationContext(),TaskDatabase.class, "")

        if(findViewById(R.id.fragment_container) != null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }
            fragmentManager.beginTransaction().add(R.id.fragment_container, new MainFragment()).commit();
        }

        //constraintLayout.setVisibility(View.VISIBLE);



//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
//            navigationView.setCheckedItem(R.id.itemMain);
//        }

        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            buildLocationRequest();
                            buildLocationCallBack();


                            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                                return;
                            }

                            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
                            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                        }

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).check();




        //((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10.0f);
    }


    private void buildLocationCallBack() {
        locationCallback = new LocationCallback() {

            @Override
            public void onLocationResult(LocationResult location) {


                API.location = location.getLastLocation();

                //constraintLayout = (ConstraintLayout) viewfindViewById(R.id.view);

               // viewPager = (ViewPager) findViewById(R.id.viewPager);

                Log.d("Location", location.getLastLocation().getLatitude() + "/" + location.getLastLocation().getLongitude());
            }



        };

    }





}
