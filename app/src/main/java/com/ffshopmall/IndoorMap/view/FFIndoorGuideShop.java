package com.ffshopmall.IndoorMap.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.im.data.IMDataManager;
import com.amap.api.im.data.IMRoutePlanning;
import com.amap.api.im.listener.IMMapEventListener;
import com.amap.api.im.listener.IMMapLoadListener;
import com.amap.api.im.listener.IMRoutePlanningListener;
import com.amap.api.im.listener.MapLoadStatus;
import com.amap.api.im.listener.RoutePLanningStatus;
import com.amap.api.im.util.IMFloorInfo;
import com.amap.api.im.util.IMLog;
import com.amap.api.im.util.IMPoint;
import com.amap.api.im.util.IMSearchResult;
import com.amap.api.im.view.IMIndoorMapFragment;
import com.ffshopmall.IndoorMap.Route.PoiInfo;
import com.ffshopmall.IndoorMap.Route.PoiMapCell;
import com.ffshopmall.IndoorMap.utils.Constant;
import com.ffshopmall.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/27.
 */

public class FFIndoorGuideShop extends FragmentActivity {

    private IMIndoorMapFragment mIndoorMapFragment = null;
    private IMDataManager mDataManager = null;
    private ImageView icon_back;
    private String mLastSelectedPoiId = null;
    private Context mContext = null;
    private TextView tv_title;

    //导航规划参数
    private IMPoint mLocationPoint = new IMPoint(113.780284, 23.035553,4);
    private String mLocationBdId = "";
    PoiInfo mInfoFrom;
    PoiInfo mInfoTo;
    private String shopPoiId = null;
    private String shopName = null;

