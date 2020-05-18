package com.rizky92.sportsdbapp.ui.activity.detail;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rizky92.sportsdbapp.ui.fragment.DescriptionFragment;
import com.rizky92.sportsdbapp.ui.fragment.LastMatchFragment;
import com.rizky92.sportsdbapp.ui.fragment.NextMatchFragment;
import com.rizky92.sportsdbapp.ui.fragment.TeamFragment;

public class TabPagerAdapter extends FragmentPagerAdapter {

    private final Context context;

    public TabPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    private final String[] TAB_TITLES = new String[] {
            "Description",
            "Teams",
            "Previous Match",
            "Next Match"
    };

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DescriptionFragment();
            case 1:
                return new TeamFragment();
            case 2:
                return new LastMatchFragment();
            case 3:
                return new NextMatchFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }
}
