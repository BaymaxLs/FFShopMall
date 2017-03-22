package com.ffshopmall.model;

/**
 * Created by Baymax on 2017/3/14.
 */

public class shopbean {

    private int id;
    private int shopmall_id;
    private String name;
    private int image;
    private String type;
    private String floor;
    private String distance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShopmall_id() {
        return shopmall_id;
    }

    public void setShopmall_id(int shopmall_id) {
        this.shopmall_id = shopmall_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public shopbean(int id, int shopmall_id, String name, int image, String type, String floor, String distance) {
        this.id = id;
        this.shopmall_id = shopmall_id;
        this.name = name;
        this.image = image;
        this.type = type;
        this.floor = floor;
        this.distance = distance;
    }
}
