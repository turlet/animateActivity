package com.turlet.recyclerdemo;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 作者：杨兴朗 on 2015/12/28 16:18
 * 邮箱：xinglang.yxl@alibaba-inc.com
 */
public class RecyclerAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private int refresh = 10;

    private int count;

    private Activity mActivity;

    public RecyclerAdapter(Activity mActivity) {
        this.mActivity = mActivity;
        count = 40;
    }

    public void setData(){
        count +=refresh;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int[] location = new int[2];
                    itemView.getLocationOnScreen(location);
                    PageArgs args = new PageArgs();
                    args.setX(location[0]);
                    args.setY(location[1]-DetailActivity.getStatusBarHeight(mActivity));
                    DetailActivity.open(mActivity,args);
                }
            });
            return new ItemViewHolder(itemView);
        }else if(viewType == TYPE_FOOTER){
            TextView t = new TextView(parent.getContext());
            t.setText("footer");
            return new FooterViewHolder(t);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  ItemViewHolder) {
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.textView.setText("点击我=" + position);

        }
    }


    @Override
    public int getItemCount() {
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position+1  == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }

    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }

    }



}
