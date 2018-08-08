package com.example.wj.android_per.ui.home.fragment.my.Router.Model;

import android.databinding.ViewDataBinding;

import com.airbnb.epoxy.DataBindingEpoxyModel;
import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.example.wj.android_per.BR;
import com.example.wj.android_per.R;

@EpoxyModelClass(layout = R.layout.foot_view_model)
public abstract class FootModel extends DataBindingEpoxyModel {

    @EpoxyAttribute
    String clickName;
    @Override
    protected void setDataBindingVariables(ViewDataBinding binding) {
        clickName = "dataBinding";
        binding.setVariable(BR.clickName, clickName);//绑定字符串
    }
}
