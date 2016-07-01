package com.robosoftin.assignment.myapplication.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.robosoftin.assignment.myapplication.R;
import com.robosoftin.assignment.myapplication.Utils.DialogUtils;
import com.robosoftin.assignment.myapplication.adapter.ViewPagerAdapter;
import com.robosoftin.assignment.myapplication.common.BaseActivity;


public class HomeScreenActivity extends BaseActivity {
    private static final String TAG = HomeScreenActivity.class.getSimpleName();
    private boolean mDoubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolBarTitle(toolbar, getString(R.string.home_screen));
        enableToolbarBackButton(true);
        initTabs();
    }

    private void initTabs() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        if (tabLayout != null) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CategoriesFragment(), getString(R.string.categories));
        adapter.addFragment(new DirectionsFragment(), getString(R.string.directions));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        if (mDoubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        mDoubleBackToExitPressedOnce = true;
        DialogUtils.showToast(this, getString(R.string.please_click_back_again_to_exit));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
