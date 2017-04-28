package com.ffshopmall.IndoorMap.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.amap.api.im.data.IMDataManager;
import com.amap.api.im.listener.IMMapEventListener;
import com.amap.api.im.listener.IMMapLoadListener;
import com.amap.api.im.listener.MapLoadStatus;
import com.amap.api.im.util.IMLog;
import com.amap.api.im.util.IMSearchResult;
import com.amap.api.im.view.IMIndoorMapFragment;
import com.ffshopmall.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/27.
 */

public class FFIndoorMapMain extends FragmentActivity {

    private IMIndoorMapFragment mIndoorMapFragment = null;
    private IMDataManager mDataManager = null;
    private ImageView icon_back;
    private String mLastSelectedPoiId = null;
    private Context mContext = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indoormap);
        mContext = getApplicationContext();

        initMap();

        initView();

        initEvent();

    }

    private void initMap() {
        mIndoorMapFragment = (IMIndoorMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_activity_indoormap_map_view);
        mDataManager = IMDataManager.getInstance();
        mIndoorMapFragment.loadMap("B03018OI07", mMapLoadListener);
        mIndoorMapFragment.setDataPath(Environment.getExternalStorageDirectory()+"/data_path");
        mIndoorMapFragment.setMapEventListener(mMapEventListener);
        mIndoorMapFragment.initSwitchFloorToolBar();
        mIndoorMapFragment.initZoomView();
        mIndoorMapFragment.initCompass();
        mIndoorMapFragment.hideAmapLogo();
    }

    private void initView() {

        icon_back = (ImageView) findViewById(R.id.id_map_top_icon_back);

    }

    private void initEvent() {

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDestroy();
            }
        });
    }

    private IMMapEventListener mMapEventListener = new IMMapEventListener() {

        @Override
        public void onFloorChange(int i) {

        }

        @Override
        public void onSelectedPoi(String poiId) {

            IMLog.logd("#######-------- onSelectedShop:" + poiId + " id:" + Thread.currentThread().getId());
            if (poiId != null && !poiId.equals("")) {
                if (mLastSelectedPoiId != null) {
                    mIndoorMapFragment.clearFeatureColor(mLastSelectedPoiId);
                }
                mLastSelectedPoiId = poiId;

                mIndoorMapFragment.selectFeature(poiId);//气泡

                mIndoorMapFragment.setFeatureColor(poiId, "0xffea8081");//高亮
                mIndoorMapFragment.setFeatureCenter(poiId);//居中
                mIndoorMapFragment.refreshMap();
                boolean ret = mIndoorMapFragment.positionInBuilding(111.3, 37.2);

                String toasttext = "";

                toasttext += "PoiId:" + poiId;
                IMSearchResult searchresult = mDataManager.searchByID(poiId);
                //toasttext += "-" + vec.size() + "-";
                if (searchresult != null) {
                    if (searchresult.getName() != null) {
                        toasttext += "\n" + "PoiName:" + searchresult.getName();
                    }
                    if (searchresult.getCatagory() != null) {
                        toasttext += "\n" + "基本分类:" + searchresult.getCatagory();
                        ArrayList<String> cats = new ArrayList<String>();
                        cats.add(searchresult.getCatagory());
                        toasttext += "\n" + "本类型个数:" + mDataManager.searchByCategories(cats, mDataManager.getCurrentFloorNo()).size();
                    }
                }
                Toast.makeText(mContext, toasttext, Toast.LENGTH_SHORT).show();

            } else {
                // 取消选择
//                mIndoorMapFragment.clearSelected();
//                mIndoorMapFragment.clearHighlight();
                //mLastSelectedPoiId = "";
            }

            FFIndoorMapDialogActivity dialogActivity = new FFIndoorMapDialogActivity();
            dialogActivity.show(getSupportFragmentManager(),"dialogActivity");

        }

        @Override
        public void onSingleTap(double v, double v1) {

        }

        @Override
        public void onDoubleTap() {

        }

        @Override
        public void onLongPress(double v, double v1) {

        }

        @Override
        public void onInclineBegin() {

        }

        @Override
        public void onIncline(float v, float v1, float v2) {

        }

        @Override
        public void onInclineEnd() {

        }

        @Override
        public void onScaleBegin() {

        }

        @Override
        public void onScale(float v, float v1, float v2) {

        }

        @Override
        public void onScaleEnd() {

        }

        @Override
        public void onTranslateBegin() {

        }

        @Override
        public void onTranslate(float v, float v1) {

        }

        @Override
        public void onTranslateEnd() {

        }

        @Override
        public void onRotateBegin() {

        }

        @Override
        public void onRotate(float v, float v1, float v2) {

        }

        @Override
        public void onRotateEnd() {

        }

        @Override
        public void onFrameDrawOver() {

        }

        @Override
        public void onMarkerClick(String s) {

        }
    };

//    /**
//     * 初始化定位按钮
//     */
//    private void initLocationButton() {
//
//        mLocationView = (ImageView) findViewById(R.id.locating_btn);
//
//        mLocationView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //IMLog.logd("#######-------- onClick ");
//
//                if (!mLocationStatus) {         // 处理开始定位
//                    startLocating();
//                    mLocationView.setImageResource(R.drawable.indoor_gps_locked);
//                    mLocationStatus = true;
//                    mFirstCenter = false;
//                } else {                    // 处理结束定位
//                    stopLocating();
//                    mLocationView.setImageResource(R.drawable.indoor_gps_unlocked);
//                    mIndoorMapFragment.clearLocatingPosition();
//                    mIndoorMapFragment.clearLocationOnFloorView();
//                    mIndoorMapFragment.refreshMap();
//                    mLocationStatus = false;
//                }
//
//            }
//        });
//    }

    /**
     * 地图加载回调接口
     */
    private IMMapLoadListener mMapLoadListener = new IMMapLoadListener() {

        @Override
        public void onMapLoadSuccess() {
            Toast.makeText(mIndoorMapFragment.getActivity(), "地图加载完毕",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onMapLoadFailure(MapLoadStatus mapLoadStatus) {
            Toast.makeText(mIndoorMapFragment.getActivity(), "地图加载失败,失败状态:" + mapLoadStatus, Toast.LENGTH_LONG).show();
        }
    };
}