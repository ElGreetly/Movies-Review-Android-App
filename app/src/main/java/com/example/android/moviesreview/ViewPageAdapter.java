package com.example.android.moviesreview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPageAdapter extends FragmentStatePagerAdapter {
    int mNumberOfTabs;
    public ViewPageAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        mNumberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1: SavedFragment savedMovies = new SavedFragment();
                return savedMovies;
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return mNumberOfTabs;
    }
}
