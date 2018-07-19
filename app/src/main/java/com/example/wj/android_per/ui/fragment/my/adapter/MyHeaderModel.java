package com.example.wj.android_per.ui.fragment.my.adapter;

import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.DataBindingEpoxyModel;
import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.example.wj.android_per.BR;
import com.example.wj.android_per.R;


@EpoxyModelClass(layout = R.layout.header_view_model)
public abstract class MyHeaderModel extends DataBindingEpoxyModel {

     @EpoxyAttribute
     String clickName;

    @Override
    protected void setDataBindingVariables(ViewDataBinding binding) {
        clickName = "佳佳来袭";
        binding.setVariable(BR.clickName, clickName);//绑定字符串

    }
}
