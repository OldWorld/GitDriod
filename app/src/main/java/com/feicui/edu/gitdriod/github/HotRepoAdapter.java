package com.feicui.edu.gitdriod.github;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.feicui.edu.gitdriod.github.view.Language;

import java.util.List;


public class HotRepoAdapter extends FragmentPagerAdapter {

    private List<Language> languages;

    public HotRepoAdapter(FragmentManager fm,Context context) {

        super(fm);
        languages = Language.getDefaultLanguage(context);
    }

    @Override public Fragment getItem(int position) {
        return HotRepoListFragment.getInstance(languages.get(position));
    }

    @Override public int getCount() {
        return languages==null?0:languages.size();
    }

    @Override public CharSequence getPageTitle(int position) {
        return languages.get(position).getName();
    }
}
