package com.example.wj.android_per.ui.home.fragment.home.adapter;

import com.airbnb.epoxy.EpoxyAdapter;
import com.airbnb.epoxy.EpoxyModel;


import java.util.List;

public class HomePageAdpater extends EpoxyAdapter {

    public void setDate(List<EpoxyModel<?>> list) {
        models.clear();
        addModels(list);
        notifyDataSetChanged();
    }


}
