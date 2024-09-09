<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>위치 히스토리 목록</title>
    <link rel="stylesheet" href="static/styles.css">
</head>
<body>
<h1>위치 히스토리 목록</h1>
<div class="menu">
    <a href="index.jsp">홈</a> |
    <a href="history">위치 히스토리 목록</a> |
    <a href="load-wifi">Open API 와이파이 정보 가져오기</a> |
    <a href="bookmark-group">즐겨 찾기 보기</a> |
    <a href="bookmark-group">즐겨 찾기 그룹 관리</a>
</div>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var="history" items="${historyList}">
        <tr>
            <td>${history.id}</td>
            <td>${history.lat}</td>
            <td>${history.lnt}</td>
            <td>${history.date}</td>
            <td>
            <c:if test="${history.status == true}">
                <form action="delete-history" method="post">
                    <input type="hidden" name="id" value="${history.id}">
                    <button onclick="return deleteButton()">삭제</button>
                </form>
            </c:if>
            </td>
        </tr>
    </tbody>
    </c:forEach>
</table>
</body>
<script>
    function deleteButton() {
        return confirm("정말로 삭제하시겠습니까?")
    }
</script>
</html>
