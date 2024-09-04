package com.example.zerobasemission1.mission.servlet;

import com.example.zerobasemission1.mission.env_config.Config;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.sql.*;
import java.util.concurrent.TimeUnit;

public class WifiInfoRepository {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    final String BASE_URL = "http://openapi.seoul.go.kr:8088/";

    public WifiInfoRepository() {
        try {
            String dbUrl = Config.DB_URL;
            String dbId = Config.DB_USER;
            String dbPass = Config.DB_PASSWORD;
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(dbUrl, dbId, dbPass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int loadData() {
        JsonObject wifiJson = getWifiJsonObject();
        JsonObject publicWifiInfoJson = wifiJson.get("TbPublicWifiInfo").getAsJsonObject();
        return publicWifiInfoJson.get("list_total_count").getAsInt();
    }

    private JsonObject getWifiJsonObject() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .build();

        String urlBuilder = BASE_URL + Config.API_KEY + "/json/TbPublicWifiInfo/" + 18859 + "/" + 19858;
        Request request = new Request.Builder().url(urlBuilder).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("API 연결 실패 :  " + response);
            }
            String responseBody = response.body().string();
            return JsonParser.parseString(responseBody).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveToDatabase(JsonObject rows) {
        String sql = "INSERT INTO PublicWifiInfo ("
                + "X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, "
                + "X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, "
                + "X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, "
                + "X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        JsonArray jsonArr = rows.get("row").getAsJsonArray();
        try  {
            pstmt = conn.prepareStatement(sql);
            for (int page = 0; page < jsonArr.size(); page++) {
                JsonObject jsonObject = jsonArr.get(page).getAsJsonObject();
                pstmt.setString(1, jsonObject.get("X_SWIFI_MGR_NO").getAsString());
                pstmt.setString(2, jsonObject.get("X_SWIFI_WRDOFC").getAsString());
                pstmt.setString(3, jsonObject.get("X_SWIFI_MAIN_NM").getAsString());
                pstmt.setString(4, jsonObject.get("X_SWIFI_ADRES1").getAsString());
                pstmt.setString(5, jsonObject.get("X_SWIFI_ADRES2").getAsString());
                pstmt.setString(6, jsonObject.get("X_SWIFI_INSTL_FLOOR").getAsString());
                pstmt.setString(7, jsonObject.get("X_SWIFI_INSTL_TY").getAsString());
                pstmt.setString(8, jsonObject.get("X_SWIFI_INSTL_MBY").getAsString());
                pstmt.setString(9, jsonObject.get("X_SWIFI_SVC_SE").getAsString());
                pstmt.setString(10, jsonObject.get("X_SWIFI_CMCWR").getAsString());
                pstmt.setString(11, jsonObject.get("X_SWIFI_CNSTC_YEAR").getAsString());
                pstmt.setString(12, jsonObject.get("X_SWIFI_INOUT_DOOR").getAsString());
                pstmt.setString(13, jsonObject.get("X_SWIFI_REMARS3").getAsString());
                pstmt.setString(14, jsonObject.get("LAT").getAsString());
                pstmt.setString(15, jsonObject.get("LNT").getAsString());
                pstmt.setString(16, jsonObject.get("WORK_DTTM").getAsString());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("오류");
        }
    }
}

