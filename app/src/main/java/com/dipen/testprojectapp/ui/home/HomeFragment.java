package com.dipen.testprojectapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.dipen.testprojectapp.AddEventFragment;
import com.dipen.testprojectapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {
     private HomeViewModel homeViewModel;
     ViewPager pager;
     PagerAdaper adapter;
     TabLayout mTabs;
     TabItem tab1, tab2, tab3;
     FloatingActionButton fab1;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        pager = root.findViewById(R.id.view_Pager);
        mTabs = root.findViewById(R.id.tab_layout);
        tab1 = root.findViewById(R.id.daily_tab);
        tab2 = root.findViewById(R.id.weekly_tab);
        tab3 = root.findViewById(R.id.montly_tab);
        fab1 = root.findViewById(R.id.fab);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                adapter = new PagerAdaper(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                mTabs.getTabCount());
                pager.setAdapter(adapter);
                mTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        pager.setCurrentItem(tab.getPosition());
                    }
                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                    }
                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                });
                pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabs));
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.NavigateToAddEvent);
            }
        });
        return root;
    }
}