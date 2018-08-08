package com.example.wj.android_per.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.example.wj.android_per.R;
import com.example.wj.android_per.base.BaseActivity;
import com.example.wj.android_per.bean.HomeEvent;
import com.example.wj.android_per.common.eventbus.IEventBus;
import com.example.wj.android_per.common.lifecy.BasePresenter;
import com.example.wj.android_per.ui.home.fragment.home.HomeFragment;
import com.example.wj.android_per.ui.home.fragment.my.MyFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements IEventBus{

    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment;
    private MyFragment myFragment;
    private BasePresenter basePresenter;


    public static void open (Context context){
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        basePresenter = new BasePresenter();
        getLifecycle().addObserver(basePresenter);//添加LifecycleObserver
        ButterKnife.bind(this);
        if(savedInstanceState==null){
            init();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    @SuppressWarnings("unused")
    public void onLoginStatusChangeEvent(HomeEvent event) {
        initFragment(event.getFlag());
    }

    //显示fragment
    public void initFragment(String fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);
        switch (fragment){
            case "home":
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance("jiajialaixi");
                    transaction.add(R.id.fragment_container, homeFragment);
                }
                transaction.show(homeFragment);
                break;
            case "my":
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    transaction.add(R.id.fragment_container, myFragment);
                }
                transaction.show(myFragment);
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss();
    }

   //初始化显示fragment
    public void init() {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            String flag="";
            if(item.getItemId()==R.id.item1){
                flag="home";
            }else if(item.getItemId()==R.id.item2){
                flag="my";
            }else{
            }
            initFragment(flag);
            return true;
        });
        initFragment("home");
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
    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (homeFragment == null && fragment instanceof HomeFragment)
            homeFragment = (HomeFragment) fragment;
        if (myFragment == null && fragment instanceof MyFragment)
            myFragment = (MyFragment) fragment;
    }

}
