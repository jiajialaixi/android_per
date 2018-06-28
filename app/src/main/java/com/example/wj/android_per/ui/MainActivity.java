package com.example.wj.android_per.ui;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;

import com.example.wj.android_per.R;
import com.example.wj.android_per.base.BaseActivity;
import com.example.wj.android_per.ui.fragment.home.HomeFragment;
import com.example.wj.android_per.ui.fragment.my.MyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment;
    private MyFragment myFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            hideFragments(transaction);
            switch (item.getItemId()) {
                case R.id.item1:
                    if (homeFragment == null) {
                        homeFragment = new HomeFragment();
                        transaction.add(R.id.fragment_container, homeFragment);
                    }
                    transaction.show(homeFragment);
                    break;
                case R.id.item2:
                    if (myFragment == null) {
                        myFragment = new MyFragment();
                        transaction.add(R.id.fragment_container, myFragment);
                    }
                    transaction.show(myFragment);
                    break;
            }
            transaction.commitAllowingStateLoss();
            return true;
        });
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            transaction.add(R.id.fragment_container, homeFragment);
        }
        transaction.show(homeFragment);
        transaction.commitAllowingStateLoss();
    }

    //切换
    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (myFragment != null) {
            transaction.hide(myFragment);
        }

    }
}
