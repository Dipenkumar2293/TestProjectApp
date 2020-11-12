package com.dipen.testprojectapp.ui.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.fragment.NavHostFragment;

import com.dipen.testprojectapp.DailyListFragment;
import com.dipen.testprojectapp.MonthlyFragment;
import com.dipen.testprojectapp.WeeklyFragment;

public class PagerAdaper extends FragmentPagerAdapter {
    private int tabsNumber;

    public PagerAdaper(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DailyListFragment();
            case 1:
                return new WeeklyFragment();
            case 2:
               return new MonthlyFragment();
            default:
                return new NavHostFragment();
        }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
