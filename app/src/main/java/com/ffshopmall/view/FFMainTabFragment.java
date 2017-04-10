package com.ffshopmall.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ffshopmall.R;
import com.ffshopmall.adapter.CommonAdapter;
import com.ffshopmall.adapter.ViewHolder;
import com.ffshopmall.model.bean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Baymax on 2017/3/9.
 */

public class FFMainTabFragment extends Fragment {
    private int pos;
    private ListView m_listview;
    private CommonAdapter<bean> m_Adapter;
    private List<bean> data;
    private List<bean> sm_data;

    public List<bean> getData() {
        data = new ArrayList<bean>();
        data.add(new bean(1,"东莞市", "One Mall汇一城", R.drawable.i_20121019_1342223064, "广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处", "13.8公里"));
        data.add(new bean(2,"东莞市", "东城万达广场", R.drawable.i_20121019_1342223064, "广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处", "13.8公里"));
        data.add(new bean(3,"东莞市", "世博广场", R.drawable.i_20121019_1342223064, "广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处", "13.8公里"));
        data.add(new bean(4,"东莞市", "莱蒙商业中心", R.drawable.i_20121019_1342223064, "广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处", "13.8公里"));
        data.add(new bean(5,"深圳市", "万象城", R.drawable.i_20121019_1342223064, "广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处", "13.8公里"));
        data.add(new bean(6,"深圳市", "中心城", R.drawable.i_20121019_1342223064, "广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处", "13.8公里"));
        data.add(new bean(7,"深圳市", "海岸城", R.drawable.i_20121019_1342223064, "广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处", "13.8公里"));
        data.add(new bean(8,"深圳市", "皇庭广场", R.drawable.i_20121019_1342223064, "广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处", "13.8公里"));
        return data;
    }

    public List<bean> getSm_data(String city) {

        sm_data = new ArrayList<bean>();
        for(bean newdata : data){

            if (newdata.getCity().equals(city)) {
                sm_data.add(newdata);
            }
        }
        return sm_data;
    }

    public FFMainTabFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.pos = getArguments().getInt("pos");
        getData();
        View view = null;
        switch (pos) {
            case 0:
                view = inflater.inflate(R.layout.tab, container, false);
                TextView tv = (TextView) view.findViewById(R.id.tv_test);
                tv.setText(FFMainTabAdapter.CONTENT[pos] + Integer.toString(pos));
                break;
            case 1:
                view = inflater.inflate(R.layout.tab_shoppingmall, container, false);
                m_listview = (ListView) view.findViewById(R.id.id_tab_sm_lv_mall);
                m_listview.setAdapter(m_Adapter = new CommonAdapter<bean>(R.layout.tab_list_shoppingmall, getContext(), data) {
                    @Override
                    public void convert(ViewHolder holder, bean item) {
                        holder.setImageResource(R.id.id_tab_sm_img_thumbnail, item.getImage());
                        holder.setText(R.id.id_tab_sm_tv_name, item.getTitle());
                        holder.setText(R.id.id_tab_sm_tv_address, item.getAddress());
                        holder.setText(R.id.id_tab_sm_tv_distance, item.getDistance());
                    }
                });
                m_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent m_intent = new Intent();
                        Bundle m_bundle = new Bundle();
                        m_bundle.putInt("shoppingmall_Id",data.get(position).getId());
                        m_bundle.putString("shoppingmall_name",data.get(position).getTitle());
                        m_intent.putExtras(m_bundle);
                        m_intent.setClass(getActivity(),FFShopsActivity.class);
                        startActivity(m_intent);
                    }
                });
                break;
        }
        return view;
    }
}
