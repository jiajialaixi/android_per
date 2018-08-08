package com.example.wj.android_per.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;


public class RequestBean extends BaseObservable {
    private String title;
    private String moe;
    private String image;
    public RequestBean() {
    }

    public RequestBean(String title, String moe, String image) {
        this.title = title;
        this.moe = moe;
        this.image = image;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public String getMoe() {
        return moe;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setMoe(String moe) {
        this.moe = moe;

    }


    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
