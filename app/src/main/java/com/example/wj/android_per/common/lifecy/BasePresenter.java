package com.example.wj.android_per.common.lifecy;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;

public class BasePresenter implements IPresenter {

    @Override
    public void onCreate(LifecycleOwner owner) {
        Log.d("tag", "BasePresenter.onCreate" );
    }

    @Override
    public void onStart(LifecycleOwner owner) {
        Log.d("tag", "BasePresenter.onStart" );
    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        Log.d("tag", "BasePresenter.onDestroy" );

    }

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {//状态改变 通知
        Log.d("tag", "BasePresenter.Changed" );

    }
}
