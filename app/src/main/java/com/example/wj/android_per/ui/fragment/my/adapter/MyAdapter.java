package com.example.wj.android_per.ui.fragment.my.adapter;

import com.airbnb.epoxy.EpoxyAdapter;

public class MyAdapter extends EpoxyAdapter{

    public void addModel(){
        MyHeaderModel_ myHeaderModel_ = new MyHeaderModel_();
        FootModel_ footModel_ = new FootModel_();
        addModel(myHeaderModel_);
        addModel(footModel_);
    }
}
