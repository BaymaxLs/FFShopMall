package com.ffshopmall.IndoorMap.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amap.api.im.data.IMDataManager;
import com.amap.api.im.data.IMRoutePlanning;
import com.amap.api.im.listener.IMRoutePlanningListener;
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

/**
 * Created by Administrator on 2017/4/29.
 */

public class FFIndoorGuideShop extends Fragment  {

    private View mView = null;
    private IMIndoorMapFragment mIndoorMapFragment = null;
    private IMDataManager mDataManager = null;
    private IMPoint mLocationPoint = new IMPoint(113.780284, 23.035553);
    private String mLocationBdId = "";
    PoiInfo mInfoFrom;
    PoiInfo mInfoTo;

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



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_indoormap,container,false);

        Bundle bundle = getArguments();
        String shopPoiId = bundle.getString("poiId");
        System.out.println("@@@@@@poiId:"+shopPoiId);

        InitFromMyLocation();

        InitToPoi(shopPoiId);

        Guide();
        return mView;
    }

    private void InitFromMyLocation(){

        PoiInfo info = new PoiInfo();
        info.PoiInfoType = Constant.TYPE_ROUTE_PLANNING_LOCATION;
        String namecode="F1";

        info.cell = new PoiMapCell(0 ,mLocationPoint.getX(), mLocationPoint.getY(),
                mLocationPoint.getZ(), "我的位置");
        info.floor = new IMFloorInfo(mLocationPoint.getZ(), namecode, "-1");
    }

    private void InitToPoi(String poiId){
        PoiInfo tmpPoiInfo = new PoiInfo();
        tmpPoiInfo.PoiInfoType = Constant.TYPE_ROUTE_PLANNING_POI;
        IMSearchResult mSearchResult = mDataManager.searchByID(poiId);
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

    private void Guide(){
        IMRoutePlanning routePlanning = new IMRoutePlanning(this.getActivity(),
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
            Toast.makeText(getActivity(), "路算成功", Toast.LENGTH_LONG).show();
            mIndoorMapFragment.clearRouteStart();
            mIndoorMapFragment.clearRouteStop();
            mIndoorMapFragment.clearSelected();
            mIndoorMapFragment.clearFeatureColor("");
            drawRouteStartAndStop(routePlanningData);
            mIndoorMapFragment.refreshMap();
            finish(null);

        }

        @Override
        public void onPlanningFailure(RoutePLanningStatus routePLanningStatus) {

        }
    };

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
        return info;
    }

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

    /**
     * 关闭页面
     * @param bundle
     */
    public void finish(Bundle bundle) {

        FragmentTransaction transcation = getActivity().getSupportFragmentManager().beginTransaction();
        transcation.setCustomAnimations(0, 0, 0,0);
        transcation.hide(this).show(mIndoorMapFragment);
        transcation.commit();
//        mSearchEditText.setVisibility(View.VISIBLE);
//        mSearchEditText.bringToFront();
//        mLocationView.setVisibility(View.VISIBLE);
//        mLocationView.bringToFront();
    }

    public void setLocationBdId(String locationBdId) {
        this.mLocationBdId = locationBdId;
    }

}
