package com.example.wj.android_per.ui.home.fragment.home;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.example.wj.android_per.App;
import com.example.wj.android_per.R;
import com.example.wj.android_per.bean.ModelBean;
import com.example.wj.android_per.bean.RequestBean;
import com.example.wj.android_per.common.pullrefresh.PullRefreshView;
import com.example.wj.android_per.common.router.RouterModel;
import com.example.wj.android_per.common.view.ToastSnackbarUtiles;
import com.example.wj.android_per.dao.HomeViewModelFactory;
import com.example.wj.android_per.dao.InjectorUtils;
import com.example.wj.android_per.ui.home.fragment.home.adapter.HomePageAdpater;
import com.example.wj.android_per.ui.home.fragment.home.viewmodel.HomeViewModel;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    PullRefreshView epoxyRecycleView;
    private HomePageAdpater homePageAdpater;
    private static final String ARG_ITEM_ID = "item_id";//传递的key值
    private String plantId="jiajialaixi";
    private HomeViewModel homeViewModel;

    //传递数据
    public static HomeFragment newInstance(String plantId) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_ITEM_ID, plantId);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            plantId = arguments.getString(ARG_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return inflate.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view!=null){
            epoxyRecycleView = view.findViewById(R.id.epoxy_recycle_view);
        }
        init();
        data();
    }

    public void data() {

        HomeViewModelFactory homeViewModelFactory = InjectorUtils.provideHomeViewModelFactory(getActivity(),plantId);
        homeViewModel = ViewModelProviders.of(getActivity(),homeViewModelFactory).get(HomeViewModel.class);
        homeViewModel.getUser().observe(getActivity(), user -> {
            if (null != user ) {//处理数据
                homePageAdpater.setDate(RouterModel.routerModel(user));
            }

        });
        homeViewModel.getLoader();
    }


    public void init() {
        homePageAdpater = new HomePageAdpater();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(homePageAdpater.getSpanSizeLookup());
        homePageAdpater.setSpanCount(2);
        epoxyRecycleView.setLayoutManager(gridLayoutManager);
        epoxyRecycleView.setAdapter(homePageAdpater);
        epoxyRecycleView.showLoading();
        epoxyRecycleView.getRecyclerView().setBackgroundResource(R.color.colorAccent);
        epoxyRecycleView.setOnRefreshListener(refreshlayout -> {
            epoxyRecycleView.finishRefresh();
            if(homeViewModel!=null){
                homeViewModel.getLoader();
            }
            ToastSnackbarUtiles.show(epoxyRecycleView, "刷新数据...");
        });
        epoxyRecycleView.setEnableLoadmore(false);
        epoxyRecycleView.finishLoading();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RefWatcher refWatcher = App.getRefWatcher(getActivity());
        refWatcher.watch(getActivity());
    }
}
