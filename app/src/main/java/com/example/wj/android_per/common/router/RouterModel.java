package com.example.wj.android_per.common.router;

import com.airbnb.epoxy.EpoxyModel;
import com.example.wj.android_per.bean.ModelBean;
import com.example.wj.android_per.ui.home.fragment.home.model.BannerModel_;
import com.example.wj.android_per.ui.home.fragment.home.model.TitleModel_;

import java.util.ArrayList;
import java.util.List;

public class RouterModel {

    public static List<EpoxyModel<?>>  routerModel(List<ModelBean> list) {
        List<EpoxyModel<?>> models = new ArrayList<>();
        for (ModelBean item : list) {

            switch (item.modelTitle) {
                case "home_banner"://banner
                    BannerModel_ bannerModel_ = new BannerModel_();
                    bannerModel_.requestBean(item.store_banner);
                    models.add(bannerModel_);
                    break;
                case "home_news"://头条
                    TitleModel_ titleModel_ = new TitleModel_();
                    titleModel_.requestBean(item.store_banner);
                    models.add(titleModel_);
                    break;
                case "home_product"://详情

                    break;
                case "home_classification"://分类

                    break;
                case "home_special"://专题

                    break;
                case "my_head_portrait"://头像

                    break;
                default:

                    break;
            }
        }
        return models;
    }
}
