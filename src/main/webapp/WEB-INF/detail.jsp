<%@ page import="com.example.zerobasemission1.mission.servlet.entity.WifiInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

        h1 {
            text-align: center;
            color: #333;
        }

        .menu {
            text-align: center;
            margin-bottom: 20px;
        }

        .menu a {
            color: #0b93dc;
            text-decoration: none;
            margin: 0 10px;
            font-size: 16px;
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
            color: #18e741;
            font-style: italic;
        }

        .info {
            background-color: #f2f2f2;
        }

    </style>
</head>
<body>
<h1>와이파이 정보 구하기</h1>
<div class="menu">
    <a href="../index.jsp">홈</a> |
    <a href="history">위치 히스토리 목록</a> |
    <a href="load-wifi">Open API 와이파이 정보 가져오기</a> |
    <a href="my-favorite-list">즐겨 찾기 보기</a> |
    <a href="bookmark-group">즐겨 찾기 그룹 관리</a>
</div>
<hr>
<%
    WifiInfo wifi = (WifiInfo) request.getAttribute("findWifi");  // 객체 받아오기
%>
<form action="favorite-add?wifiId=<%=wifi.getId()%>" method="post" onsubmit="return favorite()">
    <select name="group" id="group">
        <option selected disabled value=" ">북마크 그룹 이름 선택</option>
        <c:forEach var="bookmark" items="${bookMarkInfo}">
            <option value="${bookmark.id}">${bookmark.name}</option>
        </c:forEach>
    </select>
    <button>즐겨찾기 추가하기</button>
</form>


<table>
    <tr>
        <td class="info">거리(Km)</td>
        <td><%= wifi.getDistance() %></td> <!-- 거리 속성 출력 -->
    </tr>
    <tr>
        <td class="info">관리번호</td>
        <td><%= wifi.getId() %></td> <!-- 관리번호 속성 출력 -->
    </tr>
    <tr>
        <td class="info">자치구</td>
        <td><%= wifi.getWrdofc() %></td> <!-- 자치구 속성 출력 -->
    </tr>
    <tr>
        <td class="info">와이파이명</td>
        <td><%= wifi.getNm() %></td> <!-- 와이파이명 출력 -->
    </tr>
    <tr>
        <td class="info" >도로명주소</td>
        <td><%= wifi.getAddress1() %></td> <!-- 도로명주소 출력 -->
    </tr>
    <tr>
        <td class="info">상세주소</td>
        <td><%= wifi.getAddress2() %></td> <!-- 상세주소 출력 -->
    </tr>
    <tr>
        <td class="info">설치위치(층)</td>
        <td><%= wifi.getFloor() %></td> <!-- 설치위치(층) 출력 -->
    </tr>
    <tr>
        <td class="info">설치유형</td>
        <td><%= wifi.getTy() %></td> <!-- 설치유형 출력 -->
    </tr>
    <tr>
        <td class="info">설치기관</td>
        <td><%= wifi.getMby() %></td> <!-- 설치기관 출력 -->
    </tr>
    <tr>
        <td class="info">서비스구분</td>
        <td><%= wifi.getSe() %></td> <!-- 서비스구분 출력 -->
    </tr>
    <tr>
        <td class="info"> 망종류</td>
        <td><%= wifi.getCmcwr() %></td> <!-- 망종류 출력 -->
    </tr>
    <tr>
        <td class="info">설치년도</td>
        <td><%= wifi.getYear() %></td> <!-- 설치년도 출력 -->
    </tr>
    <tr>
        <td class="info" >실내외구분</td>
        <td><%= wifi.getDoor() %></td> <!-- 실내외구분 출력 -->
    </tr>
    <tr>
        <td class="info">WiFi 접속환경</td>
        <td><%= wifi.getRemars3() %></td> <!-- WiFi 접속환경 출력 -->
    </tr>
    <tr>
        <td class="info" >X좌표</td>
        <td><%= wifi.getLat() %></td> <!-- X좌표 출력 -->
    </tr>
    <tr>
        <td class="info"> Y좌표</td>
        <td><%= wifi.getLnt() %></td> <!-- Y좌표 출력 -->
    </tr>
    <tr>
        <td class="info">작업일자</td>
        <td><%= wifi.getDttm() %></td> <!-- 작업일자 출력 -->
    </tr>
</table>
</body>
<script>
    function favorite() {
        var group = document.getElementById('group').value;
        if (group == ' ') {
            alert("북마크 그룹을 선택해주세요.");
            return false;
        }
        return confirm("북마크 그룹을 추가하시겠습니까?");
    }

</script>
</html>
