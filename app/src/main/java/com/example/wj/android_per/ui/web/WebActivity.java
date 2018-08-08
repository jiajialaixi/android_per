package com.example.wj.android_per.ui.web;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.wj.android_per.R;
import com.example.wj.android_per.base.BaseActivity;
import com.example.wj.android_per.ui.web.fragment.WebFragment;

public class WebActivity extends BaseActivity {


    public static void open(Context context,String url){
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Fragment fragment=null;
        if(fragment==null){
            fragment= WebFragment.newInstance(getIntent().getStringExtra("url"));
        }
        openFragment(fragment);
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitAllowingStateLoss();
    }
}
