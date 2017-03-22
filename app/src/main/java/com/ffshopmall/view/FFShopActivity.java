package com.ffshopmall.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ffshopmall.R;
import com.ffshopmall.model.shopinfobean;

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

    private shopinfobean bean;
    private List<shopinfobean> list;

    public List<shopinfobean> getList() {

        list = new ArrayList<shopinfobean>();
        list.add(new shopinfobean(101,R.drawable.mxcib,R.drawable.starbuck,"星巴克","广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处","L1-122","周五买一送一","星巴克(Starbucks)是美国一家连锁咖啡公司的名称，1971年成立，为全球最大的咖啡连锁店，其总部坐落美国华盛顿州西雅图市。星巴克在全球范围内已经有近12,000间分店遍布北美、南美洲、欧洲、中东及太平洋区。星巴克旗下零售产品包括30多款全球顶级的咖啡豆、手工制作的浓缩咖啡和多款咖啡冷热饮料、新鲜美味的各式糕点食品以及丰富多样的咖啡机、咖啡杯等商品。"));
        list.add(new shopinfobean(102,R.drawable.mxcib,R.drawable.starbuck,"GAP","广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处","L1-122","周五买一送一","星巴克(Starbucks)是美国一家连锁咖啡公司的名称，1971年成立，为全球最大的咖啡连锁店，其总部坐落美国华盛顿州西雅图市。星巴克在全球范围内已经有近12,000间分店遍布北美、南美洲、欧洲、中东及太平洋区。星巴克旗下零售产品包括30多款全球顶级的咖啡豆、手工制作的浓缩咖啡和多款咖啡冷热饮料、新鲜美味的各式糕点食品以及丰富多样的咖啡机、咖啡杯等商品。"));
        list.add(new shopinfobean(103,R.drawable.mxcib,R.drawable.starbuck,"GAT","广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处","L1-122","周五买一送一","星巴克(Starbucks)是美国一家连锁咖啡公司的名称，1971年成立，为全球最大的咖啡连锁店，其总部坐落美国华盛顿州西雅图市。星巴克在全球范围内已经有近12,000间分店遍布北美、南美洲、欧洲、中东及太平洋区。星巴克旗下零售产品包括30多款全球顶级的咖啡豆、手工制作的浓缩咖啡和多款咖啡冷热饮料、新鲜美味的各式糕点食品以及丰富多样的咖啡机、咖啡杯等商品。"));
        return list;
    }

    private shopinfobean getBean(int shopId){
        for(shopinfobean newbean : list){
            if (newbean.getShopId() == shopId) {
                bean = new shopinfobean(newbean.getShopId(),newbean.getShopImage(),newbean.getShopLogo(),newbean.getShopName(),newbean.getShopAddress(),newbean.getFloorAddress(),newbean.getShopActivity(),newbean.getShopInfo());
            }
        }

        return bean;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_shop);

        initView();

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
        tv_shopActivity = (TextView) findViewById(R.id.id_activity_shop_tv_activity);
        tv_shopInfo = (TextView) findViewById(R.id.id_activity_shop_tv_shopinfo);

        getList();

        Bundle bundle = this.getIntent().getExtras();
        getBean(bundle.getInt("shopId"));

        iv_shopImage.setImageResource(bean.getShopImage());
        iv_shopLogo.setImageResource(bean.getShopLogo());
        tv_shopName.setText(bean.getShopName());
        tv_shopAddress.setText(bean.getShopAddress());
        tv_floorAddress.setText(bean.getFloorAddress());
        tv_shopGuide.setText("导航到"+bean.getShopName());
        tv_shopActivity.setText(bean.getShopActivity());
        tv_shopInfo.setText(bean.getShopInfo());

    }
    private void initEvent() {
        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FFShopActivity.this.finish();
            }
        });
    }
}
