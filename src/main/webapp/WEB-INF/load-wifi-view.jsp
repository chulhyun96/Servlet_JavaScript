<%--
  Created by IntelliJ IDEA.
  User: cheolhyeon
  Date: 2024. 9. 2.
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        div {
            padding-top: 40px;
            text-align: center;
        }
    </style>
</head>
<body>
<div>
    <h1><%=request.getAttribute("result")%></h1>
    <h3><a href="../index.jsp">홈으로 가기</a></h3>
</div>
</body>
</html>
