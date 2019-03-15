package by.umdom.serviceclientviewpagerumdom1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import by.umdom.serviceclientviewpagerumdom1.fragment.FragmentBase;
import by.umdom.serviceclientviewpagerumdom1.fragment.FragmentBedroom;
import by.umdom.serviceclientviewpagerumdom1.fragment.FragmentCanteen;
import by.umdom.serviceclientviewpagerumdom1.fragment.FragmentHol;
import by.umdom.serviceclientviewpagerumdom1.fragment.FragmentOutside;
import by.umdom.serviceclientviewpagerumdom1.fragment.FragmentRooms;
import by.umdom.serviceclientviewpagerumdom1.fragment.FragmentSettings;

/**
 * Created by abdalla on 2/18/18.
 */

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentRooms();
            case 1:
                return new FragmentBedroom();
            case 2:
                return new FragmentHol();
            case 3:
                return new FragmentCanteen();
            case 4:
                return new FragmentBase();
            case 5:
                return new FragmentOutside();
            case 6:
                return new FragmentSettings();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
