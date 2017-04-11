package com.ffshopmall.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ffshopmall.FMmap.map.FMMapMain;
import com.ffshopmall.R;
import com.ffshopmall.adapter.CommonAdapter;
import com.ffshopmall.adapter.ViewHolder;
import com.ffshopmall.model.shopbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Baymax on 2017/3/14.
 */

public class FFShopsActivity extends Activity {

    private ListView shop_ListView;
    private CommonAdapter<shopbean> shop_Adapter;
    private ImageView icon_back;
    private TextView shoppingmall_name;
    private Spinner type_Spinner;
    private Spinner floor_Spinner;
    private TextView tv_map;
    private List<String> type_List;
    private List<String> floor_List;
    /**
     * data是总数据，完整的原始数据
     * sm_data是该商场的所有店铺数据，从上个activity传值过来后，所有处理过的数据都应该以sm_data为总数据
     * shopdata是存放处理后的数据，最终view也是以该数据绘图的
     * */
    private List<shopbean> data;
    private List<shopbean> sm_data;
    private List<shopbean> shopdata;

    private List<String> getType_List(){
        type_List = new ArrayList<String>();
        type_List.add("全部");
        type_List.add("服饰");
        type_List.add("餐饮");
        type_List.add("购物");
        return type_List;
    }

    private List<String> getFloor_List(){
        floor_List = new ArrayList<String>();
        floor_List.add("全部");
        floor_List.add("B2层");
        floor_List.add("B1层");
        floor_List.add("F1层");
        return floor_List;
    }

    public List<shopbean> getData(){
        data = new ArrayList<shopbean>();
        data.add(new shopbean(101,1,"星巴克（汇一城店）",R.drawable.starbuck,"餐饮","F1层","9.8公里"));
        data.add(new shopbean(102,1,"GAP（汇一城店）",R.drawable.starbuck,"服饰","F1层","9.8公里"));
        data.add(new shopbean(103,2,"GAT（汇一城店）",R.drawable.starbuck,"服饰","F1层","9.8公里"));
        return data;
    }

    public List<shopbean> getShopData(int shoppingmall_Id){
        sm_data = new ArrayList<shopbean>();
        shopdata = new ArrayList<shopbean>();
        for(shopbean newdata:data){
            if(newdata.getShopmall_id()==shoppingmall_Id){
                sm_data.add(newdata);
            }
        }
        shopdata.addAll(sm_data);
        return sm_data;
    }

    public List<shopbean> getShopTypeData(String type){
        shopdata = new ArrayList<shopbean>();
        if (type.equals("全部")) {
            shopdata.addAll(sm_data);
        }else {
            for(shopbean newdata:sm_data){
                if(newdata.getType().equals(type)){
                    shopdata.add(newdata);
                }
            }
        }
        return shopdata;
    }

    public List<shopbean> getShopFloorData(String floor){
        shopdata = new ArrayList<shopbean>();
        if (floor.equals("全部")) {
            shopdata.addAll(sm_data);
        }else {
            for(shopbean newdata:sm_data){
                if(newdata.getFloor().equals(floor)){
                    shopdata.add(newdata);
                }
            }
        }
        return shopdata;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sm);

        initView();

        initAdapter();

        initEvent();

    }

    private void initEvent() {

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FFShopsActivity.this.finish();
            }
        });

        /*
        * 商铺ListView点击监听，点击进入商铺详情
        * */
        shop_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("shopId",sm_data.get(position).getId());
                intent.putExtras(bundle);
                intent.setClass(FFShopsActivity.this,FFShopActivity.class);
                startActivity(intent);

            }
        });

        /*
        * 分类下拉监听
        * */
        type_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<String> s_adapter = (ArrayAdapter<String>) parent.getAdapter();
                getShopTypeData(s_adapter.getItem(position));
                setAdapter_shopListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                getShopTypeData("全部");
                setAdapter_shopListView();
            }
        });
        /*
        * 楼层下拉监听
        * */
        floor_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<String> f_adapter = (ArrayAdapter<String>) parent.getAdapter();
                getShopFloorData(f_adapter.getItem(position));
                setAdapter_shopListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                getShopFloorData("全部");
                setAdapter_shopListView();
            }
        });

        tv_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("!!!!!!!!!!!!!!!!!");
                startActivity(new Intent(FFShopsActivity.this, FMMapMain.class));

            }
        });

    }

    private void initView() {

        icon_back = (ImageView) findViewById(R.id.id_sm_top_icon_back);

        shop_ListView = (ListView) findViewById(R.id.id_activity_sm_lv_shop);

        shoppingmall_name = (TextView) findViewById(R.id.id_sm_top_tv_mallname);

        type_Spinner = (Spinner) findViewById(R.id.id_sm_top_type_shoptype);

        tv_map = (TextView) findViewById(R.id.id_sm_top_tv_map);

        floor_Spinner = (Spinner) findViewById(R.id.id_sm_top_type_floor);

        getData();
        getType_List();
        getFloor_List();

        Bundle sm_bundle = this.getIntent().getExtras();
        shoppingmall_name.setText(sm_bundle.getString("shoppingmall_name"));
        getShopData(sm_bundle.getInt("shoppingmall_Id"));
    }

    private void initAdapter(){

        ArrayAdapter<String> type_Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,type_List);
        type_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> floor_Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,floor_List);
        floor_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        type_Spinner.setAdapter(type_Adapter);

        floor_Spinner.setAdapter(floor_Adapter);

        setAdapter_shopListView();
    }

    private void setAdapter_shopListView() {
        shop_ListView.setAdapter(shop_Adapter = new CommonAdapter<shopbean>(R.layout.sm_list_shop,getApplicationContext(),shopdata) {
            @Override
            public void convert(ViewHolder holder, shopbean item) {
                holder.setImageResource(R.id.id_activity_sm_img_thumbnail,item.getImage());
                holder.setText(R.id.id_activity_sm_tv_name,item.getName());
                holder.setText(R.id.id_activity_sm_tv_typefloor,item.getType()+" "+item.getFloor());
                holder.setText(R.id.id_activity_sm_tv_distance,item.getDistance());
            }
        });
    }
}
