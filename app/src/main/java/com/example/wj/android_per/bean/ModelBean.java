package com.example.wj.android_per.bean;

import android.databinding.BaseObservable;

import java.util.List;

public class ModelBean extends BaseObservable{
    public String modelTitle;//模板类型
    public int modelId;//模板id
    public int _id;
    public List<BannerBean> store_banner;


    public static class BannerBean {

        public String imagePath;//图片
        public String title;//文字描述
        public int count;
        public String router;//路由跳转
        public int _id;//路由跳转

    }
}
