package com.xy.toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jason on 2017/7/11.
 */

public class TabLayoutActivity extends AppCompatActivity {

    private String[] titles = {"头条", "娱乐", "体育", "财经", "科技", "视频", "汽车", "房产", "政务"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        XYPagerAdapter pagerAdapter = new XYPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class XYPagerAdapter extends FragmentPagerAdapter {

        public XYPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            XYFragment xyFragment = new XYFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title",titles[position]);
            xyFragment.setArguments(bundle);
            return xyFragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }

}
