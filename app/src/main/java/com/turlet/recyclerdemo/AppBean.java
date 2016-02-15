package com.turlet.recyclerdemo;

import java.util.Comparator;

/**
 * 作者：杨兴朗 on 2016/1/4 14:25
 * 邮箱：xinglang.yxl@alibaba-inc.com
 */
public class AppBean implements Comparator<AppBean> {
    private String name;
    private String sdfs;


    @Override
    public int compare(AppBean lhs, AppBean rhs) {
//        Collections.sor
        return 0;
    }
}
