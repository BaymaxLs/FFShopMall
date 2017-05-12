package com.ffshopmall.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
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
import com.ffshopmall.model.ShopmallBean;
import com.ffshopmall.model.bean;
import com.ffshopmall.model.recommendshopbean;
import com.ffshopmall.model.saleinfobean;
import com.ffshopmall.utils.FileUtils;
import com.ffshopmall.utils.HttpImage;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.URL;
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
    private CommonAdapter<ShopmallBean> m_Adapter;
    /**
     * 推荐商铺ListView
     */
    private ListView rs_listview;
    private CommonAdapter<recommendshopbean> rs_Adapter;

    private ListView p_listview;
    private CommonAdapter<saleinfobean> p_Adapter;

    private List<bean> data;
    private List<bean> sm_data;

    private List<ShopmallBean> shopmall_data;

    private List<saleinfobean> sale_data;
    private List<recommendshopbean> rs_data;

    private String shopmallUrl = FileUtils.URLIP+"FFShopMall/shopmalljson!findShopmallJson.action";

    public List<bean> getData() {
        data = new ArrayList<bean>();
        data.add(new bean(1,"东莞市", "东城万达广场", R.drawable.w20170320153654_6205297, "广东省东莞市东纵路208号", "8.8公里"));
//        data.add(new bean(2,"北京市", "爱琴海购物中心", R.drawable.aiqinhai, "北京市朝阳区七圣中街12号", "2167.6公里"));
//        data.add(new bean(3,"东莞市", "世博广场", R.drawable.i_20121019_1342223064, "广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处", "13.8公里"));
//        data.add(new bean(4,"东莞市", "莱蒙商业中心", R.drawable.i_20121019_1342223064, "广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处", "13.8公里"));
//        data.add(new bean(5,"深圳市", "万象城", R.drawable.i_20121019_1342223064, "广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处", "13.8公里"));
//        data.add(new bean(6,"深圳市", "中心城", R.drawable.i_20121019_1342223064, "广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处", "13.8公里"));
//        data.add(new bean(7,"深圳市", "海岸城", R.drawable.i_20121019_1342223064, "广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处", "13.8公里"));
//        data.add(new bean(8,"深圳市", "皇庭广场", R.drawable.i_20121019_1342223064, "广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处", "13.8公里"));
        return data;
    }

    public List<recommendshopbean> getRs_data(){
        rs_data = new ArrayList<recommendshopbean>();
        rs_data.add(new recommendshopbean("8ef89e935be8f9c6015be944c7ef0002",R.drawable.rs_starbuck,"星巴克（东城万达店）","美式咖啡","抹茶星冰乐","摩卡星冰乐",1));
        rs_data.add(new recommendshopbean("8ef89e935be8f9c6015be944c7ef0002",R.drawable.jintangshaologo,"金汤匙台湾新料理","三杯鸡","卤肉饭","炸豆腐",2));
        return rs_data;
    }

    public List<saleinfobean> getSale_data(){
        sale_data = new ArrayList<saleinfobean>();
        sale_data.add(new saleinfobean(10101,"8ef89e935be8f9c6015be944c7ef0002","当季新品 冷萃冰咖啡",R.drawable.starbuck_saleimage,R.drawable.starbuck_saleimage11,"2017.04.01 - 2017.06.01","去年夏天让你爱不释手的冷萃冰咖啡，现在终于回归了。而今年，星巴克西雅图咖啡团队又进一步探索关于它的更多可能，新研发了一款“轻甜香草风味奶油冷萃冰咖啡”，快去你身边的星巴克门店，感受专属咖啡豆的甘醇与本味。（同期还有全新设计的冷萃咖啡星享卡）"));
        sale_data.add(new saleinfobean(10102,"8ef89e935be8f9c6015be944c7ef0002","当季新品 星冰粽",R.drawable.starbuck_saleimage2,R.drawable.starbuck_saleimage22,"2017.05.01 - 2017.07.01","悠闲的夏午后，怎能少得清甜品的陪伴。全新星巴克星冰粽，将你喜欢的饮品冷萃和桃桃变成甜品，多重味的冰沁甜点，打造你的轻甜夏时光。"));
        return sale_data;
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
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                        .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                        .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());
        this.pos = getArguments().getInt("pos");
        Bundle bundle = getArguments();
        System.out.println("!!!"+bundle.getString("city"));
        getShopmallData(shopmallUrl);
        getData();
        getRs_data();
        getSale_data();
        View view = null;
        switch (pos) {
            case 0:
                view = inflater.inflate(R.layout.tab_homepage, container, false);
                rs_listview = (ListView) view.findViewById(R.id.id_tab_homepage_lv_recommendshop);
                p_listview = (ListView) view.findViewById(R.id.id_tab_homepage_lv_preferential_info);
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
                        m_bundle.putString("shopId",rs_data.get(position).getShopId());
                        m_intent.putExtras(m_bundle);
                        m_intent.setClass(getActivity(),FFShopActivity.class);
                        startActivity(m_intent);
                    }
                });

                p_listview.setAdapter(p_Adapter = new CommonAdapter<saleinfobean>(R.layout.tab_list_hp_preferential_info,getContext(),sale_data) {
                    @Override
                    public void convert(ViewHolder holder, saleinfobean item) {
                        holder.setText(R.id.id_tab_hp_p_list_tv_shopname,"星巴克（东城万达店）");
                        holder.setText(R.id.id_tab_hp_p_list_tv_salename, item.getSaleName());
                        holder.setText(R.id.id_tab_hp_p_list_tv_saletime,item.getSaleTime());
                        holder.setImageResource(R.id.id_tab_hp_p_list_iv_image,item.getSaleImage());
                    }
                });

                p_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent m_intent = new Intent();
                        Bundle m_bundle = new Bundle();
                        m_bundle.putString("shopId",sale_data.get(position).getShopId());
                        m_intent.putExtras(m_bundle);
                        m_intent.setClass(getActivity(),FFShopActivity.class);
                        startActivity(m_intent);
                    }
                });

                break;
            case 1:
                view = inflater.inflate(R.layout.tab_shoppingmall, container, false);
                m_listview = (ListView) view.findViewById(R.id.id_tab_sm_lv_mall);
                m_listview.setAdapter(m_Adapter = new CommonAdapter<ShopmallBean>(R.layout.tab_list_shoppingmall, getContext(), shopmall_data) {
                            @Override
                            public void convert(ViewHolder holder, ShopmallBean item) {
                                holder.setImageBitmap(R.id.id_tab_sm_img_thumbnail, HttpImage.getHttpBitmap(item.getShopmallImage()));
                                holder.setText(R.id.id_tab_sm_tv_name, item.getShopmallName());
                                holder.setText(R.id.id_tab_sm_tv_address, item.getShopmallAddress());
                                holder.setText(R.id.id_tab_sm_tv_distance, "8.9公里");
                            }
                        });
