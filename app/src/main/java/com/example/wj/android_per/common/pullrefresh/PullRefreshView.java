package com.example.wj.android_per.common.pullrefresh;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.example.wj.android_per.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class PullRefreshView extends CoordinatorLayout {


    protected TStatusView statusView;
    protected EpoxyRecyclerView recyclerView;
    protected RefreshLayout refreshLayout;


    public PullRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    protected void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.pull_view, this);
        refreshLayout = findViewById(R.id.refreshLayout);
        statusView = findViewById(R.id.t_status_view);
        recyclerView = findViewById(R.id.epoxy_recycler);
        refreshLayout.setEnableAutoLoadmore(false);//开启自动加载功能（非必须）
        refreshLayout. setEnableLoadmoreWhenContentNotFull(false);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    public EpoxyRecyclerView getRecyclerView() {
        return recyclerView;
    }
    //设置线条
    public void addItemDecoration(RecyclerView.ItemDecoration decor) {
        recyclerView.addItemDecoration(decor);
    }
    //移除
    public void removeItemDecoration(RecyclerView.ItemDecoration decor) {
        recyclerView.removeItemDecoration(decor);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (recyclerView.getLayoutManager() == null) {
            throw new RuntimeException("请先设置一个layoutManger");
        }
        recyclerView.setAdapter(adapter);
    }

    //上拉加载
    public void setLoadmoreListener(OnLoadmoreListener nextPageListener) {
        refreshLayout.setOnLoadmoreListener(nextPageListener);
    }
    public void setEnableLoadmore(boolean loadmore) {
        refreshLayout.setEnableLoadmore(loadmore);
    }
    public void setEnableRefresh(boolean loadmore) {
        refreshLayout.setEnableRefresh(loadmore);
    }
    //下拉刷新
    public void setOnRefreshListener(OnRefreshListener refreshListener) {
        refreshLayout.setEnableAutoLoadmore(false);//开启自动加载功能（非必须）
        refreshLayout.setOnRefreshListener(refreshListener);
        statusView.setOnRefreshListener(refreshLayout);

    }

    //显示加载框
    public void showLoading() {
        statusView.showLoading();
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setEnableRefresh(true);
    }

    //显示加载失败文案
    public void showEmpty(String emptyMessage) {
        statusView.showEmpty(emptyMessage, "");
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(false);
    }

    //显示加载失败文案
    public void showEmpty(String emptyMessage, String flag) {
        statusView.showEmpty(emptyMessage, flag);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(false);
    }

    //显示加载异常信息
    public void showError(Throwable throwable) {
        statusView.showError(throwable);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(false);
    }
    public void finishRefresh() {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
        refreshLayout.resetNoMoreData();
    }
    public void finishLoading() {
        statusView.finish();
    }
    public void finishLoadmore() {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
        refreshLayout.finishLoadmoreWithNoMoreData();
    }
    //设置列表颜色
    public void setColor(int color) {
        recyclerView.setBackgroundResource(color);
    }

    //设置列表位置
    public void scrollToPosition(int position) {
        recyclerView.scrollToPosition(position);
    }
}
