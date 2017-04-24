package com.ffshopmall.model;

/**
 * Created by Baymax on 2017/4/19.
 */

public class saleinfobean {

    private int shopId;
    private String saleName;
    private int saleImage;
    private String saleTime;
    private String saleContent;

    public saleinfobean(int shopId, String saleName, int saleImage, String saleTime, String saleContent) {
        this.shopId = shopId;
        this.saleName = saleName;
        this.saleImage = saleImage;
        this.saleTime = saleTime;
        this.saleContent = saleContent;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public int getSaleImage() {
        return saleImage;
    }

    public void setSaleImage(int saleImage) {
        this.saleImage = saleImage;
    }

    public String getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(String saleTime) {
        this.saleTime = saleTime;
    }

    public String getSaleContent() {
        return saleContent;
    }

    public void setSaleContent(String saleContent) {
        this.saleContent = saleContent;
    }
}
