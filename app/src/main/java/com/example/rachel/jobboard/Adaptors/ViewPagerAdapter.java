package com.example.rachel.jobboard.Adaptors;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.rachel.jobboard.Fragments.ClosedJobsFragment;
import com.example.rachel.jobboard.Fragments.OpenJobsFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int numOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new OpenJobsFragment();
            case 1:
                return new ClosedJobsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
