package com.example.notimelikethepresent.View.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.notimelikethepresent.Connection.API;
import com.example.notimelikethepresent.Connection.ServiceGenerator;
import com.example.notimelikethepresent.Connection.WeatherAPI;
import com.example.notimelikethepresent.Connection.WeatherResponse;
import com.example.notimelikethepresent.R;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.example.notimelikethepresent.Connection.API.App_id;

import static com.example.notimelikethepresent.Connection.API.location;

//import android.app.Fragment;


public class WeatherFragment extends Fragment {


    private ConstraintLayout constraintLayout;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;

    private static Context context = null;

    private TextView textViewCity, textViewTemp, textViewSth, textViewSth2,
                    textViewWind, textViewSunset, textViewSunrise, textViewCoord, textViewPressure, textViewHumidity;
    private ImageView imageViewWeather;

    CompositeDisposable compositeDisposable;
    WeatherAPI weatherAPI;

    public WeatherFragment(){
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = ServiceGenerator.getInstance();
        weatherAPI = retrofit.create(WeatherAPI.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        textViewCity = (TextView) view.findViewById(R.id.textViewCity);
        textViewTemp = (TextView) view.findViewById(R.id.textViewTemperature);
        textViewSth = (TextView) view.findViewById(R.id.textViewSth);
        textViewSth2 = (TextView) view.findViewById(R.id.textViewSth2);
        textViewWind = (TextView) view.findViewById(R.id.textViewWind);
        textViewSunrise = (TextView) view.findViewById(R.id.textViewSunrise);
        textViewSunset = (TextView) view.findViewById(R.id.textViewSunset);
        textViewHumidity = (TextView) view.findViewById(R.id.textViewHumidity);
        textViewCoord = (TextView) view.findViewById(R.id.textViewCoord);
        textViewPressure = (TextView) view.findViewById(R.id.textViewPressure);

        imageViewWeather = (ImageView) view.findViewById(R.id.imageViewWeather);

        getWeatherInformation();

        context = getActivity();

        //constraintLayout = (ConstraintLayout) view.findViewById(R.id.view);

        //constraintLayout.setVisibility(View.VISIBLE);


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        //((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Dexter.withActivity(getActivity())
                .withPermissions(Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {


                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            buildLocationRequest();
                            buildLocationCallBack();

                            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        Snackbar.make(constraintLayout, "Permission denied", Snackbar.LENGTH_LONG).show();
                    }
                }).check();




        return view;

    }

    private void getWeatherInformation() {
        compositeDisposable.add(weatherAPI.getWeatherByLocalization(String.valueOf(location),
                String.valueOf(location),
                App_id,
                "metric")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<WeatherResponse>() {
                       @Override
                       public void accept(WeatherResponse weatherResponse) throws Exception {

                           //Load image
                           Picasso.get().load(new StringBuilder("https://openweathermap.org/img/wn/")
                                   .append(weatherResponse.getWeather().get(0).getIcon())
                           .append(".png").toString()).into(imageViewWeather);

                           textViewCity.setText(weatherResponse.getName());
                           textViewTemp.setText(new StringBuilder(
                                   String.valueOf(weatherResponse.getMain().getTemp())).append("°C").toString());
                            textViewSth.setText(API.convertUnixToDate(weatherResponse.getDt()));
                            textViewPressure.setText(new StringBuilder(String.valueOf(weatherResponse.getMain().getPressure())).append("hpa").toString());
                            textViewHumidity.setText(new StringBuilder(String.valueOf(weatherResponse.getMain().getHumidity())).append("%").toString());textViewSunrise.setText(API.convertUnixToHour(weatherResponse.getSys().getSunrise()));
                           textViewSunrise.setText(API.convertUnixToHour(weatherResponse.getSys().getSunrise()));
                           textViewSunset.setText(API.convertUnixToHour(weatherResponse.getSys().getSunset()));
                           textViewCoord.setText(new StringBuilder("[").append(weatherResponse.getCoord().toString()).append("]").toString());
                       }
                   }, new Consumer<Throwable>() {
                       @Override
                       public void accept(Throwable throwable) throws Exception {
                           Toast.makeText(getActivity(), ""+throwable.getMessage(), Toast.LENGTH_SHORT);
                       }
                   }
        ));
    }

    private void buildLocationCallBack() {
       locationCallback = new LocationCallback(){

           @Override
           public void onLocationResult(LocationResult location) {
               super.onLocationResult(location);

               API.location =  location.getLastLocation();

               //constraintLayout = (ConstraintLayout) viewfindViewById(R.id.view);

               //Log
               Log.d("Location", location.getLastLocation().getLatitude() + "/" + location.getLastLocation().getLongitude());
           }

       };

    }

    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10.0f);
    }

}
