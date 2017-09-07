package com.sirimarco.terminiello.unlp.homecontroller.ui.home;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sirimarco.terminiello.unlp.homecontroller.model.House;

public class HomePagerAdapter extends FragmentStatePagerAdapter{

    private House house;

    public HomePagerAdapter(FragmentManager fm, House house) {
        super(fm);
        this.house = house;
    }

    @Override
    public Fragment getItem(int position) {
        return RoomFragment.newInstance(house.getRooms().get(position));
    }

    @Override
    public int getCount() {
         return house.getRooms().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return house.getRooms().get(position).getName();
    }
}
