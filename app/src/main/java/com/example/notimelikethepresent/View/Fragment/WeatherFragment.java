package com.example.notimelikethepresent.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.example.notimelikethepresent.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;

//import android.app.Fragment;


public class WeatherFragment extends Fragment {
    //private TabLayout tabLayout;
    // private Toolbar toolbar;
    //private ViewPager viewPager;

    private CoordinatorLayout coordinatorLayout;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;

    private static Context context = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
//
//        context=getActivity();
//
//        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.root_view);
//
//        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
//
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        Dexter.withActivity(getActivity())
//                .withPermissions(Manifest.permission.ACCESS_COARSE_LOCATION,
//                        Manifest.permission.ACCESS_FINE_LOCATION)
//                .withListener(new MultiplePermissionsListener(){
//
//                    @Override
//                    public void onPermissionsChecked(MultiplePermissionsReport report) {
//                        if(report.areAllPermissionsGranted()){
//                            buildLocationRequest();
//                            buildLocationCallBack();
//
//                            if(ContextCompat.checkSelfPermission(WeatherFragment.context.getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//                                return;
//                            }
//                            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
//                        }
//
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//                        Snackbar.make(coordinatorLayout,"Permission Denied",Snackbar.LENGTH_LONG)
//                                .show();
//                    }
//                }).check();

        return view;
    }
}

//    private void buildLocationCallBack() {
//        locationCallback = new LocationCallback(){
//
//            @Override
//            public void onLocationResult(LocationResult location){
//                super.onLocationResult(location);
//
//                API.current_location = location.getLastLocation();
//
//                viewPager = (ViewPager) viewPager.findViewById(R.id.view_pager);
//                setupViewPager(viewPager);
//                tabLayout = (TabLayout) tabLayout.findViewById(R.id.tabs);
//                tabLayout.setupWithViewPager(viewPager);
//
//                //Log
//                Log.d("Location", location.getLastLocation().getLatitude()+ "/"+location.getLastLocation().getLongitude());
//            }
//        };
//
//
//    }
//
//    private void setupViewPager(ViewPager viewPager) {
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter (getFragmentManager());
//        viewPagerAdapter.addFragment(TodayFragment.getInstance(), "Today Weather");
//        viewPager.setAdapter(viewPagerAdapter);
//
//    }
//
//    private void buildLocationRequest() {
//        locationRequest = new LocationRequest();
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.getSmallestDisplacement();
//        locationRequest.setInterval(5000);
//        locationRequest.setFastestInterval(3000);
//
//
//    }
//}
