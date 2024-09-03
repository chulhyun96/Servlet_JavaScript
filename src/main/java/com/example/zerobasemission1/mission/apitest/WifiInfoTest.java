package com.example.zerobasemission1.mission.apitest;

import com.example.zerobasemission1.mission.env_config.Config;
import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class WifiInfoTest {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        String apiKey = Config.API_KEY;
        String url = "http://openapi.seoul.go.kr:8088/" + apiKey + "/json/TbPublicWifiInfo/1/5";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String responseBody = response.body().string();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
            JsonObject tbPublicWifiInfo = json.get("TbPublicWifiInfo").getAsJsonObject();

            int totalCount = tbPublicWifiInfo.get("list_total_count").getAsInt();

            JsonArray rows = tbPublicWifiInfo.getAsJsonArray("row");
            rows.forEach(row -> {
                JsonObject rowElement = row.getAsJsonObject();
                rowElement.entrySet().forEach(entry -> {
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                });
            });

            System.out.println("============TOTAL COUNT============");
            System.out.println("TOTAL Count = " +totalCount);
            System.out.println("JSON PRINT " + gson.toJson(json));
            System.out.println("============TOTAL COUNT============");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
