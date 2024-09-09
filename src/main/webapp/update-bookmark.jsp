<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
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
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
            text-align: center;
            font-size: 13px;
        }

        input[type="text"] {
            width: 100%;
            padding: 6px;
            box-sizing: border-box;
        }

        button {
            margin-top: 10px;
            padding: 10px 20px;
            background-color: #0b93dc;
            color: white;
            border: none;
            cursor: pointer;
        }

        .center {
            text-align: center;
        }

        button:hover {
            background-color: #45a049;
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

<form action="update-bookmark" method="post">
    <table>
        <tr>
            <th>북마크 이름</th>
            <td><label>
                <input type="text" name="bookmarkName" required>
            </label></td>
        </tr>
        <tr>
            <th>순서</th>
            <td><label>
                <input type="text" name="orders" required>
            </label></td>
        </tr>
        <!-- 추가된 ID 입력 필드 -->
        <input type="hidden" name="bookmarkId" value="${param.bookmarkId}">
    </table>
    <div class="center">
        <button type="button" onclick="back()">돌아가기</button>
        <button type="submit">수정</button>
    </div>
</form>

<script>
    function back() {
        window.history.back();
    }
</script>
</body>
</html>