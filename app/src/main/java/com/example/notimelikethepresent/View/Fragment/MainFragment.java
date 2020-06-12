package com.example.notimelikethepresent.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.notimelikethepresent.Adapter.ViewPagerAdapter;
import com.example.notimelikethepresent.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainFragment extends Fragment {
    private TabItem weatherTab, calendarTab, healthTab, toDoTab;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragemnt_main, container, false);
        tabLayout = view.findViewById(R.id.tabs);
        weatherTab = view.findViewById(R.id.weather_tab);
        calendarTab = view.findViewById(R.id.calendar_tab);
        healthTab = view.findViewById(R.id.health_tab);
        toDoTab = view.findViewById(R.id.to_do_tab);
        viewPager = view.findViewById(R.id.viewPager);

        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
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


        return view;
    }
}

