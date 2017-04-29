package com.ffshopmall.IndoorMap.Route;

import com.amap.api.im.util.IMFloorInfo;

import java.io.Serializable;

public class PoiInfo implements Serializable {
	private static final long serialVersionUID = 8589268912354164692L;
	public PoiMapCell cell;
	public IMFloorInfo floor;
	public String des;
	public int PoiInfoType;
}
