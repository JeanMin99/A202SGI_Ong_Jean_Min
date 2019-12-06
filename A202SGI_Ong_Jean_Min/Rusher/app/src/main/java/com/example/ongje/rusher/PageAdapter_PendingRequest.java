package com.example.ongje.rusher;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter_PendingRequest extends FragmentPagerAdapter {

    private int numOfTabs;
    public PageAdapter_PendingRequest(FragmentManager fm, int numOfTabs)//Define var that hold size of tabs
    {
        super(fm);
        this.numOfTabs=numOfTabs;
    }

    @Override
    public Fragment getItem(int position) //To define number of fragment
    {
        switch (position)
        {
            case 0:
                return new FragmentPendingLeaveRequest();
            case 1:
                return new FragmentPendingRoomRequest();
            default:

                return null;
        }

    }

    @Override
    public int getCount() //To return correct num of tabs
    {
        return numOfTabs;
    }

}
