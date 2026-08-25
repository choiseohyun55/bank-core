# 🏦 bank-core (금융 계좌 이체 시스템)

> **대용량 트랜잭션 처리와 동시성 제어 및 데이터 정합성 보장에 집중한 금융 백엔드 REST API 서비스입니다.**

---

## 🛠️ 기술 스택 (Tech Stack)

* **Language:** Java 17
* **Framework:** Spring Boot 3.x
* **Database:** MySQL
* **ORM:** Spring Data JPA
* **Tools:** IntelliJ IDEA, Git/GitHub, Postman

---

## 📌 핵심 기능 (Core Features)

* **회원 관리 (`User`)**
  * 회원가입 및 이메일 중복 검증
  * Spring Security & JWT 기반 인증/인가 (로그인, 토큰 재발급)
* **계좌 관리 (`Account`)**
  * 계좌 개설 및 보유 계좌 목록 조회
  * 입금 및 출금 처리 (잔액 검증 로직 포함)
* **거래 및 이체 (`Transaction`)**
  * 계좌 간 송금 기능 (트랜잭션 롤백 보장)
  * JPA 비관적/낙관적 락을 활용한 **동시성 제어(Concurrency Control)**
  * 계좌별 거래 내역 조회 (최신순 정렬)

---

## 📐 ERD (데이터베이스 구조)

```text
[User] 1 ─── N [Account] 1 ─── N [Transaction]
                  │
                  └─── N [RefreshToken]
