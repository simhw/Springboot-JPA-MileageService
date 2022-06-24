# Springboot-JPA-MileageService
## 환경
* MySQL
* Java 11 

## MySQL 데이터베이스 생성 및 초기화 
* /src/main/resources/sql/ddl.sql 파일 실행 시 데이터베이스 및 테이블 생성 
* /src/main/resources/application.yml ddl-auto: update 변경 시 데이터베이스 및 테이블 자동 생성 

```
spring:
  jpa:
    hibernate:
      ddl-auto: update
```

* /src/main/resources/sql/data.sql 파일 실행 시 테스트 데이터 삽입 

## 실행 
### 리뷰 API, 적립 API
* POST /http://localhost:8080/events 
```
{
    "type": "REVIEW",
    "action": "ADD",
    "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
    "content": "좋아요!!",
    "attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332"],
    "userId": "3ede0ef2-92b7--a5f3-0c575361f745",
    "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
}
```
```
{
    "type": "REVIEW",
    "action": "MOD",
    "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
    "content": "<수정>좋아요!!",
    "attachedPhotoIds": [],
    "userId": "3ede0ef2-92b7--a5f3-0c575361f745",
    "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
}
```
```
{
    "type": "REVIEW",
    "action": "DELETE",
    "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
    "content": "<수정>좋아요!!",
    "attachedPhotoIds": [],
    "userId": "3ede0ef2-92b7--a5f3-0c575361f745",
    "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
}
```
## 포인트 조회 API
* 전체 포인트 적립 내역 조회 
  * GET /http://localhost:8080/points
* 회원 포인트 적립 내역 조회 
  * GET /http://localhost:8080/points/{userId}
 
