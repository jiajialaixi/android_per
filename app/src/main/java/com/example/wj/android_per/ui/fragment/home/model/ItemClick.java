package com.example.wj.android_per.ui.fragment.home.model;




import com.example.wj.android_per.common.view.ToastSnackbarUtiles;

public class ItemClick {

    public void onSaveClick(String clickName){
        ToastSnackbarUtiles.show(clickName);
    }

}
