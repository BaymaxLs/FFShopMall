package com.ffshopmall.model;

/**
 * Created by Baymax on 2017/3/13.
 */

public class bean {

    private int id;
    private String city;
    private String title;
    private int image;
    private String address;
    private String distance;

    public bean(int id, String city, String title, int image, String address, String distance) {
        this.id = id;
        this.city = city;
        this.title = title;
        this.image = image;
        this.address = address;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
