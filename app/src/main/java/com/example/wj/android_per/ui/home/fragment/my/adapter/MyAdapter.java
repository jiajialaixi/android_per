package com.example.wj.android_per.ui.home.fragment.my.adapter;

import com.airbnb.epoxy.EpoxyAdapter;
import com.example.wj.android_per.ui.home.fragment.my.Router.Model.FootModel_;
import com.example.wj.android_per.ui.home.fragment.my.Router.Model.MyHeaderModel_;

public class MyAdapter extends EpoxyAdapter{

    public void addModel(){
        MyHeaderModel_ myHeaderModel_ = new MyHeaderModel_();
        FootModel_ footModel_ = new FootModel_();
        addModel(myHeaderModel_);
        addModel(footModel_);
    }
}
