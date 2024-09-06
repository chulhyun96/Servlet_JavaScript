document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('locationButton').addEventListener('click', function (event) {
        event.preventDefault();
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(successCallback);
        } else {
            alert("");
        }
    });

    function successCallback(position) {
        const latitude = position.coords.latitude;
        const longitude = position.coords.longitude;

        // 위치 정보를 HTML 입력 필드에 설정
        document.getElementById('lat').value = latitude;
        document.getElementById('lnt').value = longitude;
    }
});
