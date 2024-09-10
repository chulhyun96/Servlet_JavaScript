# ZEROBASE - MASTER MISSON
스프링의 기술을 학습하기 전에 Servlet과 JSP, DB와 연동하여 오픈 API 데이터를 받아서(서울시 공공와이파이가 설치된 장소), 웹 페이지를 만들어보고 DB의 연관관계에 대한 학습을 진행하기 위한 저장소

## 기능목록
### 내 위치 가져오기
- 사용자의 위치를 버튼 클릭으로 가져와서 input 태그에 그대로 넣어둔다.(Javascript 이용).
- 위치에 대한 정보는 위도와 경도로 구분하여 가져온다.
- 내 위치를 가져와서 가까운 곳으로 검색을 시도 한다.(반경 5km로 하였음.)
___
### 가져온 위치로 검색하기
- 검색을 해서 최대 20개를 한 페이지에 보여준다.(페이징 처리는 따로 하지 않는다.)
- 예외 처리
    - 오픈 API 데이터가 서울시 기준이기 때문에, 가져온 위도와 경도가 서울시가 아닌 경우 alert()창으로 메시지를 띄운 후 재입력 받도록 하였다.
___

### 검색 히스토리 목록
- 사용자가 여태 검색했던 정보가 검색 히스토리 목록에 남도록 하였다.
- 해당 검색 히스토리 정보는 삭제버튼이 있어 삭제를 할 수 있다.
___
### 북마크 그룹 추가,수정,삭제
- 사용자는 자신만의 북마크 그룹을 만듦으로써 자신이 원하는 정보를 따로 저장할 수 있도록 하였다.(즐겨찾기)

___
### 즐겨찾기 추가,삭제
- 사용자는 자신의 북마크를 가지고 공공와이파이 상세페에지로 들어가서 북마크를 클릭하고 해당 북마크에 즐겨찾기를 추가 할 수 있도록 하였다.
- 본인의 즐겨찾기 목록에 들어가서 삭제를 할 수 있도록 하였다.

---

### 과제 시연 영상
[제로베이스-마스터미션.zip](../../../Desktop/%EC%A0%9C%EB%A1%9C%EB%B2%A0%EC%9D%B4%EC%8A%A4-%EB%A7%88%EC%8A%A4%ED%84%B0%EB%AF%B8%EC%85%98.zip)



