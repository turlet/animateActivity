package com.turlet.recyclerdemo;

import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * 作者：杨兴朗 on 2015/10/27 14:52
 * 邮箱：xinglang.yxl@alibaba-inc.com
 */
public class MarginProxyAnimator {

    private ViewGroup mView;

    public MarginProxyAnimator(ViewGroup view) {
        this.mView = view;
    }

    public int getHeight() {
        FrameLayout.LayoutParams  lp = (FrameLayout.LayoutParams) mView.getLayoutParams();
        return lp.height;
    }

    public void setHeight(int height) {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mView.getLayoutParams();
        lp.height = height;
        mView.setLayoutParams(lp);

    }


}