    public void setPoiInfoFrom(){
        setPoiInfoFrom(null);
    }
    public void setPoiInfoFrom(PoiInfo mInfoFrom){
        if(mInfoFrom==null){
            this.mInfoFrom =  loadMylocation();
        }else{
            this.mInfoFrom=mInfoFrom;
        }
    }
    public void setPoiInfoTo(PoiInfo mInfoTo){
        if (mInfoTo == null) {
            return;
        }
        this.mInfoTo = mInfoTo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indoormap);
        mContext = getApplicationContext();

        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null ) {
            shopPoiId = bundle.getString("poiId");
            shopName = bundle.getString("poiName");
        }

        initMap();

        initView();

        initEvent();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus){
            if(shopPoiId != null){
                Guide(shopPoiId);
            }
        }
    }

    private void initMap() {
        mIndoorMapFragment = (IMIndoorMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_activity_indoormap_map_view);
        mIndoorMapFragment.setStyleDirectory("im_style");
        mIndoorMapFragment.setIconDirectory("im_icon");
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
        tv_title = (TextView) findViewById(R.id.id_map_top_tv_mallname);

        tv_title.setText("导航至"+shopName);
    }

    private void initEvent() {

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIndoorMapFragment.clearAllData();
                finish();
            }
        });
    }

    /**
     * 初始化出发位置
     */
    private void InitFromMyLocation(){

        PoiInfo info = new PoiInfo();
        info.PoiInfoType = Constant.TYPE_ROUTE_PLANNING_LOCATION;
        String namecode="F1";

        info.cell = new PoiMapCell(0 ,mLocationPoint.getX(), mLocationPoint.getY(),
                mLocationPoint.getZ(), "我的位置");
        info.floor = new IMFloorInfo(mLocationPoint.getZ(), namecode, "-1");

        setPoiInfoFrom(info);
    }

    /**
     * 初始化目标商铺位置
     * @param poiId
     */
    private void InitToPoi(String poiId){
        PoiInfo tmpPoiInfo = new PoiInfo();
        tmpPoiInfo.PoiInfoType = Constant.TYPE_ROUTE_PLANNING_POI;
        IMSearchResult mSearchResult = mDataManager.searchByID(poiId);
        System.out.println("2222"+mDataManager.searchByID("GD0009320210100003"));
        System.out.println("3333"+mSearchResult);
        int floorNo = mSearchResult.getFloorNo();
        IMFloorInfo tmpFloorInfo = new IMFloorInfo(floorNo, "");
        PoiMapCell tmpPoiMapCell = new PoiMapCell();
        tmpPoiMapCell.setFloorNo(floorNo);
        tmpPoiMapCell.setPoiId(poiId);
        tmpPoiMapCell.setName(mSearchResult.getName());
        tmpPoiInfo.cell = tmpPoiMapCell;
        tmpPoiInfo.floor = tmpFloorInfo;

        //设置到店铺的位置
        setPoiInfoTo(tmpPoiInfo);
    }

    /**
     * 加载指定的位置
     */
    private PoiInfo loadMylocation() {
        String curBdId = mIndoorMapFragment.getCurrentBuildingId();
        if (mLocationPoint == null || mLocationBdId == null || !mLocationBdId.equals(curBdId)) {
            new AlertDialog.Builder(mIndoorMapFragment.getActivity())
                    .setTitle("提示")
                    .setMessage("没有定位结果,无法使用定位位置!")
                    .setIcon(
                            android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("确定", null).show();
            return null;
        }

        PoiInfo info = new PoiInfo();
        info.PoiInfoType = Constant.TYPE_ROUTE_PLANNING_LOCATION;
        String namecode="F1";

        info.cell = new PoiMapCell(0 ,mLocationPoint.getX(), mLocationPoint.getY(),
                mLocationPoint.getZ(), "我的位置");
        info.floor = new IMFloorInfo(mLocationPoint.getZ(), namecode, "-1");
        System.out.println("77777"+info.cell);
        return info;
    }

    /**
     * 导航模块
     */
    private void Guide(String shopPoiId){

        InitFromMyLocation();

        InitToPoi(shopPoiId);

        IMRoutePlanning routePlanning = new IMRoutePlanning(getApplicationContext(),
                mRoutePlanningListener);
        String buildingId = IMDataManager.getInstance().getCurrentBuildingId();

        PoiMapCell fromMapCell = mInfoFrom.cell;
        PoiMapCell toMapCell = mInfoTo.cell;

        IMLog.logd("####### ------ from PoiInfoType:" + mInfoFrom.PoiInfoType);
        IMLog.logd("####### ------ to PoiInfoType:" + mInfoTo.PoiInfoType);

        if (mInfoFrom.PoiInfoType == Constant.TYPE_ROUTE_PLANNING_LOCATION) {
            routePlanning.excutePlanningPointToPoi(buildingId, fromMapCell.getFloorNo(),
                    fromMapCell.getX(), fromMapCell.getY(), toMapCell.getPoiId());
            IMLog.logd("####### ------ start Point2Poi");
            return;
        }
    }

    private IMRoutePlanningListener mRoutePlanningListener = new IMRoutePlanningListener() {
        @Override
        public void onPlanningSuccess(String routePlanningData) {
            Toast.makeText(getApplicationContext(), "路算成功", Toast.LENGTH_LONG).show();
            mIndoorMapFragment.clearRouteStart();
            mIndoorMapFragment.clearRouteStop();
            mIndoorMapFragment.clearSelected();
            mIndoorMapFragment.clearFeatureColor("");
            drawRouteStartAndStop(routePlanningData);
            mIndoorMapFragment.refreshMap();

        }

        @Override
        public void onPlanningFailure(RoutePLanningStatus routePLanningStatus) {

        }
    };

    /**
     * 设置路径规划开始点和停止点
     */
    public void drawRouteStartAndStop(String routePlanningData) {
//        String fromPoiId = mInfoFrom.cell.getPoiId();
//        String toPoiId = mInfoTo.cell.getPoiId();
        String fromPoiId = "113.781348,23.034888";
        String toPoiId = "";
        IMLog.logd("####### ------ from:" + fromPoiId + ", to:" + toPoiId);

        if (fromPoiId != null && !fromPoiId.equals("")) {
            mIndoorMapFragment.setRouteStart(fromPoiId);
        }

        if (toPoiId != null && !toPoiId.equals("")) {
            mIndoorMapFragment.setRouteStop(toPoiId);
        }

        mIndoorMapFragment.setRouteData(routePlanningData);
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
                System.out.println("poiId:"+poiId);
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
//            Guide(poiId);
//          mIndoorMapFragment.getCurrentSelectSourceId() 点击店铺可以获取poiId
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