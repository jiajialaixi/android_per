package com.example.wj.android_per.ui.fragment.home.adapter;

import com.airbnb.epoxy.EpoxyAdapter;
import com.example.wj.android_per.bean.HomePageBean;
import com.example.wj.android_per.bean.RequestBean;
import com.example.wj.android_per.ui.fragment.home.model.BannerModel;
import com.example.wj.android_per.ui.fragment.home.model.BannerModel_;
import com.example.wj.android_per.ui.fragment.home.model.HomePageItemModel_;

import java.util.List;

public class HomePageAdpater extends EpoxyAdapter {

    public void setDate(HomePageBean list) {

        if (null != list) {
            if (null != list.getBanner() && list.getBanner().size() > 0) {
                BannerModel_ bannerModel_ = new BannerModel_();
                bannerModel_.requestBean(list.getBanner());
                addModel(bannerModel_);
            }
            if (null != list.getItem() && list.getItem().size() > 0) {
                for (int i = 0; i < list.getItem().size(); i++) {
                    HomePageItemModel_ homePageItemModel_ = new HomePageItemModel_();
                    homePageItemModel_.requestBean(list.getItem().get(i));
                    addModel(homePageItemModel_);
                }
            }


        }
        notifyDataSetChanged();
    }

}
