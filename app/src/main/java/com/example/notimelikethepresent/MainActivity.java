package com.example.notimelikethepresent;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;


import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.notimelikethepresent.Adapter.ViewPagerAdapter;
import com.example.notimelikethepresent.View.Fragment.ToDoFragment;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity   {

    private ViewPagerAdapter viewPagerAdapter;

    private TabItem weatherTab,calendarTab,healthTab, toDoTab;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        tabLayout = findViewById(R.id.tabs);
        weatherTab = findViewById(R.id.weather_tab);
        calendarTab = findViewById(R.id.calendar_tab);
        healthTab = findViewById(R.id.health_tab);
        toDoTab = findViewById(R.id.to_do_tab);
        viewPager = findViewById(R.id.viewPager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    viewPagerAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 1) {
                    viewPagerAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 2) {
                    viewPagerAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 3) {
                    viewPagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }


}
