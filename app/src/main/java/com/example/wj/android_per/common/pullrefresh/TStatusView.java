package com.example.wj.android_per.common.pullrefresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.example.wj.android_per.R;
import com.example.wj.android_per.common.view.ImageUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import retrofit2.HttpException;

public class TStatusView extends StatusView {


    private AppCompatImageView imError;
    private AppCompatImageView imLoading;
    private AppCompatTextView tvError;
    private AppCompatTextView tvTitle;

    public TStatusView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    RefreshLayout refreshLayout;
    public void setOnRefreshListener(RefreshLayout onRefreshListener) {
        this.refreshLayout = onRefreshListener;
    }
    @Override
    protected void init(Context context) {
        super.init(context);
        imError = findViewById(R.id.im_error);
        imLoading = findViewById(R.id.im_loading);
        tvError = findViewById(R.id.tv_error);
        tvTitle = findViewById(R.id.tv_title);

    }

    @Override
    public void showError(Throwable throwable) {
        super.showError(throwable);
        if (throwable instanceof HttpException) {
            if (((HttpException) throwable).code() == 504) {
                showEmpty(R.mipmap.ic_launcher, "嘿，哥们! 你的网线断了!");
            } else if (((HttpException) throwable).code() == 502) {
                showEmpty(R.mipmap.ic_launcher, "连接服务器失败，请重试!");
            } else {
                showEmpty(R.mipmap.ic_launcher, "请求出错，请检查网络后，点击重试");
            }
        } else {
            showEmpty(R.mipmap.ic_launcher, "淡定，那都不是事儿...");
        }
    }

    public void showEmpty(String flag,String text){
        showEmpty(R.mipmap.ic_launcher,text);
    }

    @Override
    public void showEmpty(int resource, String text) {
        super.showEmpty(resource, text);
        imLoading.setVisibility(GONE);
        setAlpha(1);
        imError.setVisibility(VISIBLE);
        tvTitle.setVisibility(GONE);
        tvError.setVisibility(VISIBLE);
        ImageUtil.displayImage(resource, imError);
        if (TextUtils.isEmpty(text)) {
            tvError.setText("空空如也!");
        } else {
            tvError.setText(text);
        }
    }

    @Override
    public void showLoading() {
        super.showLoading();
        if (imLoading != null) {
            imLoading.setVisibility(VISIBLE);
        }
        if (tvTitle != null) {
            tvTitle.setVisibility(VISIBLE);
        }
        if (tvError != null) {
            tvError.setVisibility(GONE);
        }
        if (imError != null) {
            imError.setVisibility(GONE);
        }
        imLoading.setImageResource(R.drawable.head_login);
        AnimationDrawable animationDrawable = (AnimationDrawable) imLoading.getDrawable();
        animationDrawable.start();
        tvTitle.setText("在路上了，马上到...");
    }


    public boolean isLoading() {
        return getVisibility() == VISIBLE;
    }

    @Override
    protected int getContentResource() {
        return R.layout.view_statusview;

    }
}
