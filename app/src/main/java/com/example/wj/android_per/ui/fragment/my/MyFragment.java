package com.example.wj.android_per.ui.fragment.my;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wj.android_per.R;
import com.example.wj.android_per.common.pullrefresh.PullRefreshView;
import com.example.wj.android_per.common.view.ToastSnackbarUtiles;
import com.example.wj.android_per.ui.fragment.my.adapter.MyAdapter;
import com.example.wj.android_per.ui.fragment.my.viewmodel.MyViewModel;


public class MyFragment extends Fragment {


    private MyAdapter myAdapter;
    private MyViewModel myViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.fragment_my, container, false);
        return inflate.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PullRefreshView epoxyRecycleView = view.findViewById(R.id.epoxy_recycle_view);
        myViewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);

        epoxyRecycleView.getRecyclerView().setItemSpacingPx(5);
        epoxyRecycleView.getRecyclerView().setHasFixedSize(true);
        epoxyRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myAdapter = new MyAdapter();
        myAdapter.addModel();
        epoxyRecycleView.setAdapter(myAdapter);
        epoxyRecycleView.showLoading();

        epoxyRecycleView.setOnRefreshListener(refreshlayout -> {
            epoxyRecycleView.finishRefresh();
            ToastSnackbarUtiles.show(view, "刷新数据...");
        });


        epoxyRecycleView.setEnableLoadmore(false);
        epoxyRecycleView.finishLoading();
    }
    private void  updateData() {

    }
    public void init() {
        myViewModel.getUser().observe(getActivity(), user -> {
            if (null != user) {//处理数据
                if(myAdapter!=null){
                    myAdapter.addModel();
                }
            }
        });

        myViewModel.getLoader();
    }
}
