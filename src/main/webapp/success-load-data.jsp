<%--
  Created by IntelliJ IDEA.
  User: cheolhyeon
  Date: 2024. 9. 6.
  Time: 00:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="static/styles.css">
</head>
<body>
<div class="container">
    <h1>와이파이 정보 구하기</h1>
    <div class="menu">
        <a href="#">위치 히스토리 목록</a> |
        <a href="load-wifi">Open API 와이파이 정보 가져오기</a> |
        <a href="#">즐겨 찾기 보기</a> |
        <a href="#">즐겨 찾기 그룹 관리</a>
    </div>
    <div class="location-inputs">
        <form action="search" method="get">
            <%
                String lat = request.getParameter("lat");
                String lnt = request.getParameter("lnt");
            %>
            LAT:<input type="text" id="lat" name="lat" value="<%=lat%>">
            LNT:<input type="text" id="lnt" name="lnt" value="<%=lnt%>">
            <button id="locationButton">내 위치 기반으로 검색</button>
            <button>근처 WIFI 정보 보기</button>
        </form>
    </div>
    <table>
        <thead>
        <tr>
            <th>거리(Km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        </thead>
        <c:forEach var="wifi" items="${searchList}">

            <tr>
                <td>${wifi.distance}</td>
                <td>${wifi.id}</td>
                <td>${wifi.wrdofc}</td>
                <td>${wifi.nm}</td>
                <td>${wifi.address1}</td>
                <td>${wifi.address2}</td>
                <td>${wifi.floor}</td>
                <td>${wifi.ty}</td>
                <td>${wifi.mby}</td>
                <td>${wifi.se}</td>
                <td>${wifi.cmcwr}</td>
                <td>${wifi.year}</td>
                <td>${wifi.door}</td>
                <td>${wifi.lat}</td>
                <td>${wifi.lnt}</td>
                <td>${wifi.dttm}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>