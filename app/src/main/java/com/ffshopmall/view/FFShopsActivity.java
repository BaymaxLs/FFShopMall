package com.ffshopmall.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ffshopmall.FMmap.map.FMMapMain;
import com.ffshopmall.IndoorMap.view.FFIndoorMapMain;
import com.ffshopmall.R;
import com.ffshopmall.adapter.CommonAdapter;
import com.ffshopmall.adapter.ViewHolder;
import com.ffshopmall.model.Shopbean;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Baymax on 2017/3/14.
 */

public class FFShopsActivity extends Activity {

    private ListView shop_ListView;
    private CommonAdapter<Shopbean> shop_Adapter;
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
    private List<Shopbean> data;
//    private List<Shopbean> sm_data;
    private List<Shopbean> shopdata;
    private String distance = null;

    private String shopUrl = FileUtils.URLIP+"FFShopMall/shopjson!findShopJson.action?shopmallId=";

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
        floor_List.add("2B层");
        floor_List.add("1B层");
        floor_List.add("1F层");
        floor_List.add("2F层");
        floor_List.add("3F层");
        floor_List.add("4F层");
        return floor_List;
    }

//    public List<shopbean> getData(){
//        data = new ArrayList<shopbean>();
//        data.add(new shopbean(101,1,"星巴克（东城万达店）",R.drawable.starbuck,"餐饮","F1层","9.8公里"));
//        data.add(new shopbean(102,1,"金汤匙台湾新料理",R.drawable.jintangshaologo,"餐饮","F3层","9.8公里"));
////        data.add(new shopbean(102,1,"GAP（汇一城店）",R.drawable.starbuck,"服饰","F1层","9.8公里"));
//        data.add(new shopbean(103,2,"星巴克（北京爱琴海店）",R.drawable.starbuck,"餐饮","F1层","2167.6公里"));
//        return data;
//    }

//    public List<Shopbean> getShopData(String shoppingmall_Id){
//        sm_data = new ArrayList<Shopbean>();
//        shopdata = new ArrayList<Shopbean>();
//        for(Shopbean newdata:data){
//            if(newdata.getShopmallId().equals(shoppingmall_Id)){
//                sm_data.add(newdata);
//            }
//        }
//        shopdata.addAll(sm_data);
//        return sm_data;
//    }

    public List<Shopbean> getShopTypeData(String type){
        shopdata = new ArrayList<Shopbean>();
        if (type.equals("全部")) {
            shopdata.addAll(data);
        }else {
            for(Shopbean newdata:data){
                if(newdata.getShopType().equals(type)){
                    shopdata.add(newdata);
                }
            }
        }
        return shopdata;
    }

    public List<Shopbean> getShopFloorData(String floor){
        shopdata = new ArrayList<Shopbean>();
        if (floor.equals("全部")) {
            shopdata.addAll(data);
        }else {
            for(Shopbean newdata:data){
                if(newdata.getShopFloor().equals(floor)){
                    shopdata.add(newdata);
                }
            }
        }
        return shopdata;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sm);

        initView();

        initAdapter();

        initEvent();

    }

    private void initEvent() {

        /**
         * 返回按钮监听
         */
        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FFShopsActivity.this.finish();
            }
        });

        /**
        * 商铺ListView点击监听，点击进入商铺详情
        * */
        shop_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("shopId",data.get(position).getShopId());
                intent.putExtras(bundle);
                intent.setClass(FFShopsActivity.this,FFShopActivity.class);
                startActivity(intent);
            }
        });

        /**
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
        /**
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
                startActivity(new Intent(FFShopsActivity.this, FFIndoorMapMain.class));

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

//        getData();
        getType_List();
        getFloor_List();

        Bundle sm_bundle = this.getIntent().getExtras();
        String smId = sm_bundle.getString("shopmallId");
        shoppingmall_name.setText(sm_bundle.getString("shopmallName"));
        distance = sm_bundle.getString("distance");
        //getShopData(sm_bundle.getString("shopmallId"));
        getShopmallData(shopUrl+smId);

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
        shop_ListView.setAdapter(shop_Adapter = new CommonAdapter<Shopbean>(R.layout.sm_list_shop,getApplicationContext(),data) {
                    @Override
                    public void convert(ViewHolder holder, Shopbean item) {
                        holder.setImageBitmap(R.id.id_activity_sm_img_thumbnail, HttpImage.getHttpBitmap(item.getShopLogo()));
                        holder.setText(R.id.id_activity_sm_tv_name,item.getShopName());
                        holder.setText(R.id.id_activity_sm_tv_typefloor,item.getShopType()+" "+item.getShopFloor());
                        holder.setText(R.id.id_activity_sm_tv_distance,distance);
                    }
                }


        );

//        shop_ListView.setAdapter(shop_Adapter = new CommonAdapter<shopbean>(R.layout.sm_list_shop,getApplicationContext(),shopdata) {
//            @Override
//            public void convert(ViewHolder holder, shopbean item) {
//                holder.setImageResource(R.id.id_activity_sm_img_thumbnail,item.getImage());
//                holder.setText(R.id.id_activity_sm_tv_name,item.getName());
//                holder.setText(R.id.id_activity_sm_tv_typefloor,item.getType()+" "+item.getFloor());
//                holder.setText(R.id.id_activity_sm_tv_distance,item.getDistance());
//            }
//        });
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

                    data = new ArrayList<Shopbean>();
                    for(int i = 0; i<jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        Shopbean bean = new Shopbean();
                        bean.setShopmallId(jsonObject.getString("shopmallId"));
                        bean.setShopId(jsonObject.getString("shopId"));
                        bean.setPoiId(jsonObject.getString("poiId"));
                        bean.setShopName(jsonObject.getString("shopName"));
                        bean.setShopFloor(jsonObject.getString("shopFloor"));
                        bean.setShopType(jsonObject.getString("shopType"));
                        bean.setShopPhone(jsonObject.getString("shopPhone"));
                        bean.setShopInfo(jsonObject.getString("shopInfo"));
                        bean.setShopImage(FileUtils.URLIP+jsonObject.getString("shopImage"));
                        bean.setShopLogo(FileUtils.URLIP+jsonObject.getString("shopLogo"));

                        data.add(bean);
                    }

                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
