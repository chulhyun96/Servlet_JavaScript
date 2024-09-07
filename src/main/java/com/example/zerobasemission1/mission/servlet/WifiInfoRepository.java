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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
        /*JsonObject wifiJson = getWifiJsonObject();
        JsonObject publicWifiInfoJson = wifiJson.get("TbPublicWifiInfo").getAsJsonObject();
        saveToDatabase(publicWifiInfoJson);*/
        String sql = "SELECT count(*) FROM PublicWifiInfo";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    private JsonObject getWifiJsonObject() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .build();

        String urlBuilder = BASE_URL + Config.API_KEY + "/json/TbPublicWifiInfo/" + 25859 + "/" + 26567;
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

                pstmt.setDouble(14, jsonObject.get("LAT").getAsDouble());
                pstmt.setDouble(15, jsonObject.get("LNT").getAsDouble());

                pstmt.setString(16, jsonObject.get("WORK_DTTM").getAsString());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("오류");
        }
    }

    public List<WifiInfo> findSearchResult(double lat, double lnt) {
        String sql = "SELECT *, " +
                "(6371 * ACOS(COS(RADIANS(?)) * COS(RADIANS(LAT)) * COS(RADIANS(LNT) - RADIANS(?)) + " +
                "SIN(RADIANS(?)) * SIN(RADIANS(LAT)))) AS distance " +
                "FROM PublicWifiInfo " +
                "HAVING distance <= 5 " +
                "ORDER BY distance " +
                "LIMIT 20";
        List<WifiInfo> list = new ArrayList<>();

        try  {
            pstmt = conn.prepareStatement(sql);

            pstmt.setDouble(1, lat); // 사용자의 위도
            pstmt.setDouble(2, lnt); // 사용자의 경도
            pstmt.setDouble(3, lat); // 사용자의 위도 (거리 계산을 위한 값)

            rs = pstmt.executeQuery();
            while (rs.next()) {
                WifiInfo wifiInfo = new WifiInfo();
                wifiInfo.setId(rs.getString("X_SWIFI_MGR_NO"));
                wifiInfo.setWrdofc(rs.getString("X_SWIFI_WRDOFC"));
                wifiInfo.setNm(rs.getString("X_SWIFI_MAIN_NM"));
                wifiInfo.setAddress1(rs.getString("X_SWIFI_ADRES1"));
                wifiInfo.setAddress2(rs.getString("X_SWIFI_ADRES2"));
                wifiInfo.setFloor(rs.getString("X_SWIFI_INSTL_FLOOR"));
                wifiInfo.setTy(rs.getString("X_SWIFI_INSTL_TY"));
                wifiInfo.setMby(rs.getString("X_SWIFI_INSTL_MBY"));
                wifiInfo.setSe(rs.getString("X_SWIFI_SVC_SE"));
                wifiInfo.setCmcwr(rs.getString("X_SWIFI_CMCWR"));
                wifiInfo.setYear(rs.getString("X_SWIFI_CNSTC_YEAR"));
                wifiInfo.setDoor(rs.getString("X_SWIFI_INOUT_DOOR"));
                wifiInfo.setRemars3(rs.getString("X_SWIFI_REMARS3"));
                wifiInfo.setLat(rs.getDouble("LAT"));
                wifiInfo.setLnt(rs.getDouble("LNT"));
                wifiInfo.setDttm(rs.getString("WORK_DTTM"));
                wifiInfo.setDistance(rs.getDouble("distance"));
                list.add(wifiInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public WifiInfo findById(String id) {
        String sql = "SELECT * FROM PublicWifiInfo WHERE X_SWIFI_MGR_NO = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                WifiInfo wifiInfo = new WifiInfo();
                wifiInfo.setId(rs.getString("X_SWIFI_MGR_NO"));
                wifiInfo.setWrdofc(rs.getString("X_SWIFI_WRDOFC"));
                wifiInfo.setNm(rs.getString("X_SWIFI_MAIN_NM"));
                wifiInfo.setAddress1(rs.getString("X_SWIFI_ADRES1"));
                wifiInfo.setAddress2(rs.getString("X_SWIFI_ADRES2"));
                wifiInfo.setFloor(rs.getString("X_SWIFI_INSTL_FLOOR"));
                wifiInfo.setTy(rs.getString("X_SWIFI_INSTL_TY"));
                wifiInfo.setMby(rs.getString("X_SWIFI_INSTL_MBY"));
                wifiInfo.setSe(rs.getString("X_SWIFI_SVC_SE"));
                wifiInfo.setCmcwr(rs.getString("X_SWIFI_CMCWR"));
                wifiInfo.setYear(rs.getString("X_SWIFI_CNSTC_YEAR"));
                wifiInfo.setDoor(rs.getString("X_SWIFI_INOUT_DOOR"));
                wifiInfo.setRemars3(rs.getString("X_SWIFI_REMARS3"));
                wifiInfo.setLat(rs.getDouble("LAT"));
                wifiInfo.setLnt(rs.getDouble("LNT"));
                wifiInfo.setDttm(rs.getString("WORK_DTTM"));
                wifiInfo.setDistance(0d);
                return wifiInfo;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new WifiInfo(); // 빈 객체 반환
    }

    public int saveMyHistory( Long memberId, double lat, double lnt, boolean status) {
        String sql = "INSERT INTO HISTORY (member_id,lat, lnt, date, status) VALUES (?,?, ?, ?, ?)";

        LocalDateTime now = LocalDateTime.now();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            pstmt.setDouble(2, lat);
            pstmt.setDouble(3, lnt);
            pstmt.setTimestamp(4, Timestamp.valueOf(now));
            pstmt.setBoolean(5,status);
            int rowAffected = pstmt.executeUpdate();
            if (rowAffected > 0) System.out.println("HISTORY 목록 추가 완료." + rowAffected);
            return rowAffected;
        } catch (SQLException e) {
            System.out.println("데이터 삽입 중 오류 발생: " + e.getMessage());
            return -1;
        }
    }

    public List<History> findMyHistoryList(Long id) {
        String sql = "SELECT id, lat, lnt, date, status FROM HISTORY WHERE member_id = ? and status = ?";
        List<History> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.setBoolean(2, true);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                History history = new History();
                history.setId(rs.getLong("id"));
                history.setLat(rs.getDouble("lat"));
                history.setLnt(rs.getDouble("lnt"));
                history.setDate(rs.getTimestamp("date").toLocalDateTime());
                history.setStatus(rs.getBoolean("status"));
                list.add(history);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateWifiStatus(long wifiId) {
        String sql = "UPDATE HISTORY SET status = ? WHERE id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setBoolean(1, false);
            pstmt.setLong(2, wifiId);
            int rowAffected = pstmt.executeUpdate();
            if (rowAffected > 0) System.out.println("HISTORY 상태 수정(삭제 버튼) 완료." + rowAffected);
            return rowAffected;
        } catch (SQLException e) {
            System.out.println("수정 실패 : " + e.getMessage());
            return -1;
        }
    }
}

