package com.sevenpeakssoftware.fott.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;

import com.sevenpeakssoftware.fott.R;
import com.sevenpeakssoftware.fott.databinding.ActivityFeedBinding;
import com.sevenpeakssoftware.fott.ui.base.BaseActivity;
import com.sevenpeakssoftware.fott.ui.fragments.FeedFragment;
import com.sevenpeakssoftware.fott.ui.utils.FragmentUtils;

/**
 * Created by razir on 1/3/2017.
 */

public class FeedActivity extends BaseActivity {

    ActivityFeedBinding binding;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, FeedActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        setupSideMenu();
        if (savedInstanceState == null) {
            FragmentUtils.changeFragment(this, R.id.content, FeedFragment.newInstance(), false);
        }
    }

    private void setupSideMenu() {
        binding.navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_feed:
                    closeMenu();
                    return true;

            }
            return false;
        });
    }

    private void closeMenu() {
        binding.drawerLayout.closeDrawers();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            binding.drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
