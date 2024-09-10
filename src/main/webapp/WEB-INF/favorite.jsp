<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>북마크 목록</title>
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
            color: #6c3c3c;
            font-style: italic;
            text-align: center;
        }

        .button-div {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="menu">
    <h1>북 마크 목록</h1>
    <a href="../index.jsp">홈</a> |
    <a href="history">위치 히스토리 목록</a> |
    <a href="load-wifi">Open API 와이파이 정보 가져오기</a> |
    <a href="my-favorite-list">즐겨 찾기 보기</a> |
    <a href="bookmark-group">즐겨 찾기 그룹 관리</a>
</div>

<table>
    <thead>
    <tr>
        <th>id</th>
        <th>북마크 이름</th>
        <th>와이파이명</th>
        <th>등록일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="favorite" items="${favoriteList}">
        <tr>
            <td>${favorite.id}</td>
            <td>${favorite.bookmarkName}</td>
            <td>${favorite.wifiName}</td>
            <td>${favorite.createDate}</td>
            <td>
                <c:if test="${favorite.status}">
                    <div class="button-div">
                        <form action="delete-favorite" method="post">
                            <input type="hidden" name="favoriteId" value="${favorite.id}">
                            <button id="deleteButton">삭제</button>
                        </form>
                    </div>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</body>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        let deleteButton = document.getElementById('deleteButton');
        deleteButton.addEventListener('click', function (event) {
            if (!confirm("정말로 삭제하시겠습니까?")) {
                event.preventDefault();
            }
        });
    });
</script>
</html>