//                m_listview.setAdapter(m_Adapter = new CommonAdapter<ShopmallBean>(R.layout.tab_list_shoppingmall, getContext(), shopmall_data) {
//                    @Override
//                    public void convert(ViewHolder holder, bean item) {
//                        holder.setImageResource(R.id.id_tab_sm_img_thumbnail, item.getImage());
//                        holder.setText(R.id.id_tab_sm_tv_name, item.getTitle());
//                        holder.setText(R.id.id_tab_sm_tv_address, item.getAddress());
//                        holder.setText(R.id.id_tab_sm_tv_distance, item.getDistance());
//                    }
//                });
                m_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent m_intent = new Intent();
                        Bundle m_bundle = new Bundle();
                        m_bundle.putString("shopmallId",shopmall_data.get(position).getShopmallId());
                        m_bundle.putString("shopmallName",shopmall_data.get(position).getShopmallName());
                        m_bundle.putString("distance","8.9公里");
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

    private void getShopmallData(String url){

        HttpClient client = new DefaultHttpClient();
        HttpPost request;
        try{
            request = new HttpPost(new URI(url));
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) { //200表示请求成功
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String out = EntityUtils.toString(entity, "UTF-8");
                    JSONArray jsonArray = new JSONArray(out);

                    shopmall_data = new ArrayList<ShopmallBean>();
                    for(int i = 0; i<jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        ShopmallBean bean = new ShopmallBean();
                        bean.setShopmallId(jsonObject.getString("shopmallId"));
                        bean.setBuildingId(jsonObject.getString("buildingId"));
                        bean.setShopmallName(jsonObject.getString("shopmallName"));
                        bean.setShopmallCity(jsonObject.getString("shopmallCity"));
                        bean.setShopmallImage(FileUtils.URLIP+jsonObject.getString("shopmallImage"));
                        bean.setShopmallAddress(jsonObject.getString("shopmallAddress"));

                        shopmall_data.add(bean);
                    }

                    }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }


}
