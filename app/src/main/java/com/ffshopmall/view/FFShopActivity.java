package com.ffshopmall.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ffshopmall.IndoorMap.view.FFIndoorGuideShop;
import com.ffshopmall.IndoorMap.view.FFIndoorMapMain;
import com.ffshopmall.R;
import com.ffshopmall.adapter.CommonAdapter;
import com.ffshopmall.adapter.ViewHolder;
import com.ffshopmall.model.Shopbean;
import com.ffshopmall.model.saleinfobean;
import com.ffshopmall.model.shopinfobean;
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
 * Created by Baymax on 2017/3/16.
 */

public class FFShopActivity extends Activity {

    private ImageView icon_back;
    private ImageView iv_shopImage;
    private ImageView iv_shopLogo;
    private TextView tv_shopName;
    private LinearLayout ll_shopAddress;
    private TextView tv_shopAddress;
    private TextView tv_floorAddress;
    private ImageView icon_phone;
    private LinearLayout ll_shopGuide;
    private TextView tv_shopGuide;
    private LinearLayout ll_shopActivity;
    private TextView tv_shopActivity;
    private TextView tv_shopInfo;
    private ListView lv_saleinfo;
    private CommonAdapter<saleinfobean> sale_Adapter;
    private List<saleinfobean> sale_data;
    //商铺的id
    private String poiId = "GD0009320210100003";

    private FragmentManager mFragmentManager = null;
    private FragmentTransaction mFragmentTransaction = null;

    private shopinfobean bean;
    private List<shopinfobean> shop_data;

    private Shopbean shopbean = new Shopbean();;
    private List<Shopbean> shopData;

    private String shopUrl = FileUtils.URLIP+"FFShopMall/shopjson!findShopIdJson.action?shopId=";

//    public List<shopinfobean> getList() {
//
//        shop_data = new ArrayList<shopinfobean>();
//        shop_data.add(new shopinfobean(101,R.drawable.mxcib,R.drawable.starbuck,"星巴克","广东省东莞市东莞市东纵路208号","1F-1001/1002","周五买一送一","星巴克(Starbucks)是美国一家连锁咖啡公司的名称，1971年成立，为全球最大的咖啡连锁店，其总部坐落美国华盛顿州西雅图市。星巴克在全球范围内已经有近12,000间分店遍布北美、南美洲、欧洲、中东及太平洋区。星巴克旗下零售产品包括30多款全球顶级的咖啡豆、手工制作的浓缩咖啡和多款咖啡冷热饮料、新鲜美味的各式糕点食品以及丰富多样的咖啡机、咖啡杯等商品。"));
////        shop_data.add(new shopinfobean(102,R.drawable.mxcib,R.drawable.starbuck,"GAP","广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处","L1-122","周五买一送一","星巴克(Starbucks)是美国一家连锁咖啡公司的名称，1971年成立，为全球最大的咖啡连锁店，其总部坐落美国华盛顿州西雅图市。星巴克在全球范围内已经有近12,000间分店遍布北美、南美洲、欧洲、中东及太平洋区。星巴克旗下零售产品包括30多款全球顶级的咖啡豆、手工制作的浓缩咖啡和多款咖啡冷热饮料、新鲜美味的各式糕点食品以及丰富多样的咖啡机、咖啡杯等商品。"));
//        shop_data.add(new shopinfobean(103,R.drawable.xingbaba,R.drawable.starbuck,"星巴克","北京市朝阳区七圣中街12号","1F1007","周五买一送一","星巴克(Starbucks)是美国一家连锁咖啡公司的名称，1971年成立，为全球最大的咖啡连锁店，其总部坐落美国华盛顿州西雅图市。星巴克在全球范围内已经有近12,000间分店遍布北美、南美洲、欧洲、中东及太平洋区。星巴克旗下零售产品包括30多款全球顶级的咖啡豆、手工制作的浓缩咖啡和多款咖啡冷热饮料、新鲜美味的各式糕点食品以及丰富多样的咖啡机、咖啡杯等商品。"));
//        return shop_data;
//    }

    public List<saleinfobean> getSale_data(){
        sale_data = new ArrayList<saleinfobean>();
        sale_data.add(new saleinfobean(10101,"8ef89e935be8f9c6015be944c7ef0002","当季新品 冷萃冰咖啡",R.drawable.starbuck_saleimage,R.drawable.starbuck_saleimage11,"2017.04.01 - 2017.06.01","去年夏天让你爱不释手的冷萃冰咖啡，现在终于回归了。而今年，星巴克西雅图咖啡团队又进一步探索关于它的更多可能，新研发了一款“轻甜香草风味奶油冷萃冰咖啡”，快去你身边的星巴克门店，感受专属咖啡豆的甘醇与本味。（同期还有全新设计的冷萃咖啡星享卡）"));
        sale_data.add(new saleinfobean(10102,"8ef89e935be8f9c6015be944c7ef0002","当季新品 星冰粽",R.drawable.starbuck_saleimage2,R.drawable.starbuck_saleimage22,"2017.05.01 - 2017.07.01","悠闲的夏午后，怎能少得清甜品的陪伴。全新星巴克星冰粽，将你喜欢的饮品冷萃和桃桃变成甜品，多重味的冰沁甜点，打造你的轻甜夏时光。"));
        return sale_data;
    }

//    private shopinfobean getBean(int shopId){
//        for(shopinfobean newbean : shop_data){
//            if (newbean.getShopId() == shopId) {
//                bean = new shopinfobean(newbean.getShopId(),newbean.getShopImage(),newbean.getShopLogo(),newbean.getShopName(),newbean.getShopAddress(),newbean.getFloorAddress(),newbean.getShopActivity(),newbean.getShopInfo());
//            }
//        }
//        return bean;
//    }

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
        setContentView(R.layout.activity_shop);

