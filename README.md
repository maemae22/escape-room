# <img src="https://github.com/maemae22/escape-room/assets/101652279/fb6c589d-242e-4373-8480-f1f40b0a3a85" style="width: 5%;">&nbsp; 열려라 참깨 : 방탈출카페 소개 및 리뷰 서비스 
<br>

방탈출카페 소개 및 리뷰 서비스입니다.<br>
이 프로젝트는 [기존 팀 프로젝트였던 '열려라 참깨' 프로젝트](https://github.com/maemae22/spring-legacy-project-escape-room)에서  
제가 맡았던 부분 위주로 클린 코드 작성에 집중하여 리팩토링해본 개인 프로젝트입니다.<br>
<br>
개발 기간 : 2023.05 ~ 2023.06<br>
개발 인원 : 1명 (개인 프로젝트)<br>
Code (Github) : https://github.com/maemae22/escape-room<br>
블로그 : https://maemae22.tistory.com/category/escape-room<br>
API 명세서 : https://documenter.getpostman.com/view/24197132/2s93eeQ9MA<br>

## 개발 환경

언어 : Java 11<br>
프레임워크 : Spring Boot 2.7.11, Spring Data JPA, QueryDSL<br>
DB : MySQL 8.0.32 (Amazon RDS)<br>
IDE : IntelliJ<br>
Tools : Git, GitHub, GitKraken, DBeaver, Postman<br>

## Project Architecture
<img src="https://github.com/maemae22/escape-room/assets/101652279/0332070e-14cd-4911-bb81-af764176be37" style="width: 70%;">

## 구현 기능
### 1. 카페 검색

\- @RequestParam의 required 속성을 false로 설정하여, 지역(loc) 리스트와 검색어(keyword) 중 입력받은 것으로만 동적으로 쿼리를 생성하여 검색함<br>
\- 파라미터로 받은 keyword가 빈 문자열(혹은 null)이 아닐 경우, name like '%keyword%' 으로 where 검색 조건 추가됨<br>
\- 파라미터로 받은 loc(List)가 null이거나 비어있지 않을 경우, location in ('loc1', 'loc2')으로 where 검색 조건 추가됨<br>
\- 예시 : https://www.openthedoor.site:8080/cafe/search?loc=홍대&loc=건대&keyword=비밀<br>
\- 상세 설명 : https://maemae22.tistory.com/107<br>

#### (1) 카페 검색 - 지역 검색
<img src="https://github.com/Maxiimum/algorithm_study/assets/80867166/ade5bed6-192e-415d-8b8a-477208a607a3" style="width: 50%;">

#### (2) 카페 검색 - 키워드 검색
<img src="https://blog.kakaocdn.net/dn/b7iEKK/btspsFXA7UM/AKT6NA2x7ADNqKZSak27B1/img.gif" style="width: 60%;">

### 2. 테마 검색
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FvYZGJ%2FbtspmhiVLgl%2FwCTsBrR9XOZCjSlgjhdWF1%2Fimg.png" style="width: 60%;">
- 검색 조건 : 지역(location), 장르(genre), 난이도(difficult), 활동성(activity), 추천인원(peopleNum), 키워드(keyword)로 검색 가능. <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 지역, 장르, 난이도, 활동성의 경우 복수 선택 검색 가능  <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  쿼리스트링으로 입력된 경우에만 검색 조건으로 추가하여 동적으로 쿼리를 생성하여 검색함<br>
- 정렬(sorting) : 리뷰 높은 순, 이름 순(name), 지역 순(location) 정렬 중 선택 가능, 선택 안할 시 기본값은 리뷰 높은 순 정렬<br>
- 예시 : https://www.openthedoor.site:8080/theme/search?activity=보통&difficult=3&genre=공포&genre=스릴러&sorting=location<br>
- Code : https://github.com/maemae22/escape-room/pull/50


### 3. 테마 상세페이지
#### (1) 테마 상세 정보
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbutAe3%2FbtspsFQPGpl%2FRfbpeGMAhke0IEjP1wtLl1%2Fimg.png" style="width: 60%;">

#### (2) 이 업체의 다른 테마

\- 테마 상세페이지에서 우측 배너로 현재 테마와 동일한 업체의 다른 테마 중 2개를 랜덤으로 보여줌<br>
\- 만약 이 업체의 다른 테마가 2개 미만일 경우, 해당 개수만큼만 반환함<br>
\- 리뷰 평균 점수의 소수점 반올림 처리 : Expressions.template() 메서드를 사용하여 ROUND 함수를 적용함<br>
\- 예시 : https://www.openthedoor.site:8080/theme/others?id=53<br>

<img src="https://blog.kakaocdn.net/dn/OSfTg/btsplMjcP1M/kcSLophelM3sifzDbjAzaK/img.gif" style="width: 40%;">

### 3-1. 테마 상세페이지 내 리뷰 CRUD
#### (1) 리뷰 작성
<img src="https://blog.kakaocdn.net/dn/chDtdQ/btspjWmks3A/lkZE0ZZqVqodMsw8w72lr1/img.gif" style="width: 60%;">

#### (2) 리뷰 수정

\- 변경 감지를 통한 리뷰 수정 : updateReview 메서드를 통해 JPA의 변경 감지(dirty-checking) 기능을 사용하여 리뷰 수정 기능을 구현함

<img src="https://blog.kakaocdn.net/dn/dyUPmc/btsppHBnEGc/4NkIWrvuBNw3BN1ogwVCxk/img.gif" style="width: 60%;">

#### (3) 리뷰 삭제
<img src="https://blog.kakaocdn.net/dn/clXyXQ/btspkLL5gba/q5SfDUsKcPcH2GkN4vED80/img.gif" style="width: 40%;">

#### (4) 리뷰 조회
<img src="https://github.com/maemae22/escape-room/assets/101652279/528c7c80-e32d-438a-8634-cf34529bed08" style="width: 50%;">


