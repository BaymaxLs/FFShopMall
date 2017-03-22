package com.ffshopmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Baymax on 2017/3/11.
 * 通用Adapter
 */

public abstract class CommonAdapter<T> extends BaseAdapter {

    protected LayoutInflater m_Inflater;
    protected Context m_Context;
    protected List<T> m_Data;
    protected final int m_LayoutId;

    public CommonAdapter(int m_LayoutId, Context m_Context, List<T> m_Data) {
        this.m_LayoutId = m_LayoutId;
        this.m_Inflater = LayoutInflater.from(m_Context);
        this.m_Context = m_Context;
        this.m_Data = m_Data;
    }

    @Override
    public int getCount() {
        return m_Data.size();
    }

    @Override
    public T getItem(int position) {
        return m_Data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = getViewHolder(position, convertView, parent);
        convert(holder, getItem(position));
        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T item);

    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.getHolder(m_Context,convertView,m_LayoutId,parent,position);
    }
}
