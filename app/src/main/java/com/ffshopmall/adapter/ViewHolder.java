package com.ffshopmall.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Baymax on 2017/3/11.
 * 通用ViewHolder
 */

public class ViewHolder {

    private final SparseArray<View> m_Views;
    private View m_ConvertView;
    private int m_Position;

    /*
    * init ViewHolder
    * */

    public ViewHolder(Context context, int layoutId, ViewGroup parent, int position){
        this.m_Views = new SparseArray<View>();
        m_ConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        m_Position = position;
        m_ConvertView.setTag(this);
    }

    /*
    * getViewHolder
    * */

    public static ViewHolder getHolder(Context context, View convertView, int layoutId, ViewGroup parent,int position){
        if (convertView == null) {
            return new ViewHolder(context,layoutId,parent,position);
        }else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.m_Position = position;
            return holder;
        }
    }

    public View getConvertView(){
        return m_ConvertView;
    }

    /*
    * get View
    * */
    public <T extends View> T getView(int viewId){
        View view = m_Views.get(viewId);
        if (view == null) {
            view = m_ConvertView.findViewById(viewId);
            m_Views.put(viewId, view);
        }
        return (T)view;
    }

    /*
    * set Text
    * */
    public ViewHolder setText(int viewId, String text){
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /*
    * set image res
    * */
    public  ViewHolder setImageResource(int viewId, int resId){
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    /*
    * set image bitmap
    * */
    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap){
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    public int getPosition(){
        return m_Position;
    }

}
