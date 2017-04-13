package com.ffshopmall.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ffshopmall.R;
import com.ffshopmall.adapter.FFMainTabAdapter;
import com.viewpagerindicator.TabPageIndicator;
import com.zaaach.citypicker.CityPickerActivity;

public class FFMainActivity extends FragmentActivity {

    private static final int REQUEST_CODE_PICK_CITY = 233;

    private LinearLayout top_location;
    private ImageView icon_search;
    private ImageView icon_city;
    private TextView tv_city;
    private ViewPager mViewPager;
    private TabPageIndicator mTabPageIndicator;
    private FFMainTabAdapter mAdapter;
    private ListView sm_list;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ffmain);

        initView();

        initEvent();

    }

    private void initEvent() {

        icon_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(FFMainActivity.this, CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
            }
        });

        tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(FFMainActivity.this, CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
            }
        });

        icon_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FFMainActivity.this, FFSearchActivity.class));
            }
        });

    }

    private void initView() {

        tv_city = (TextView) findViewById(R.id.id_top_tv_city);

        icon_search = (ImageView) findViewById(R.id.id_top_icon_search);
        icon_city = (ImageView) findViewById(R.id.id_top_icon_city);

        top_location = (LinearLayout) findViewById(R.id.id_top_location);

        sm_list = (ListView) findViewById(R.id.id_tab_sm_lv_mall);

        mViewPager = (ViewPager) findViewById(R.id.id_main_viewpager);
        mTabPageIndicator = (TabPageIndicator) findViewById(R.id.id_main_indicator);
        mAdapter = new FFMainTabAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        mTabPageIndicator.setViewPager(mViewPager, 0);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        Fragment mall_fragment = new Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("city","深圳");
        mall_fragment.setArguments(bundle);
        transaction.add(R.id.id_main_activity_ffmain,mall_fragment);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                tv_city.setText(city);

                Intent in = new Intent();

//                Fragment mall_fragment = new FFMainTabFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("city","深圳");
//                mall_fragment.setArguments(bundle);
//                transaction = manager.beginTransaction();
//                transaction.add(R.id.id_main_activity_ffmain,mall_fragment);
//                transaction.commitAllowingStateLoss();

            }
        }
    }
}
