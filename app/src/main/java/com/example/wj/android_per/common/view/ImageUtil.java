package com.example.wj.android_per.common.view;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.wj.android_per.R;


/*
* @ imageUtil 图片加载
* */
public class ImageUtil {

    public static RequestOptions apply(){
        RequestOptions requestOptions=new RequestOptions();
        requestOptions.centerCrop();//裁剪
        requestOptions.error(R.mipmap.ic_launcher);//失败图片
        return requestOptions;
    }
    public static RequestOptions applyNoError(){
        RequestOptions requestOptions=new RequestOptions();
        requestOptions.centerCrop();//裁剪
        requestOptions.error(R.mipmap.ic_launcher);//失败图片
        return requestOptions;
    }

    @BindingAdapter("android:image")
    public static void setImageUrl(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .apply(apply())
                .thumbnail(0.05f)
                .into(view);
    }

    public static void displayImage( @NonNull int resource, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(resource)
                .apply(applyNoError())
                .thumbnail(0.05f)
                .into(imageView);
    }
}
