<%@ page import="com.example.zerobasemission1.mission.servlet.Bookmark" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cheolhyeon
  Date: 2024. 9. 9.
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>북 마크 목록</title>
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
    <a href="index.jsp">홈</a> |
    <a href="history">위치 히스토리 목록</a> |
    <a href="load-wifi">Open API 와이파이 정보 가져오기</a> |
    <a href="">즐겨 찾기 보기</a> |
    <a href="bookmark-group">즐겨 찾기 그룹 관리</a>
</div>
<a href="bookmark-add.jsp">
    <button>북마크 그룹 이름 추가</button>
</a>
<table>
    <thead>
    <tr>
        <th>id</th>
        <th>북마크 이름</th>
        <th>순서</th>
        <th>등록일자</th>
        <th>수정일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <% List<Bookmark> bookmarkList = (List<Bookmark>) request.getAttribute("bookmarkList");%>
    <c:if test="${not empty bookmarkList}">
        <c:forEach var="bookmark" items="${bookmarkList}">
            <tr>
            <td>${bookmark.id}</td>
            <td>${bookmark.name}</td>
            <td>${bookmark.orders}</td>
            <td>${bookmark.createdDate}</td>
            <td>${bookmark.modifyDate}</td>
            <td>
                <c:if test="${bookmark.status}">
                    <div class="button-div">
                        <form action="delete-bookmark" method="post">
                            <input type="hidden" name="bookmarkId" value="${bookmark.id}">
                            <button onclick="return deleteButton()">삭제</button>
                        </form>
                        <button onclick="return updateButton(${bookmark.id})">수정</button>
                    </div>
                </c:if>
            </td>
        </c:forEach>
        </tr>
    </c:if>

    <c:if test="${empty bookmarkList}">
        <tr>
            <td colspan="17"><b>정보가 존재하지 않습니다.</b></td>
        </tr>
    </c:if>
    </tbody>
</table>
</body>
<script>

    function deleteButton() {
        return confirm("정말로 삭제하시겠습니까?");
    }


    function updateButton(id) {
        if (confirm("정말로 수정하시겠습니까?")) {
            location.href = "update-bookmark.jsp?bookmarkId=" + id;
        }
    }
</script>
</html>


