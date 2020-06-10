package com.example.notimelikethepresent.Connection;

import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;

public class API {
    public static final String App_id = "117294ceca25a94e9addb22e95298597";

    public static Location location = null;


    public static String convertUnixToDate(long dt) {
        Date date = new Date(dt*1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm EEE MM yyyy");
        String formatted = simpleDateFormat.format(date);
        return formatted;
    }

    public static String convertUnixToHour(long sunrise) {
        Date date = new Date(sunrise*1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String formatted = simpleDateFormat.format(date);
        return formatted;
    }
}
