package com.example.ongje.rusher;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LecturerPagerAdapter extends FragmentPagerAdapter {

    FragmentManager fm;

    private int numOfTabsMain;
    public LecturerPagerAdapter(FragmentManager fm, int numOfTabsMain)
    {
        super(fm);
        this.numOfTabsMain = numOfTabsMain;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position)
    {
        // To return different fragment when select different tab position
        switch (position) {
            case 0:
                return new LecturerLeaveRequest();
            case 1:
                return new LecturerBookRequest();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return numOfTabsMain;
    }
}
