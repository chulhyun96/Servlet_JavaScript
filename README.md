# ZEROBASE - MASTER MISSON
스프링의 기술을 학습하기 전에 Servlet과 JSP, DB와 연동하여 공공데이터 API 를 받아서(서울시 공공와이파이가 설치된 장소), 웹 페이지를 만들어보고 DB의 연관관계에 대한 학습을 진행하기 위한 저장소

## 기능목록
### - 내 위치 가져오기
- 사용자의 위치를 버튼 클릭으로 가져와서 input 태그에 그대로 넣어둔다.(Javascript 이용).
- 위치에 대한 정보는 위도와 경도로 구분하여 가져온다.
- 내 위치를 가져와서 가까운 곳으로 검색을 시도 한다.(반경 5km로 하였음.)
___


### - 가져온 위치로 검색하기
- 검색을 해서 최대 20개를 한 페이지에 보여준다.(페이징 처리는 따로 하지 않는다.)
- 예외 처리
    - 공공데이터 API가 서울시 기준이기 때문에, 가져온 위도와 경도가 서울시가 아닌 경우 alert()창으로 메시지를 띄운 후 재입력 받도록 하였다.
___

### - 검색 히스토리 목록
- 사용자가 여태 검색했던 정보가 검색 히스토리 목록에 남도록 하였다.
- 해당 검색 히스토리 정보는 삭제버튼이 있어 삭제를 할 수 있다.
___


### - 북마크 그룹 추가,수정,삭제
- 사용자는 자신만의 북마크 그룹을 만듦으로써 자신이 원하는 정보를 따로 저장할 수 있도록 하였다.(즐겨찾기)

___


### - 즐겨찾기 추가,삭제
- 사용자는 자신의 북마크를 가지고 공공와이파이 상세페이지로 들어가서 북마크를 클릭하고 해당 북마크에 즐겨찾기를 추가 할 수 있도록 하였다.
- 본인의 즐겨찾기 목록에 들어가서 삭제를 할 수 있도록 하였다.

---

### Gradle 외부 라이브러리 의존성
```groovy
implementation 'javax.servlet:javax.servlet-api:4.0.1' -> HTTP 요청과 응답을 처리하는 Servlet 구현
implementation 'com.squareup.okhttp3:okhttp:4.11.0' -> 외부 API와의 통신이나 서버 간의 HTTP 요청을 보내고 응답 받는 작업
implementation 'com.google.code.gson:gson:2.10.1' -> API 요청 또는 응답에서 JSON 형식의 데이터를 주고받고 JSON 객체를 다루기 위해
implementation 'io.github.cdimascio:dotenv-java:2.2.0' -> .env 파일에서 환경변수를 로드하고 민감한 파일 정보를 숨기기 위해
implementation 'org.mariadb.jdbc:mariadb-java-client:3.0.7' -> MariaDB와 자바연결
implementation group: 'javax.servlet', name: 'jstl', version: '1.2' -> JSP페이지에서 반복문과 조건문을 태그 형태로 쉽게 처리하기 위해
```


### - 과제 시연 영상
[시연 영상 보기]https://drive.google.com/file/d/1l0VUThwT_vBEW5W6FvV7Q5ALfLpsxlh1/view?usp=sharing
