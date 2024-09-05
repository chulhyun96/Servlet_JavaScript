package com.example.zerobasemission1.mission.servlet;


import java.text.DecimalFormat;

public class WifiInfo {
    private String id;
    private String wrdofc;
    private String nm;
    private String address1;
    private String address2;
    private String floor;
    private String ty;
    private String mby;
    private String se;
    private String cmcwr;
    private String year;
    private String door;
    private String remars3;
    private Double lat;
    private Double lnt;
    private Double distance;
    private String dttm;
    private DecimalFormat df = new DecimalFormat("#.####");


    public WifiInfo() {
    }

    public WifiInfo(String id, String wrdofc, String nm, String address1, String address2, String floor, String ty, String mby, String se, String cmcwr, String year, String door, String remars3, Double lat, Double lnt, Double distance, String dttm) {
        this.id = id;
        this.wrdofc = wrdofc;
        this.nm = nm;
        this.address1 = address1;
        this.address2 = address2;
        this.floor = floor;
        this.ty = ty;
        this.mby = mby;
        this.se = se;
        this.cmcwr = cmcwr;
        this.year = year;
        this.door = door;
        this.remars3 = remars3;
        this.lat = lat;
        this.lnt = lnt;
        this.distance = distance;
        this.dttm = dttm;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = Double.valueOf(df.format(distance));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWrdofc() {
        return wrdofc;
    }

    public void setWrdofc(String wrdofc) {
        this.wrdofc = wrdofc;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getTy() {
        return ty;
    }

    public void setTy(String ty) {
        this.ty = ty;
    }

    public String getMby() {
        return mby;
    }

    public void setMby(String mby) {
        this.mby = mby;
    }

    public String getSe() {
        return se;
    }

    public void setSe(String se) {
        this.se = se;
    }

    public String getCmcwr() {
        return cmcwr;
    }

    public void setCmcwr(String cmcwr) {
        this.cmcwr = cmcwr;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getRemars3() {
        return remars3;
    }

    public void setRemars3(String remars3) {
        this.remars3 = remars3;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLnt() {
        return lnt;
    }

    public void setLnt(Double lnt) {
        this.lnt = lnt;
    }

    public String getDttm() {
        return dttm;
    }

    public void setDttm(String dttm) {
        this.dttm = dttm;
    }
}
