package com.example.wj.android_per.ui.fragment.home;


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
import com.example.wj.android_per.R;
import com.example.wj.android_per.bean.RequestBean;
import com.example.wj.android_per.common.view.AutoGridLayoutManager;
import com.example.wj.android_per.ui.fragment.home.adapter.HomePageAdpater;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeFragment extends Fragment {


    @BindView(R.id.epoxy_recycle_view)
    EpoxyRecyclerView epoxyRecycleView;
    Unbinder unbinder;
    private HomePageAdpater homePageAdpater;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homePageAdpater = new HomePageAdpater();
      /*  GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setSpanSizeLookup(homePageAdpater.getSpanSizeLookup());*/
        epoxyRecycleView.setItemSpacingPx(5);
        epoxyRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        epoxyRecycleView.setAdapter(homePageAdpater);


        init();
    }

    public void init(){
        List<RequestBean> list = new ArrayList<>();
        list.add(new RequestBean("databing", "mvvm", "http://img.taopic.com/uploads/allimg/121019/234917-121019231h258.jpg"));
        list.add(new RequestBean("优化", "mvvm", "http://img.taopic.com/uploads/allimg/121019/234917-121019231h258.jpg"));
        homePageAdpater.setDate(list);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
