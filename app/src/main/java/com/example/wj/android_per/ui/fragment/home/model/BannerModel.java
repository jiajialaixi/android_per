package com.example.wj.android_per.ui.fragment.home.model;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.airbnb.epoxy.DataBindingEpoxyModel;
import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.example.wj.android_per.R;
import com.example.wj.android_per.bean.RequestBean;
import com.example.wj.android_per.common.view.ImageBanner;
import com.example.wj.android_per.common.view.ToastSnackbarUtiles;

import java.util.ArrayList;
import java.util.List;

@EpoxyModelClass(layout = R.layout.item_banner)
public abstract class BannerModel extends DataBindingEpoxyModel {
    @EpoxyAttribute
    List<RequestBean> requestBean;
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
    @Override
    protected void setDataBindingVariables(ViewDataBinding binding) {
        View root = binding.getRoot();
        ImageBanner imageBanner = root.findViewById(R.id.image_banner);
        List<String> list = new ArrayList<>();
        for (int i = 0; i <requestBean.size() ; i++) {
            list.add(requestBean.get(i).getImage());
        }
        imageBanner.setList(list);
        imageBanner.setOnBannerClickListener(pos -> {
            ToastSnackbarUtiles.show(imageBanner,pos+"");
        });
    }
}
