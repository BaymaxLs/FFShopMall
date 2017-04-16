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

import com.ffshopmall.R;
import com.ffshopmall.adapter.CommonAdapter;
import com.ffshopmall.adapter.ViewHolder;
import com.ffshopmall.model.bean;
import com.ffshopmall.model.recommendshopbean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Baymax on 2017/3/9.
 */

public class FFMainTabFragment extends Fragment {
    private int pos;
    /**
     * 购物中心ListView
     */
    private ListView m_listview;
    private CommonAdapter<bean> m_Adapter;
    /**
     * 推荐商铺ListView
     */
    private ListView rs_listview;
    private CommonAdapter<recommendshopbean> rs_Adapter;

    private List<bean> data;
    private List<bean> sm_data;
    private List<recommendshopbean> rs_data;

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

    public List<recommendshopbean> getRs_data(){
        rs_data = new ArrayList<recommendshopbean>();
        rs_data.add(new recommendshopbean(101,R.drawable.rs_starbuck,"星巴克（汇一城店）","美式咖啡","抹茶星冰乐","摩卡星冰乐",1));
        return rs_data;
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
        Bundle bundle = getArguments();
        System.out.println("!!!"+bundle.getString("city"));
        getData();
        getRs_data();
        View view = null;
        switch (pos) {
            case 0:
                view = inflater.inflate(R.layout.tab_homepage, container, false);
                rs_listview = (ListView) view.findViewById(R.id.id_tab_homepage_lv_recommendshop);
                rs_listview.setAdapter(rs_Adapter = new CommonAdapter<recommendshopbean>(R.layout.tab_list_hp_recommendshop,getContext(),rs_data) {
                    @Override
                    public void convert(ViewHolder holder, recommendshopbean item) {
                        holder.setText(R.id.id_tab_hp_rs_tv_shopname, item.getShopName());
                        holder.setImageResource(R.id.id_tab_hp_rs_iv_logo, item.getShopLogo());
                        holder.setText(R.id.id_tab_hp_rg_tv_goodsname1,item.getShopGoods1());
                        holder.setText(R.id.id_tab_hp_rg_tv_goodsname2,item.getShopGoods2());
                        holder.setText(R.id.id_tab_hp_rg_tv_goodsname3,item.getShopGoods3());
                    }
                });
                rs_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent m_intent = new Intent();
                        Bundle m_bundle = new Bundle();
                        m_bundle.putInt("shopId",rs_data.get(position).getShopId());
                        m_intent.putExtras(m_bundle);
                        m_intent.setClass(getActivity(),FFShopActivity.class);
                        startActivity(m_intent);
                    }
                });
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

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle2 = getArguments();
        System.out.println("!!!22"+bundle2.getString("city"));
    }

}
