package com.example.zerobasemission1.mission.servlet;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private Long id;
    private static Member instance;
    private List<WifiInfo> wifiInfoList;

    private Member(Long id) {
        this.id = id;
    }
    public static Member getInstance() {
        if (instance == null) {
            instance = new Member(1L);
        }
        return instance;
    }

    public Long getId() {
        return id;
    }

    public List<WifiInfo> getWifiInfoList() {
        return wifiInfoList;
    }
    public void addHistory(WifiInfo wifiInfo) {
        wifiInfoList.add(wifiInfo);
    }
}
