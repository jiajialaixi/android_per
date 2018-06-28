package com.example.wj.android_per.ui.fragment.home.adapter;

import com.airbnb.epoxy.EpoxyAdapter;
import com.example.wj.android_per.bean.RequestBean;
import com.example.wj.android_per.ui.fragment.home.model.BannerModel;
import com.example.wj.android_per.ui.fragment.home.model.BannerModel_;
import com.example.wj.android_per.ui.fragment.home.model.HomePageItemModel_;

import java.util.List;

public class HomePageAdpater extends EpoxyAdapter {

    public void setDate(List<RequestBean> list){

        BannerModel_ bannerModel_ = new BannerModel_();
        addModel(bannerModel_);
        for (int i = 0; i <list.size() ; i++) {
            HomePageItemModel_ homePageItemModel_=new HomePageItemModel_();
            homePageItemModel_.requestBean(list.get(i));
            addModel(homePageItemModel_);
        }
        notifyDataSetChanged();
    }

}
