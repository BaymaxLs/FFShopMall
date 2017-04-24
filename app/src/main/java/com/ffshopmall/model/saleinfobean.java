package com.ffshopmall.model;

/**
 * Created by Baymax on 2017/4/19.
 */

public class saleinfobean {

    private int saleId;
    private int shopId;
    private String saleName;
    private int saleImage;
    private int saleImage2;
    private String saleTime;
    private String saleContent;

    public saleinfobean(int saleId, int shopId, String saleName, int saleImage, int saleImage2, String saleTime, String saleContent) {
        this.saleId = saleId;
        this.shopId = shopId;
        this.saleName = saleName;
        this.saleImage = saleImage;
        this.saleImage2 = saleImage2;
        this.saleTime = saleTime;
        this.saleContent = saleContent;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
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

    public int getSaleImage2() {
        return saleImage2;
    }

    public void setSaleImage2(int saleImage2) {
        this.saleImage2 = saleImage2;
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
