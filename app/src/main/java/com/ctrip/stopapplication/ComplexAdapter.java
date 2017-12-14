package com.ctrip.stopapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public class ComplexAdapter extends RecyclerView.Adapter {

    private List<String> mContentDataList;

    private static final int TYPE_ITEM_HEADER = 0;
    private static final int TYPE_ITEM_TAB = 1;
    private static final int TYPE_ITEM_CONTENT = 2;

    public ComplexAdapter(List<String> contentList) {
        mContentDataList = contentList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType) {
            case TYPE_ITEM_HEADER:
                return new HeaderViewHolder(inflater.inflate(R.layout.item_header, parent, false));
            case TYPE_ITEM_TAB:
                return new TabViewHolder(inflater.inflate(R.layout.item_tab, parent, false));
            case TYPE_ITEM_CONTENT:
                return new ItemViewHolder(inflater.inflate(R.layout.item_content, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).bind(mContentDataList.get(position - 2));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ITEM_HEADER;
        } else if (position == 1) {
            return TYPE_ITEM_TAB;
        } else {
            return TYPE_ITEM_CONTENT;
        }
    }

    @Override
    public int getItemCount() {
        return mContentDataList.size() + 2;
    }
}
