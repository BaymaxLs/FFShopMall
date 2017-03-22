package com.ffshopmall.model;

/**
 * Created by Baymax on 2017/3/17.
 */

public class shopinfobean {

    private int shopId;
    private int shopImage;
    private int shopLogo;
    private String shopName;
    private String shopAddress;
    private String floorAddress;
    private String shopActivity;
    private String shopInfo;

    public shopinfobean(int shopId, int shopImage, int shopLogo, String shopName, String shopAddress, String floorAddress, String shopActivity, String shopInfo) {
        this.shopId = shopId;
        this.shopImage = shopImage;
        this.shopLogo = shopLogo;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.floorAddress = floorAddress;
        this.shopActivity = shopActivity;
        this.shopInfo = shopInfo;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getShopImage() {
        return shopImage;
    }

    public void setShopImage(int shopImage) {
        this.shopImage = shopImage;
    }

    public int getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(int shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getFloorAddress() {
        return floorAddress;
    }

    public void setFloorAddress(String floorAddress) {
        this.floorAddress = floorAddress;
    }

    public String getShopActivity() {
        return shopActivity;
    }

    public void setShopActivity(String shopActivity) {
        this.shopActivity = shopActivity;
    }

    public String getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(String shopInfo) {
        this.shopInfo = shopInfo;
    }
}
