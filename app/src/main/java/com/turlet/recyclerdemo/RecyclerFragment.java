package com.turlet.recyclerdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：杨兴朗 on 2015/12/29 17:08
 * 邮箱：xinglang.yxl@alibaba-inc.com
 */
public class RecyclerFragment extends Fragment {


    private View view;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        if (container == null) {//优化View减少View的创建次数
            //该部分可通过xml文件设计Fragment界面，再通过LayoutInflater转换为View组件
            view = inflater.inflate(R.layout.fragment_recycler,null,false);
            recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        }
//        ViewGroup parent = (ViewGroup) container.getParent();
//        if (parent != null) {
//            //如果View已经添加到容器中，要进行删除，负责会报错
//            parent.removeView(view);
//        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecyclerAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }
}
