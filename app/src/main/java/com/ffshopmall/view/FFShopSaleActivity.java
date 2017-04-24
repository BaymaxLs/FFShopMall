package com.ffshopmall.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ffshopmall.R;
import com.ffshopmall.adapter.CommonAdapter;
import com.ffshopmall.model.saleinfobean;
import com.ffshopmall.model.shopinfobean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class FFShopSaleActivity extends Activity {

    private ImageView icon_back;
    private ImageView iv_saleimage;
    private TextView tv_salename;
    private LinearLayout ll_shop;
    private TextView tv_shopname;
    private LinearLayout ll_phone;
    private LinearLayout ll_guide;
    private TextView tv_saledetails;

    private saleinfobean salebean;
    private List<saleinfobean> sale_data;

    private shopinfobean shopbean;
    private List<shopinfobean> shop_data;

    public List<shopinfobean> getList() {

        shop_data = new ArrayList<shopinfobean>();
        shop_data.add(new shopinfobean(101,R.drawable.mxcib,R.drawable.starbuck,"星巴克","广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处","L1-122","周五买一送一","星巴克(Starbucks)是美国一家连锁咖啡公司的名称，1971年成立，为全球最大的咖啡连锁店，其总部坐落美国华盛顿州西雅图市。星巴克在全球范围内已经有近12,000间分店遍布北美、南美洲、欧洲、中东及太平洋区。星巴克旗下零售产品包括30多款全球顶级的咖啡豆、手工制作的浓缩咖啡和多款咖啡冷热饮料、新鲜美味的各式糕点食品以及丰富多样的咖啡机、咖啡杯等商品。"));
        shop_data.add(new shopinfobean(102,R.drawable.mxcib,R.drawable.starbuck,"GAP","广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处","L1-122","周五买一送一","星巴克(Starbucks)是美国一家连锁咖啡公司的名称，1971年成立，为全球最大的咖啡连锁店，其总部坐落美国华盛顿州西雅图市。星巴克在全球范围内已经有近12,000间分店遍布北美、南美洲、欧洲、中东及太平洋区。星巴克旗下零售产品包括30多款全球顶级的咖啡豆、手工制作的浓缩咖啡和多款咖啡冷热饮料、新鲜美味的各式糕点食品以及丰富多样的咖啡机、咖啡杯等商品。"));
        shop_data.add(new shopinfobean(103,R.drawable.mxcib,R.drawable.starbuck,"GAT","广东省东莞市南城区鸿福路200号（鸿福路与东莞大道交汇处","L1-122","周五买一送一","星巴克(Starbucks)是美国一家连锁咖啡公司的名称，1971年成立，为全球最大的咖啡连锁店，其总部坐落美国华盛顿州西雅图市。星巴克在全球范围内已经有近12,000间分店遍布北美、南美洲、欧洲、中东及太平洋区。星巴克旗下零售产品包括30多款全球顶级的咖啡豆、手工制作的浓缩咖啡和多款咖啡冷热饮料、新鲜美味的各式糕点食品以及丰富多样的咖啡机、咖啡杯等商品。"));
        return shop_data;
    }

    public List<saleinfobean> getSale_data(){
        sale_data = new ArrayList<saleinfobean>();
        sale_data.add(new saleinfobean(10101,101,"当季新品 冷萃冰咖啡",R.drawable.starbuck_saleimage,R.drawable.starbuck_saleimage11,"2017.04.01 - 2017.06.01","去年夏天让你爱不释手的冷萃冰咖啡，现在终于回归了。而今年，星巴克西雅图咖啡团队又进一步探索关于它的更多可能，新研发了一款“轻甜香草风味奶油冷萃冰咖啡”，快去你身边的星巴克门店，感受专属咖啡豆的甘醇与本味。（同期还有全新设计的冷萃咖啡星享卡）"));
        sale_data.add(new saleinfobean(10102,101,"当季新品 星冰粽",R.drawable.starbuck_saleimage2,R.drawable.starbuck_saleimage22,"2017.05.01 - 2017.07.01","悠闲的夏午后，怎能少得清甜品的陪伴。全新星巴克星冰粽，将你喜欢的饮品冷萃和桃桃变成甜品，多重味的冰沁甜点，打造你的轻甜夏时光。"));
        return sale_data;
    }

    private shopinfobean getBean(int shopId){
        for(shopinfobean newbean : shop_data){
            if (newbean.getShopId() == shopId) {
                shopbean = new shopinfobean(newbean.getShopId(),newbean.getShopImage(),newbean.getShopLogo(),newbean.getShopName(),newbean.getShopAddress(),newbean.getFloorAddress(),newbean.getShopActivity(),newbean.getShopInfo());
            }
        }
        return shopbean;
    }

    private saleinfobean getSaleinfobean(int saleId){
        for(saleinfobean newbean : sale_data){
            if(newbean.getSaleId() == saleId){
                salebean = new saleinfobean(newbean.getSaleId(),newbean.getShopId(),newbean.getSaleName(),newbean.getSaleImage(),newbean.getSaleImage2(),newbean.getSaleTime(),newbean.getSaleContent());
            }
        }
        return salebean;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_saleinfo);

        initView();

        initEvent();

    }

    private void initView() {
        icon_back = (ImageView) findViewById(R.id.id_saleinfo_top_icon_back);
        iv_saleimage = (ImageView) findViewById(R.id.id_activity_shop_saleinfo_iv_saleimage);
        tv_salename = (TextView) findViewById(R.id.id_activity_shop_saleinfo_tv_salename);
        ll_shop = (LinearLayout) findViewById(R.id.id_activity_shop_saleinfo_layout_shop);
        ll_phone = (LinearLayout) findViewById(R.id.id_activity_shop_saleinfo_layout_phone);
        tv_shopname = (TextView) findViewById(R.id.id_activity_shop_saleinfo_tv_shopname);
        ll_guide = (LinearLayout) findViewById(R.id.id_activity_shop_saleinfo_layout_guide);
        tv_saledetails = (TextView) findViewById(R.id.id_activity_shop_saleinfo_tv_saledetails);

        getList();
        getSale_data();

        Bundle bundle = this.getIntent().getExtras();
        getBean(bundle.getInt("shopId"));
        getSaleinfobean(bundle.getInt("saleId"));

        iv_saleimage.setImageResource(salebean.getSaleImage2());
        tv_salename.setText(salebean.getSaleName());
        tv_shopname.setText(shopbean.getShopName());
        tv_saledetails.setText(salebean.getSaleContent());
    }

    private void initEvent() {

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FFShopSaleActivity.this.finish();
            }
        });

        ll_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("shopId",shopbean.getShopId());
                intent.putExtras(bundle);
                intent.setClass(FFShopSaleActivity.this,FFShopActivity.class);
                startActivity(intent);
            }
        });

    }
}
