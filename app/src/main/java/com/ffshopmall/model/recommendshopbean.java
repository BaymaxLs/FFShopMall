package com.ffshopmall.model;

/**
 * Created by Baymax on 2017/4/15.
 */

public class recommendshopbean {

    private String shopId;
    private int shopLogo;
    private String shopName;
    private String shopGoods1;
    private String shopGoods2;
    private String shopGoods3;
    private int rs_Num;

    public recommendshopbean(String shopId, int shopLogo, String shopName, String shopGoods1, String shopGoods2, String shopGoods3, int rs_Num) {
        this.shopId = shopId;
        this.shopLogo = shopLogo;
        this.shopName = shopName;
        this.shopGoods1 = shopGoods1;
        this.shopGoods2 = shopGoods2;
        this.shopGoods3 = shopGoods3;
        this.rs_Num = rs_Num;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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

    public String getShopGoods1() {
        return shopGoods1;
    }

    public void setShopGoods1(String shopGoods1) {
        this.shopGoods1 = shopGoods1;
    }

    public String getShopGoods2() {
        return shopGoods2;
    }

    public void setShopGoods2(String shopGoods2) {
        this.shopGoods2 = shopGoods2;
    }

    public String getShopGoods3() {
        return shopGoods3;
    }

    public void setShopGoods3(String shopGoods3) {
        this.shopGoods3 = shopGoods3;
    }

    public int getRs_Num() {
        return rs_Num;
    }

    public void setRs_Num(int rs_Num) {
        this.rs_Num = rs_Num;
    }
}
