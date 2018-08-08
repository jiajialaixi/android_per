package com.example.wj.android_per.common.router;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.example.wj.android_per.common.view.ToastSnackbarUtiles;
import com.example.wj.android_per.ui.home.MainActivity;
import com.example.wj.android_per.ui.login.LoginActivity;
import com.example.wj.android_per.ui.web.WebActivity;

public class RouterViewUtil {

    public static void open(Context context, String path) {
        if (TextUtils.isEmpty(path)) {
            ToastSnackbarUtiles.show("路径不能为空...");
            return;
        }
        Uri parse = Uri.parse(path);
        // Uri parse = Uri.parse("per://store/login?cid=1001091000&sid=100109100010001");
        String scheme = parse.getScheme();
        String host = parse.getHost();
        if (scheme.equals("per") && host.equals("store")) {
            switch (parse.getPath()) {
                case "/login":
                    LoginActivity.open(context);
                    break;
                case "/home":
                    MainActivity.open(context);
                    break;
                case "/web":
                    WebActivity.open(context,parse.getQuery());
                    break;
                default:
                    ToastSnackbarUtiles.show("对不起你暂时没有升级到最新版本，无法体验相应的功能");
                    break;
            }

        }
    }
}
