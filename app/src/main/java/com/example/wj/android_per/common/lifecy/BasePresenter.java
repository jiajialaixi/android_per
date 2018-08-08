package com.example.wj.android_per.common.lifecy;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

import com.example.wj.android_per.common.view.LogUtil;

public class BasePresenter implements IPresenter {

    @Override
    public void onCreate(LifecycleOwner owner) {
        LogUtil.d("BasePresenter.onCreate");
    }

    @Override
    public void onStart(LifecycleOwner owner) {
        LogUtil.d("BasePresenter.onStart" );
    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        LogUtil.d("BasePresenter.onDestroy");
    }

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {//状态改变 通知
        LogUtil.d("BasePresenter.Changed");
    }
}
