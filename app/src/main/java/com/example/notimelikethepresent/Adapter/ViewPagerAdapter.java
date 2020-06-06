package com.example.notimelikethepresent.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.notimelikethepresent.View.Fragment.CalendarFragment;
import com.example.notimelikethepresent.View.Fragment.HealthFragment;
import com.example.notimelikethepresent.View.Fragment.ToDoFragment;
import com.example.notimelikethepresent.View.Fragment.WeatherFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int tabNumber;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int tabNumber) {

        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabNumber = tabNumber;
    }

    @Override
    public Fragment getItem(int position) {

        switch(position) {
            case 0:
                 return new ToDoFragment();
            case 1:
                return new CalendarFragment();
            case 2:
                return new HealthFragment();
            case 3:
               return new WeatherFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabNumber;
    }

//    public CharSequence getPageTitle(int position){
//        return ta
//    }

}
