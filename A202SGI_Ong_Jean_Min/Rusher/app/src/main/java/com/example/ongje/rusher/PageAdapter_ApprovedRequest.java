package com.example.ongje.rusher;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter_ApprovedRequest extends FragmentPagerAdapter {

    FragmentManager fm;
    private int numOfTabs;
    public PageAdapter_ApprovedRequest(FragmentManager fm, int numOfTabs)//Define var that hold size of tabs
    {
        super(fm);
        this.numOfTabs=numOfTabs;
    }

    @Override
    public Fragment getItem(int position) //To define number of fragment and return accordingly when user swipe through different fragment
    {
        switch (position)
        {
            case 0:
                return new FragmentApprovedFoodReserve();
            case 1:
                return new FragmentApproved_ShuttleRequest();
            case 2:
                return new ApprovedLeaveRequest();
            case 3:
                return new ApprovedBookRequest();
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
