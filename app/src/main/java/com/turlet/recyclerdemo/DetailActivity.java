package com.turlet.recyclerdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 作者：杨兴朗 on 2016/1/6 10:07
 * 邮箱：xinglang.yxl@alibaba-inc.com
 */
public class DetailActivity extends SwipeBackActivity implements View.OnClickListener {

    private static final int MARGIN_TOP = 60;

    private int heightLayout = 70;
    private int imgLength = 56;

    private SwipeBackLayout mSwipeBackLayout;

    private ImageView mImageView;

    private PageArgs mPageArgs;
    private RelativeLayout mLayout;

    private TextView mTextView;

    private ImageView close;

    private Handler mHandler = new Handler();

    public static void open(Context context, PageArgs args) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("args", args);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_ALL);
        mPageArgs = getIntent().getParcelableExtra("args");
        System.err.println("mPageArgs=" + mPageArgs.toString());

        mLayout = (RelativeLayout) findViewById(R.id.layout);
        mImageView = (ImageView) findViewById(R.id.img);
        mTextView = (TextView) findViewById(R.id.detail_bg);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setLayout(mLayout, mPageArgs.getX(), mPageArgs.getY());
                mLayout.setVisibility(View.VISIBLE);
                setLayout(mImageView, mPageArgs.getX() + dip2px(DetailActivity.this, 6), mPageArgs.getY() + dip2px(DetailActivity.this, 6));
                mImageView.setVisibility(View.VISIBLE);

                int translationY = mPageArgs.getY() - dip2px(DetailActivity.this, MARGIN_TOP) - getStatusBarHeight(DetailActivity.this);
                ObjectAnimator layoutAnim = ObjectAnimator.ofInt(new MarginProxyAnimator(mLayout), "height", dip2px(DetailActivity.this, heightLayout), getHeight(DetailActivity.this));
                ObjectAnimator layoutAnimY = ObjectAnimator.ofFloat(mLayout, View.TRANSLATION_Y, 0, -translationY);
                ObjectAnimator layoutBgAnim = ObjectAnimator.ofObject(mLayout, "backgroundColor", new ArgbEvaluator(), 0x78FFFFFF, 0xFFFFFFFF);
                layoutBgAnim.setInterpolator(new AccelerateInterpolator());
                layoutBgAnim.setDuration(400);
                layoutBgAnim.start();

                ObjectAnimator imgAnimX = ObjectAnimator.ofFloat(mImageView, View.TRANSLATION_X, 0, getWidth(DetailActivity.this) / 2 - dip2px(DetailActivity.this, imgLength) / 2);
                ObjectAnimator imgAnimY = ObjectAnimator.ofFloat(mImageView, View.TRANSLATION_Y, 0, -translationY + dip2px(DetailActivity.this, 20));

                ObjectAnimator bgAnim = ObjectAnimator.ofObject(mTextView, "backgroundColor", new ArgbEvaluator(), 0x56000000, 0xAA000000);


                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                animatorSet.setStartDelay(300);
                animatorSet.setDuration(800);
                animatorSet.play(layoutAnim);
                animatorSet.play(layoutAnimY);
                animatorSet.play(imgAnimX);
                animatorSet.play(imgAnimY);
                animatorSet.play(bgAnim);
                animatorSet.start();
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        close = (ImageView) findViewById(R.id.close);
                        close.setVisibility(View.VISIBLE);
                        close.setOnClickListener(DetailActivity.this);
                        findViewById(R.id.content).setVisibility(View.VISIBLE);
                        findViewById(R.id.push_close).setVisibility(View.VISIBLE);
                        findViewById(R.id.detail_bg1).setVisibility(View.VISIBLE);
                        findViewById(R.id.detail_bg1).setOnClickListener(DetailActivity.this);
                        mTextView.setOnClickListener(DetailActivity.this);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        }, 250);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /*
                * 设置控件所在的位置YY，并且不改变宽高，
                * XY为绝对位置
                */
    public static void setLayout(View view, int x, int y) {
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(x, y, x + margin.width, y + margin.height);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }

    public static int getWidth(Context context) {
        if (context instanceof Activity) {
            DisplayMetrics displaysMetrics = new DisplayMetrics();
            ((Activity) context).getWindowManager()
                    .getDefaultDisplay()
                    .getMetrics(displaysMetrics);

            //得到屏幕宽
            return displaysMetrics.widthPixels;
        }
        return 0;
    }

    public static int getHeight(Context context) {
        if (context instanceof Activity) {
            DisplayMetrics displaysMetrics = new DisplayMetrics();
            ((Activity) context).getWindowManager()
                    .getDefaultDisplay()
                    .getMetrics(displaysMetrics);
            //得到屏幕宽
            return displaysMetrics.heightPixels;
        }
        return 0;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int dip2px(Context context, float dpValue) {
        //dip->px
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                context.getResources().getDisplayMetrics());
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.detail_bg1)return;
        finish();
    }
}
