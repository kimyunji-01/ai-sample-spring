# Project Context: Blog Platform (Demo)

이 문서는 프로젝트의 전체 아키텍처, 기술 스택 및 개발 방향을 정의합니다. 모든 개발자는 이 문서를 바탕으로 일관된 코드를 작성해야 합니다.

---

## 1. 프로젝트 개요
- **목적**: Spring Boot 기반의 블로그 플랫폼 개발
- **주요 기능**: 사용자 관리(회원가입/로그인), 게시글 CRUD, 댓글 기능

---

## 2. 전체 아키텍처
이 프로젝트는 **도메인 기반 플랫 구조(Domain-driven Flat Structure)**를 따릅니다. 레이어(Service, Controller 등)를 폴더로 나누지 않고, 도메인별로 관련 파일을 한 곳에 모아 응집도를 높입니다.

### 레이어드 구성
- **Web Layer**: Mustache를 이용한 SSR(Server Side Rendering)과 REST API를 위한 RestController 제공
- **Service Layer**: 비즈니스 로직 처리 및 트랜잭션 관리, Entity와 DTO 간의 변환 수행
- **Repository Layer**: Spring Data JPA를 이용한 데이터 액세스
- **_core Layer**: 전역 예외 처리, 공통 응답(`Resp.java`), 공통 유틸리티 등 도메인 무관 코드

---

## 3. 기술 스택
- **Language**: Java 21
- **Framework**: Spring Boot 3.3.4
- **Persistence**: Spring Data JPA (Hibernate)
- **Database**: H2 (In-memory)
- **View Engine**: Mustache
- **Build Tool**: Gradle
- **Lombok**: 보일러플레이트 코드 제거
- **OSIV**: `false` (지연 로딩은 트랜잭션 내에서만 처리)

---

## 4. 핵심 개발 원칙 (Rules Summary)
- **Entity**: PK는 `Integer` 사용, 모든 연관관계는 `LAZY` 전략 고수
- **DTO**: 도메인별 `Request`, `Response` 파일로 관리하며 기능/범위별 내부 클래스 사용
- **RestController**: `/api` 접두사를 사용하며 반드시 `Resp<T>`로 응답
- **Session**: 인증 정보는 `HttpSession`을 통해 관리 (Security 미사용)
- **Validation**: 비즈니스 검증은 Service, 데이터 형식 검증은 DTO 레벨에서 수행

---

## 5. 데이터 초기화
- `src/main/resources/db/data.sql`을 통해 애플리케이션 시작 시 테스트 데이터를 로드합니다.
- `spring.jpa.defer-datasource-initialization=true` 설정으로 하이버네이트 생성 후 스크립트가 실행됩니다.
