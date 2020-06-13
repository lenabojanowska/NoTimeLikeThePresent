//package com.example.notimelikethepresent.Repository;
//
//import android.app.Application;
//
//import androidx.lifecycle.MutableLiveData;
//
//import com.example.notimelikethepresent.Connection.Entities.Weather;
//import com.example.notimelikethepresent.Connection.ServiceGenerator;
//import com.example.notimelikethepresent.Connection.WeatherAPI;
//
//import retrofit2.Call;
//
//public class WeatherRepository {
//
//    private static WeatherRepository instance;
//    private MutableLiveData<Weather> weather;
//    private WeatherRepository weatherRepository;
//
//    private WeatherRepository(Application application) {
//        weather = new MutableLiveData<>();
//        weatherRepository = WeatherRepository.getInstance(application);
//    }
//
//    public static synchronized WeatherRepository getInstance(Application application) {
//        if (instance == null)
//            instance = new WeatherRepository(application);
//        return instance;
//    }
//
//    public MutableLiveData<Weather> getMutableLive Data(){
//        WeatherAPI weatherAPI = ServiceGenerator.getInstance();
//        Call<Weather> call = weatherAPI.getWeatherByLocalization( );
//        call.enqueue(new Callback<BlogWrapper>() {
//            @Override
//            public void onResponse(Call<BlogWrapper> call, Response<BlogWrapper> response) {
//                BlogWrapper mBlogWrapper = response.body();
//                if (mBlogWrapper != null && mBlogWrapper.getBlog() != null) {
//                    movies = (ArrayList<Blog>) mBlogWrapper.getBlog();
//                    mutableLiveData.setValue(movies);
//                }
//            }
//            @Override
//            public void onFailure(Call<BlogWrapper> call, Throwable t) { }
//        });
//        return mutableLiveData;
//    }
//    }
//}
