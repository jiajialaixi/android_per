package com.example.wj.android_per.common.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.example.wj.android_per.App;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ToastSnackbarUtiles {

    public static void show(Context context, String msg) {
        show(context, msg, true);
    }

    public static void show(Context context, int resid) {
        show(context, resid, true);
    }

    public static void show(Context context, String msg, boolean isShort) {
        try {
            if (isShort) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
        }
    }

    public static void show(Context context, int resid, boolean isShort) {
        try {
            if (isShort) {
                Toast.makeText(context, resid, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, resid, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
        }
    }
    @SuppressLint("CheckResult")
    public static void show(String message) {
        Observable.just(message).observeOn(AndroidSchedulers.mainThread()).subscribe(s ->
                        Toast.makeText(App.getContext(), s, Toast.LENGTH_SHORT).show()
                , throwable -> LogUtil.d(throwable));
    }
    @SuppressLint("CheckResult")
    public static void show(View view, String message) {
        Observable.just(message).observeOn(AndroidSchedulers.mainThread()).subscribe(s ->
                        Snackbar.make(view, s, Snackbar.LENGTH_SHORT).show()
                , throwable -> LogUtil.d(throwable));
    }


}
