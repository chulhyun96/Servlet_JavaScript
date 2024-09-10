package com.example.zerobasemission1.mission.servlet;

import java.time.LocalDateTime;

public class Favorite {
    private Long id;
    private String bookmarkName;
    private String wifiName;
    private LocalDateTime createDate;
    private Boolean status;

    public Favorite() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookmarkName() {
        return bookmarkName;
    }

    public void setBookmarkName(String bookmarkName) {
        this.bookmarkName = bookmarkName;
    }

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", bookmarkName='" + bookmarkName + '\'' +
                ", wifiName='" + wifiName + '\'' +
                ", createDate=" + createDate +
                ", status=" + status +
                '}';
    }
}
