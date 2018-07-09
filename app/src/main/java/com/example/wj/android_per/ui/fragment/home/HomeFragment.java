package com.example.wj.android_per.ui.fragment.home;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
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
import com.example.wj.android_per.bean.RequestBean;
import com.example.wj.android_per.ui.fragment.home.adapter.HomePageAdpater;
import com.example.wj.android_per.ui.fragment.home.viewmodel.HomeViewModel;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;


public class HomeFragment extends Fragment {


    EpoxyRecyclerView epoxyRecycleView;
    Unbinder unbinder;
    private HomePageAdpater homePageAdpater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        return inflate.getRoot();
    }

    public static String ARG_ITEM_ID = "item_id";//传递的key值

    //传递数据
    public static HomeFragment newInstance(String plantId) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_ITEM_ID, plantId);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        epoxyRecycleView = view.findViewById(R.id.epoxy_recycle_view);
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString(ARG_ITEM_ID);
        }
        init();
        data();
    }

    public void data() {
        HomeViewModel homeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        homeViewModel.getUser().observe(getActivity(), user -> {
            if (null != user ) {//处理数据
                homePageAdpater.setDate(user);
            }

        });
        homeViewModel.getLoader();
    }


    public void init() {
        homePageAdpater = new HomePageAdpater();
        epoxyRecycleView.setItemSpacingPx(5);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(homePageAdpater.getSpanSizeLookup());
        homePageAdpater.setSpanCount(2);
        epoxyRecycleView.setHasFixedSize(true);
        epoxyRecycleView.setLayoutManager(gridLayoutManager);
        epoxyRecycleView.setAdapter(homePageAdpater);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RefWatcher refWatcher = App.getRefWatcher(getActivity());
        refWatcher.watch(getActivity());
        if(null!=unbinder){
            unbinder.unbind();
        }
    }
}
