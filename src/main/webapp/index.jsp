<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="static/styles.css">
    <%@ page contentType="text/html; charset=UTF-8" %>
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
            LAT:<input type="text" id="lat" name="lat">
            LNT:<input type="text" id="lnt" name="lnt">
            <button id="locationButton" type="submit">내 위치 기반으로 검색</button>
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
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI전속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
        </tr>
        </tbody>
    </table>
</div>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('locationButton').addEventListener('click', function (event) {
            event.preventDefault(); // 폼의 기본 제출 동작을 방지
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(successCallback, errorCallback);
            } else {
                alert("Geolocation is not supported by this browser.");
            }
        });

        function successCallback(position) {
            const latitude = position.coords.latitude;
            const longitude = position.coords.longitude;

            // 위치 정보를 HTML 입력 필드에 설정
            document.getElementById('lat').value = latitude;
            document.getElementById('lnt').value = longitude;

            // 위치 정보가 설정된 후 폼을 제출
            document.getElementById('searchForm').submit();
        }

        function errorCallback(error) {
            switch(error.code) {
                case error.PERMISSION_DENIED:
                    alert("사용자가 위치 정보 접근을 거부했습니다.");
                    break;
                case error.POSITION_UNAVAILABLE:
                    alert("위치 정보를 사용할 수 없습니다.");
                    break;
                case error.TIMEOUT:
                    alert("위치 정보를 가져오는 시간이 초과되었습니다.");
                    break;
                case error.UNKNOWN_ERROR:
                    alert("알 수 없는 오류가 발생했습니다.");
                    break;
            }
        }
    });
</script>
</body>
</html>