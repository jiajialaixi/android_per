package com.example.wj.android_per.bean;

import java.util.List;

public class HomePageBean {
    private List<RequestBean> item;
    private List<RequestBean> banner;

    public List<RequestBean> getItem() {
        return item;
    }

    public void setItem(List<RequestBean> item) {
        this.item = item;
    }

    public List<RequestBean> getBanner() {
        return banner;
    }

    public void setBanner(List<RequestBean> banner) {
        this.banner = banner;
    }
}
