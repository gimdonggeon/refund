# 환전 요청 시스템

## 설명

이 프로젝트는 사용자가 환전 요청을 수행하고 관리하는 시스템으로, 사용자는 환전 전 금액을 제공하고, 시스템은 이를 지정된 환율로 환전 후 금액을 계산하여 반환하는 프로그램을 개발하는 것입니다.
환전 요청은 상태를 normal과 cancelled로 관리할 수 있으며, 사용자가 삭제되면 관련된 모든 환전 요청도 삭제될 수 있도록 구현하였습니다.

## 기능

- 고객(User)과 통화(Currency) 간 연관 관계 관리
- 환전 요청 CRUD (생성, 조회, 수정, 삭제)
- 고객 삭제 시 해당 고객의 환전 요청도 삭제
- 환전 후 금액 계산 (환율 기준)

## API 명세

### 1. 환전 요청 생성
- **URL**: `/exchanges`
- **Method**: POST
- **Request Body**:
    ```json
    {
        "userId": 1,
        "toCurrencyId": 1,
        "amountInKrw": 10000
    }
    ```
- **Response**:
    ```json
    {
        "id": 1,
        "userId": 1,
        "toCurrencyId": 1,
        "amountInKrw": 10000,
        "amountAfterExchange": 6.99,
        "status": "normal"
    }
    ```

### 2. 고객의 환전 요청 조회
- **URL**: `/exchanges/user/{userId}`
- **Method**: GET
- **Response**:
    ```json
    [
        {
            "id": 1,
            "userId": 1,
            "toCurrencyId": 1,
            "amountInKrw": 10000,
            "amountAfterExchange": 6.99,
            "status": "normal"
        }
    ]
    ```

### 3. 환전 요청 상태 취소
- **URL**: `/exchanges/{id}/cancel`
- **Method**: PUT
- **Response**:
    ```json
    {
        "message": "환전 요청이 취소되었습니다."
    }
    ```

### 4. 고객 삭제 시 환전 요청 삭제
- **URL**: `/exchanges/user/{userId}`
- **Method**: DELETE
- **Response**:
    ```json
    {
        "message": "고객과 관련된 환전 요청이 삭제되었습니다."
    }
    ```

---
## ERD 

URL : https://dbdiagram.io/d/6723904ab1b39dd85815b6d8

---
