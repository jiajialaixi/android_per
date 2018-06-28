package com.example.wj.android_per.common.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.wj.android_per.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageBanner extends LinearLayout {
    Context context;
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    BannerOnClickListener onClickListener;
    public ImageBanner(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ImageBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        View inflate = View.inflate(context, R.layout.banner, this);
        ButterKnife.bind(inflate);
    }
    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == VISIBLE) {
            convenientBanner.startTurning();
        } else {
            convenientBanner.stopTurning();
        }
    }
    /**
     * 给banner中的viewpager设置数据
     *
     * @param list
     */
    public void setList(List<String> list) {
        // 取到布局中的控件
        convenientBanner.setPages(
                new CBViewHolderCreator() {
                    @Override
                    public LocalImageHolderView createHolder(View itemView) {
                        return new LocalImageHolderView(itemView);
                    }

                    @Override
                    public int getLayoutId() {
                        return R.layout.item_localimage;
                    }
                }, list)
                 .setPageIndicator(new int[]{R.drawable.page_indicator_unfocused,R.drawable.page_indicator_focused})
                .setOnItemClickListener(position -> {
                    onClickListener.onClickListener(position);
                });
    }
    /**
     * banner item的点击监听
     *
     * @param onClickListener
     */
    public void setOnBannerClickListener(BannerOnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    public interface BannerOnClickListener{
        void onClickListener(int pos);
    }


    public class LocalImageHolderView extends Holder<String> {
        private ImageView imageView;

        public LocalImageHolderView(View itemView) {
            super(itemView);
        }

        @Override
        protected void initView(View itemView) {
            imageView =itemView.findViewById(R.id.imageView);
        }

        @Override
        public void updateUI(String data) {
            ImageUtile.setImageUrl(imageView, data);
        }
    }
}
