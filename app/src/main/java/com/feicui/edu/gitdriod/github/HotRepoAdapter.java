package com.feicui.edu.gitdriod.github;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class HotRepoAdapter extends FragmentPagerAdapter {

    public HotRepoAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override public Fragment getItem(int position) {
        return new HotRepoListFragment();
    }

    @Override public int getCount() {
        return 10;
    }

    @Override public CharSequence getPageTitle(int position) {
        return "Java"+position;
    }
}