        initView();

        initAdapter();

        initEvent();
    }

    private void initView() {

        icon_back = (ImageView) findViewById(R.id.id_shop_top_icon_back);
        iv_shopImage = (ImageView) findViewById(R.id.id_activity_shop_iv_shopimage);
        iv_shopLogo = (ImageView) findViewById(R.id.id_activity_shop_iv_shoplogo);
        tv_shopName = (TextView) findViewById(R.id.id_activity_shop_tv_shopname);
        ll_shopAddress = (LinearLayout) findViewById(R.id.id_activity_shop_layout_address);
        tv_shopAddress = (TextView) findViewById(R.id.id_activity_shop_tv_address);
        tv_floorAddress = (TextView) findViewById(R.id.id_activity_shop_tv_flooraddress);
        icon_phone = (ImageView) findViewById(R.id.id_activity_shop_icon_phone);
        ll_shopGuide = (LinearLayout) findViewById(R.id.id_activity_shop_layout_guide);
        tv_shopGuide = (TextView) findViewById(R.id.id_activity_shop_tv_guide);
        ll_shopActivity = (LinearLayout) findViewById(R.id.id_activity_shop_layout_activity);
        tv_shopActivity = (TextView) findViewById(R.id.id_activity_shop_tv_sale);
        tv_shopInfo = (TextView) findViewById(R.id.id_activity_shop_tv_shopinfo);
        lv_saleinfo = (ListView) findViewById(R.id.id_activity_shop_lv_sale);

//        getList();

        getSale_data();

        Bundle bundle = this.getIntent().getExtras();
        String sId = bundle.getString("shopId");
//        getBean(sId);
        getShopmallData(shopUrl+sId);
        iv_shopImage.setImageBitmap(HttpImage.getHttpBitmap(shopbean.getShopImage()));
        iv_shopLogo.setImageBitmap(HttpImage.getHttpBitmap(shopbean.getShopLogo()));
        tv_shopName.setText(shopbean.getShopName());
        tv_shopAddress.setText(shopbean.getShopAddress());
        tv_floorAddress.setText(shopbean.getShopFloor());
        tv_shopGuide.setText("导航到"+shopbean.getShopName());
//        tv_shopActivity.setText(bean.getShopActivity());
        tv_shopInfo.setText(shopbean.getShopInfo());


    }

    private void initEvent() {
        /**
         * 返回按钮监听
         */
        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FFShopActivity.this.finish();
            }
        });

        /**
         * 活动列表监听
         */
        lv_saleinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("shopId",shopbean.getShopId());
                bundle.putInt("saleId",sale_data.get(position).getSaleId());
                intent.putExtras(bundle);
                intent.setClass(FFShopActivity.this,FFShopSaleActivity.class);
                startActivity(intent);
            }
        });

        ll_shopGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mFragmentManager = getFragmentManager();
//                mFragmentTransaction = mFragmentManager.beginTransaction();
//                FFIndoorGuideShop guideShopFragment = new FFIndoorGuideShop();
                Intent intent = new Intent(FFShopActivity.this, FFIndoorGuideShop.class);
                Bundle bundle = new Bundle();
                bundle.putString("poiId",shopbean.getPoiId());
                bundle.putString("poiName",shopbean.getShopName());
                intent.putExtras(bundle);
                startActivity(intent);
//                guideShopFragment.setArguments(bundle);
//                mFragmentTransaction.commit();
            }
        });

        icon_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent mintent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+shopbean.getShopPhone()));
//                startActivity(mintent);
            }
        });

    }

    private void initAdapter(){
        lv_saleinfo.setAdapter(sale_Adapter = new CommonAdapter<saleinfobean>(R.layout.sale_list,getApplicationContext(),sale_data) {
            @Override
            public void convert(ViewHolder holder, saleinfobean item) {
                holder.setImageResource(R.id.id_sale_list_iv_image,item.getSaleImage());
                holder.setText(R.id.id_sale_list_tv_salename,item.getSaleName());
                holder.setText(R.id.id_sale_list_tv_saletime,"活动日期："+item.getSaleTime());
            }
        });
        setListViewHeightBasedOnChildren(lv_saleinfo);
    }

    /**
     * Linean中嵌套ScrollView,其中的ListView只显示第一条数据，解决代码
     * @param listView
     */
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
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

                        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                    shopbean.setShopmallId(jsonObject.getString("shopmallId"));
                    shopbean.setShopId(jsonObject.getString("shopId"));
                    shopbean.setPoiId(jsonObject.getString("poiId"));
                    shopbean.setShopName(jsonObject.getString("shopName"));
                    shopbean.setShopAddress(jsonObject.getString("shopAddress"));
                    shopbean.setShopFloor(jsonObject.getString("shopFloor"));
                    shopbean.setShopType(jsonObject.getString("shopType"));
                    shopbean.setShopPhone(jsonObject.getString("shopPhone"));
                    shopbean.setShopInfo(jsonObject.getString("shopInfo"));
                    shopbean.setShopImage(FileUtils.URLIP+jsonObject.getString("shopImage"));
                    shopbean.setShopLogo(FileUtils.URLIP+jsonObject.getString("shopLogo"));


                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }


}