package com.turlet.recyclerdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 作者：杨兴朗 on 2015/12/28 18:42
 * 邮箱：xinglang.yxl@alibaba-inc.com
 */
public class TabActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager vp;
    ArrayList tvs;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_tab);
        tvs = new ArrayList<TextView>();
        for (int i = 0; i < 12; i++) {
            TextView tv = new TextView(this);
            tv.setText("Text");
            LinearLayout.LayoutParams lp =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            tv.setTextColor(Color.BLACK);
            tv.setBackgroundColor(Color.WHITE);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(lp);
            tv.setTextSize(22);
            tvs.add(tv);
        }
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setTabTextColors(Color.WHITE, Color.GRAY);//设置文本在选中和为选中时候的颜色
        vp = (ViewPager) findViewById(R.id.vp);
        PageAdapters adapter = new PageAdapters();
        vp.setAdapter(adapter);

        //用来设置tab的，同时也要覆写  PagerAdapter 的 CharSequence getPageTitle(int position) 方法，要不然 Tab 没有 title
        tabLayout.setupWithViewPager(vp);
        //关联 TabLayout viewpager
        tabLayout.setTabsFromPagerAdapter(adapter);


    }

    class PageAdapters extends PagerAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }
    }
}
