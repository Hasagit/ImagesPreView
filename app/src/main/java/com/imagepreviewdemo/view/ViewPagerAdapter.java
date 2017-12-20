package com.imagepreviewdemo.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NewHT on 2016/10/5.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<PreViewFragment> mFragments = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     *
     * @param fragment  添加Fragment

     */
    public void addFragment(PreViewFragment fragment){
        mFragments.add(fragment);
        //mFragmentTitle.add(fragmentTitle);
    }

    @Override
    public PreViewFragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

}
