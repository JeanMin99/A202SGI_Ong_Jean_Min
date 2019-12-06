package com.example.ongje.rusher;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter_MainActivity extends FragmentPagerAdapter {
    FragmentManager fm;
    private int numOfTabsMain;
    public PageAdapter_MainActivity(FragmentManager fm, int numOfTabsMain)
    {
        super(fm);
        this.numOfTabsMain = numOfTabsMain;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position)//To define number of fragment and return accordingly when user swipe through different fragment
    {
        switch (position) {
            case 0:
                return new FragmentShuttle();
            case 1:
                return new FragmentLeave();
            case 2:
                return new FragmentFoodReservation();
            case 3:
                return new FragmentBook();
            default:
                return null;
        }
    }
    @Override
    public int getCount()//To return correct num of tabs
    {
        return numOfTabsMain;
    }
}
