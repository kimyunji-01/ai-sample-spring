# 프로젝트 코드 컨벤션 (Project Code Convention)

본 문서는 프로젝트의 일관된 코드 품질과 유지보수성을 위해 준수해야 할 규칙을 정의한다.

---

## 1. 아키텍처 및 패키지 구조
- **구조**: 도메인 기반의 **플랫 패키지(Flat Package)** 구조를 채택한다.
- **규칙**: 레이어(Layer)가 아닌 도메인(Domain)별로 폴더를 구성하며, 관련 파일을 한곳에 모은다.
  - `com.example.demo/`
    - `_core/utils/`: 도메인 무관 공통 유틸리티 (예: `Resp.java`)
    - `{domain}/`: 해당 도메인의 Entity, Controller, Service, Repository, DTO (Request/Response)

---

## 2. 레이어별 구현 규칙

### 2.1 Entity (엔티티)
- **어노테이션 순서**: `@NoArgsConstructor` → `@Data` → `@Entity` → `@Table`
- **테이블 명명**: `{도메인}_tb` (Snake Case + `_tb` 접미사)
- **PK 정의**: 타입은 항상 `Integer`이며, `IDENTITY` 전략을 사용한다.
- **연관 관계**: 모든 연관 관계는 반드시 `FetchType.LAZY`로 설정한다. (EAGER 금지)
- **생성일**: `@CreationTimestamp`와 `LocalDateTime createdAt`을 사용한다.
- **Builder**: `@Builder`는 클래스 레벨이 아닌 **전체 필드 생성자**에 선언한다.
  - 컬렉션 필드(List 등)는 Builder 생성자에 포함하지 않는다.

### 2.2 Repository (리포지토리)
- `JpaRepository<Entity, Integer>`를 상속받아 구현한다.

### 2.3 Service (서비스)
- **어노테이션 순서**: `@Transactional(readOnly = true)` → `@RequiredArgsConstructor` → `@Service`
- **트랜잭션**: 클래스 레벨에 `readOnly = true`를 기본 적용하고, 쓰기 작업(CUD) 메서드에만 `@Transactional`을 별도로 선언한다.
- **데이터 흐름**: **DTO는 Service에서 생성**하여 반환한다. (Entity를 Controller로 직접 전달하지 않는다.)

### 2.4 Controller (컨트롤러)
- **어노테이션 순서**: `@RequiredArgsConstructor` → `@Controller` (또는 `@RestController`)
- **구분**:
  - **SSR (Mustache)**: `@Controller`를 사용하며, 템플릿 경로(`String`)를 반환한다.
  - **REST API**: `@RestController`를 사용하며, 주소 앞에 `/api`를 붙이고 `Resp<T>` 래퍼를 반환한다.
- **의존성**: Service와 `HttpSession`을 주입받아 사용한다.

---

## 3. DTO (Data Transfer Object) 규칙

### 3.1 공통 규칙
- 도메인당 하나의 파일로 관리한다: `{Domain}Request.java`, `{Domain}Response.java`
- **외부 클래스**: 어노테이션을 선언하지 않는다.
- **내부 static 클래스**: 실제 데이터 필드를 가지며 `@Data`를 선언한다.
- **변환**: Entity에서 DTO로의 변환은 DTO의 생성자 또는 정적 팩토리 메서드를 이용한다.

### 3.2 Request (요청)
- 내부 클래스 명칭은 **기능명**으로 정의한다. (예: `Save`, `Update`, `Login`)

### 3.3 Response (응답)
- 내부 클래스 명칭은 **용도명**으로 정의한다. (예: `Detail`, `Items`, `Summary`)

---

## 4. 네이밍 및 기타 규칙

| 대상 | 컨벤션 | 예시 |
| :--- | :--- | :--- |
| 클래스 / 파일 | **PascalCase** | `BoardService.java` |
| 메서드 / 변수 | **camelCase** | `findAll()`, `userId` |
| 테이블명 | **snake_case + _tb** | `user_tb` |
| 패키지명 | **lowercase** | `board`, `_core` |

- **OSIV**: `spring.jpa.open-in-view=false` (항상 비활성화)
- **Batch Size**: `default_batch_fetch_size=10` (N+1 문제 최적화)
- **인증**: 별도 요청이 없으면 `HttpSession` 기반 세션 방식을 사용한다.
- **공통 응답**: `_core/utils/Resp.java`를 사용하여 `Resp.ok(body)` 또는 `Resp.fail(status, msg)` 형식을 준수한다.
