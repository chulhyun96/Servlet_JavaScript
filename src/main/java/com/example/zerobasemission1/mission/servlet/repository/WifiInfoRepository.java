package com.example.zerobasemission1.mission.servlet.repository;

import com.example.zerobasemission1.mission.env_config.Config;
import com.example.zerobasemission1.mission.servlet.entity.Bookmark;
import com.example.zerobasemission1.mission.servlet.entity.Favorite;
import com.example.zerobasemission1.mission.servlet.entity.History;
import com.example.zerobasemission1.mission.servlet.entity.WifiInfo;
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
    private static WifiInfoRepository instance;
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private WifiInfoRepository() {
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

    public static WifiInfoRepository getInstance() {
        if (instance == null) {
            return new WifiInfoRepository();
        }
        return instance;
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

        String BASE_URL = "http://openapi.seoul.go.kr:8088/";
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

    public boolean saveMyHistory( Long memberId, double lat, double lnt, boolean status) {
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
            return true;
        } catch (SQLException e) {
            System.out.println("데이터 삽입 중 오류 발생: " + e.getMessage());
            return false;
        }
    }

    public List<History> findMyHistoryList(Long memberId) {
        String sql = "SELECT id, lat, lnt, date, status FROM HISTORY  WHERE member_id = ? and status = ? order by HISTORY.id DESC";
        List<History> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
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
        String sql = "UPDATE HISTORY SET status = false WHERE id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, wifiId);
            int rowAffected = pstmt.executeUpdate();
            if (rowAffected > 0) System.out.println("HISTORY 상태 수정(삭제 버튼) 완료." + rowAffected);
            return rowAffected;
        } catch (SQLException e) {
            System.out.println("수정 실패 : " + e.getMessage());
            return -1;
        }
    }

    public List<Bookmark> findBookMarkList(Long memberId) {
        String sql = "Select * from BOOK_MARK where member_id = ? and status = true order by orders";
        List<Bookmark> list = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Bookmark bookMark = new Bookmark();
                bookMark.setId(rs.getLong("id"));
                bookMark.setName(rs.getString("name"));
                bookMark.setOrders(rs.getLong("orders"));
                bookMark.setCreatedDate(rs.getTimestamp("create_date").toLocalDateTime());
                Timestamp modifyDate = rs.getTimestamp("modify_date");
                if (modifyDate != null) {
                    bookMark.setModifyDate(modifyDate.toLocalDateTime());
                } else {
                    bookMark.setModifyDate(null);
                }
                bookMark.setStatus(rs.getBoolean("status"));
                list.add(bookMark);
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveBookmark(Long memberId, String bookMarkName, String orders) {
        String sql = "INSERT INTO BOOK_MARK (member_id, name, orders, create_date, status) " +
                "VALUES (?, ?, ?, CURRENT_TIMESTAMP,true)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            pstmt.setString(2, bookMarkName);
            pstmt.setString(3, orders);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateBookmarkStatus(Long bookmarkId) {
        String sql = "update BOOK_MARK set status = false where id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, bookmarkId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateWifiInfo(Long bookmarkId, String bookmarkName, Long orders, LocalDateTime modifiedDate) {
        String sql = "UPDATE BOOK_MARK SET name = ?, orders = ?, modify_date = ? WHERE id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookmarkName);
            pstmt.setLong(2, orders);
            pstmt.setTimestamp(3, Timestamp.valueOf(modifiedDate));
            pstmt.setLong(4, bookmarkId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Bookmark> findBookMarkInfo(Long memberId) {
        String sql ="Select id,name from BOOK_MARK where member_id = ? and status = true";
        List<Bookmark> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Bookmark bookMark = new Bookmark();
                bookMark.setId(rs.getLong("id"));
                bookMark.setName(rs.getString("name"));
                list.add(bookMark);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveFavoriteList(long bookmarkId, Long memberId, String wifiId) {
        String sql = "INSERT INTO FAVORITE (bookmark_id, member_id, created_date, status, wifi_id) VALUES (?, ?, now(), true, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, bookmarkId);
            pstmt.setLong(2, memberId);
            pstmt.setString(3, wifiId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Favorite> findMyFavoriteList(Long memberId) {
        String sql = "SELECT " +
                "f.id, " +
                "bm.name AS b_name, " +
                "pwi.X_SWIFI_MAIN_NM AS wifi_name, " +
                "f.created_date, " +
                "f.status " +
                "FROM FAVORITE f " +
                "JOIN BOOK_MARK bm ON f.bookmark_id = bm.id " +
                "JOIN PublicWifiInfo pwi ON f.wifi_id = pwi.X_SWIFI_MGR_NO " +
                "WHERE f.member_id = ? and f.status = true";
        List<Favorite> list = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Favorite favorite = new Favorite();
                favorite.setId(rs.getLong("id"));
                favorite.setBookmarkName(rs.getString("b_name"));
                favorite.setWifiName(rs.getString("wifi_name"));
                favorite.setCreateDate(rs.getTimestamp("created_date").toLocalDateTime());
                favorite.setStatus(rs.getBoolean("status"));
                list.add(favorite);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateFavoriteStatus(Long favoriteId) {
        String sql = "update FAVORITE set status = false where id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, favoriteId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

