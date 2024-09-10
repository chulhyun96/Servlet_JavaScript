package com.example.zerobasemission1.mission.servlet;

import java.time.LocalDateTime;

public class Bookmark {
    private Long id;
    private String name;
    private Long orders;
    private LocalDateTime createdDate;
    private LocalDateTime modifyDate;
    private Boolean status;

    public Bookmark() {
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orders=" + orders +
                ", createdDate=" + createdDate +
                ", modifyDate=" + modifyDate +
                ", status=" + status +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrders() {
        return orders;
    }

    public void setOrders(Long orders) {
        this.orders = orders;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }
}
