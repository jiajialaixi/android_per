package com.example.wj.android_per.ui.fragment.home.model;

import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.DataBindingEpoxyModel;
import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.example.wj.android_per.BR;
import com.example.wj.android_per.R;
import com.example.wj.android_per.bean.RequestBean;
import com.example.wj.android_per.common.view.ToastSnackbarUtiles;

@EpoxyModelClass(layout = R.layout.item_view)
public abstract class HomePageItemModel extends DataBindingEpoxyModel {
    @EpoxyAttribute
    RequestBean requestBean;
    @EpoxyAttribute
    String clickName;

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
    @Override
    protected void setDataBindingVariables(ViewDataBinding binding) {
        binding.setVariable(BR.request, requestBean);//绑定数据
        binding.setVariable(BR.itemClick, new ItemClick());//绑定点击事件
        clickName="佳佳来袭";
        binding.setVariable(BR.clickName, clickName);//绑定字符串

        View root = binding.getRoot();
        TextView firstName = root.findViewById(R.id.firstName);
        firstName.setText("非著名程序员");
        firstName.setOnClickListener(v -> ToastSnackbarUtiles.show("点击。。。"));
    }



}
