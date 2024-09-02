package com.example.zerobasemission1.mission;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class WebRequestUtil {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        String apiKey = Config.API_KEY;

        String url = "http://openapi.seoul.go.kr:8088/"+apiKey+"/json/TbPublicWifiInfo/1/2";
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String responseBody = response.body().string();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Object o = gson.fromJson(responseBody, Object.class);
            String json = gson.toJson(o); //
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
