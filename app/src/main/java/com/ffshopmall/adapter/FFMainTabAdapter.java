package com.ffshopmall.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ffshopmall.R;
import com.ffshopmall.view.FFMainTabFragment;
import com.viewpagerindicator.IconPagerAdapter;

/**
 * Created by Baymax on 2017/3/8.
 */

public class FFMainTabAdapter extends FragmentPagerAdapter implements IconPagerAdapter {

    public static String[] CONTENT = new String[]{
            "主页", "购物中心"
    };
    public static int[] ICONS = new int[]{
            R.drawable.home_outline, R.drawable.shopping,
    };

    public FFMainTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        FFMainTabFragment fragment = new FFMainTabFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos",position);
        fragment.setArguments(bundle);
        return fragment;
    }

    public CharSequence getPageTitle(int position){

        return CONTENT[position];
    }

    @Override
    public int getIconResId(int index) {
        return ICONS[index];
    }

    @Override
    public int getCount() {
        return CONTENT.length;
    }
}
