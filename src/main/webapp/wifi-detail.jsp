<%@ page import="com.example.zerobasemission1.mission.servlet.WifiInfo" %><%--
  Created by IntelliJ IDEA.
  User: cheolhyeon
  Date: 2024. 9. 6.
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>와이파이 정보 구하기</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            font-size: 13px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
            font-size: 13px;
        }

        th {
            background-color: #f2f2f2;
        }

        tbody tr td[colspan] {
            color: #888;
            font-style: italic;
        }
    </style>
</head>
<body>
<h1>와이파이 정보 구하기</h1>
<div class="menu">
    <a href="index.jsp">홈</a> |
    <a href="history">위치 히스토리 목록</a> |
    <a href="load-wifi">Open API 와이파이 정보 가져오기</a> |
    <a href="#">즐겨 찾기 보기</a> |
    <a href="#">즐겨 찾기 그룹 관리</a>
</div>
<hr>
<select name="group" id="group">
    <option value="">북마크 그룹 이름 선택</option>
    <option value="group1">그룹 1</option>
    <option value="group2">그룹 2</option>
    <option value="group3">그룹 3</option>
</select>
<%
    WifiInfo wifi = (WifiInfo) request.getAttribute("findWifi");  // 객체 받아오기
%>

<table>
    <tr>
        <td>거리(Km)</td>
        <td><%= wifi.getDistance() %></td> <!-- 거리 속성 출력 -->
    </tr>
    <tr>
        <td>관리번호</td>
        <td><%= wifi.getId() %></td> <!-- 관리번호 속성 출력 -->
    </tr>
    <tr>
        <td>자치구</td>
        <td><%= wifi.getWrdofc() %></td> <!-- 자치구 속성 출력 -->
    </tr>
    <tr>
        <td>와이파이명</td>
        <td><%= wifi.getNm() %></td> <!-- 와이파이명 출력 -->
    </tr>
    <tr>
        <td>도로명주소</td>
        <td><%= wifi.getAddress1() %></td> <!-- 도로명주소 출력 -->
    </tr>
    <tr>
        <td>상세주소</td>
        <td><%= wifi.getAddress2() %></td> <!-- 상세주소 출력 -->
    </tr>
    <tr>
        <td>설치위치(층)</td>
        <td><%= wifi.getFloor() %></td> <!-- 설치위치(층) 출력 -->
    </tr>
    <tr>
        <td>설치유형</td>
        <td><%= wifi.getTy() %></td> <!-- 설치유형 출력 -->
    </tr>
    <tr>
        <td>설치기관</td>
        <td><%= wifi.getMby() %></td> <!-- 설치기관 출력 -->
    </tr>
    <tr>
        <td>서비스구분</td>
        <td><%= wifi.getSe() %></td> <!-- 서비스구분 출력 -->
    </tr>
    <tr>
        <td>망종류</td>
        <td><%= wifi.getCmcwr() %></td> <!-- 망종류 출력 -->
    </tr>
    <tr>
        <td>설치년도</td>
        <td><%= wifi.getYear() %></td> <!-- 설치년도 출력 -->
    </tr>
    <tr>
        <td>실내외구분</td>
        <td><%= wifi.getDoor() %></td> <!-- 실내외구분 출력 -->
    </tr>
    <tr>
        <td>WiFi 접속환경</td>
        <td><%= wifi.getRemars3() %></td> <!-- WiFi 접속환경 출력 -->
    </tr>
    <tr>
        <td>X좌표</td>
        <td><%= wifi.getLat() %></td> <!-- X좌표 출력 -->
    </tr>
    <tr>
        <td>Y좌표</td>
        <td><%= wifi.getLnt() %></td> <!-- Y좌표 출력 -->
    </tr>
    <tr>
        <td>작업일자</td>
        <td><%= wifi.getDttm() %></td> <!-- 작업일자 출력 -->
    </tr>
</table>
</body>
<script src="static/myhistory.js"></script>
</html>
