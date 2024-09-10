package com.example.zerobasemission1.mission.servlet.entity;

public class Member {
    private Long id;
    private static Member instance;

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
}
