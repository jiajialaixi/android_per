package com.example.wj.android_per.common.pullrefresh;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public abstract class StatusView  extends FrameLayout {

    public StatusView(@NonNull Context context) {
        super(context);
        this.init(context);
    }

    public StatusView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
    }

    public StatusView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context);
    }

    @RequiresApi(
            api = 21
    )
    public StatusView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.init(context);
    }

    protected void init(Context context) {
        inflate(context, this.getContentResource(), this);
    }

    protected abstract int getContentResource();

    public void finish() {
        this.setVisibility(View.GONE);
    }

    public void showLoading() {
        this.setAlpha(1.0F);
        this.setVisibility(View.VISIBLE);
    }

    public void showError(Throwable throwable) {
        this.setAlpha(1.0F);
        this.setVisibility(View.VISIBLE);
    }
    public void showEmpty(int resource, String text) {
        this.setAlpha(1.0F);
        this.setVisibility(View.VISIBLE);
    }

    public void finishWithAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "alpha", new float[]{1.0F, 0.0F});
        animator.addListener(new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animation) {
            }

            public void onAnimationEnd(Animator animation) {
                StatusView.this.setVisibility(View.GONE);
            }

            public void onAnimationCancel(Animator animation) {
            }

            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.setDuration(300L);
        animator.start();
    }

    public void showLoadingWithAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "alpha", new float[]{0.0F, 1.0F});
        animator.addListener(new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animation) {
            }

            public void onAnimationEnd(Animator animation) {
                StatusView.this.setVisibility(View.VISIBLE);
            }

            public void onAnimationCancel(Animator animation) {
            }

            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.setDuration(300L);
        animator.start();
    }
}
