package com.turlet.recyclerdemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    private View view;
    private RecyclerView mRecyclerView;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();


    private List<String> titleList = new ArrayList<>();

    private FagmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabLayout.addTab(tabLayout.newTab().setText("Recycler"));
        RecyclerFragment recyclerFragment = new RecyclerFragment();
        fragmentList.add(recyclerFragment);
        titleList.add("测试\n换行");
        titleList.add("测试二");
        titleList.add("测试三");
        titleList.add("测试四");
        titleList.add("测试五");
        titleList.add("测试六");
        titleList.add("测试七");
        titleList.add("测试八");
        titleList.add("测试九");
        titleList.add("测试十");
        titleList.add("测试一");
        titleList.add("测试一");

        for (int i = 0; i < titleList.size(); i++) {
            NewsFragment frag = new NewsFragment();

            Bundle bundle = new Bundle();
            bundle.putString("weburl", "test");
            bundle.putString("name", titleList.get(i));
            frag.setArguments(bundle);
            //向Fragment传入数据
            fragmentList.add(frag);
            tabLayout.addTab(tabLayout.newTab().setText(titleList.get(i)));
        }
        ImageView img = new ImageView(this);
        img.setImageResource(R.mipmap.ic_launcher);
        tabLayout.getTabAt(0).setCustomView(img);
        adapter = new FagmentAdapter(getSupportFragmentManager(),fragmentList,titleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
