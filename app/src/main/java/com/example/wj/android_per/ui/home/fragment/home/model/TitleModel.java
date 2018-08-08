package com.example.wj.android_per.ui.home.fragment.home.model;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.airbnb.epoxy.DataBindingEpoxyModel;
import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.example.wj.android_per.R;
import com.example.wj.android_per.bean.ModelBean;
import com.example.wj.android_per.common.router.RouterViewUtil;
import com.example.wj.android_per.common.view.ImageBanner;
import com.example.wj.android_per.common.view.UPMarqueeView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wangjia on 2018/2/2.
 * TODO("标题")
 */

@EpoxyModelClass(layout = R.layout.item_title)
public abstract class TitleModel extends DataBindingEpoxyModel {
    @EpoxyAttribute
    List<ModelBean.BannerBean> requestBean;
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
    @Override
    protected void setDataBindingVariables(ViewDataBinding binding) {
        View root = binding.getRoot();
        UPMarqueeView uPMarqueeView = root.findViewById(R.id.uPMarqueeView);
        uPMarqueeView.setViews(setView(requestBean,root.getContext()));

    }
    private List<View> setView(List<ModelBean.BannerBean> requestBean,Context context) {
        List<View> views=new ArrayList<>();
        for (int i = 0; i < requestBean.size(); i ++) {
            //设置滚动的单个布局
            ConstraintLayout moreView = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.item_title_model_view, null);
            //初始化布局的控件
            AppCompatTextView tv_title_name =  moreView.findViewById(R.id.tv_title_name);
            //进行对控件赋值
            tv_title_name.setText(requestBean.get(i).title);
            int finalI = i;
            tv_title_name.setOnClickListener(v-> RouterViewUtil.open(context,requestBean.get(finalI).router));
            //添加到循环滚动数组里面去
            views.add(moreView);
        }
        return views;
    }
}
