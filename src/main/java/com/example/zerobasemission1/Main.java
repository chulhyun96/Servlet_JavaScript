package com.example.zerobasemission1;

import com.example.zerobasemission1.mission.env_config.Config;

public class Main {
    public static void main(String[] args) {
        System.out.println(Config.DB_URL);
        System.out.println(Config.API_KEY);
        System.out.println(Config.DB_USER);
        System.out.println(Config.DB_PASSWORD);

    }
}
