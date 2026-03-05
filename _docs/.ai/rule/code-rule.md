# skill: java-code-convention

## 목적
이 프로젝트의 Java 소스 파일을 생성하거나 수정할 때 반드시 이 컨벤션을 따른다.

---

## 적용 시점
아래 요청 시 이 스킬을 활성화한다:
- 엔티티 / 리포지토리 / 서비스 / 컨트롤러 / 요청 DTO / 응답 DTO 생성
- 새 기능, 엔드포인트, 도메인 추가
- 기존 코드의 컨벤션 위반 검토 및 수정

---

## 1. 패키지 구조 (Package Structure)
도메인 기반의 **플랫 패키지(Flat Package)** 구조를 사용한다. 레이어(Layer) 기반 구조는 사용하지 않는다.

```
com.example.demo/
├── _core/utils/        # 도메인 무관 공통 유틸 (예: Resp.java)
├── {domain}/           # 도메인별 폴더 (Entity, Controller, Service, Repository, DTO 포함)
```

---

## 2. 엔티티 규칙 (Entity)
- **어노테이션 순서**: `@NoArgsConstructor` → `@Data` → `@Entity` → `@Table`
- **테이블 명명**: `{도메인}_tb` (Snake Case + `_tb` 접미사)
- **PK 정의**: 타입은 `Integer`, 전략은 `GenerationType.IDENTITY` 고정.
- **연관 관계**: 모든 연관 관계는 반드시 `FetchType.LAZY`로 설정 (EAGER 절대 금지).
- **생성일**: `@CreationTimestamp` + `LocalDateTime createdAt` 사용.
- **Builder**: `@Builder`는 클래스 레벨 선언을 금지하며, **전체 필드 생성자**에만 선언한다.
  - 컬렉션 필드(List 등)는 Builder 생성자에 포함하지 않는다.

---

## 3. 리포지토리 규칙 (Repository)
- `JpaRepository<Entity, Integer>`를 상속받는다.

---

## 4. 서비스 규칙 (Service)
- **어노테이션 순서**: `@Transactional(readOnly = true)` → `@RequiredArgsConstructor` → `@Service`
- **트랜잭션**: 클래스 레벨에 `readOnly = true`를 선언하고, 쓰기 메서드에만 `@Transactional`을 개별 선언한다.
- **데이터 흐름**: **DTO는 반드시 Service에서 생성**하여 반환한다. 날(raw) Entity를 Controller로 전달하는 것을 절대 금지한다.

---

## 5. 컨트롤러 규칙 (Controller)
- **어노테이션 순서**: `@RequiredArgsConstructor` → `@Controller` (또는 `@RestController`)
- **구분**:
  - **SSR (Mustache)**: `@Controller` 사용, 반환 타입은 `String` (템플릿 경로).
  - **REST API**: `@RestController` 사용, 주소 접두어 `/api` 필수, `Resp<T>` 래퍼 반환.
- **의존성**: Service와 `HttpSession`을 생성자로 주입받아 사용한다.

---

## 6. DTO 규칙 (Request / Response)
- **파일 구성**: 도메인당 `{Domain}Request.java`, `{Domain}Response.java` 파일 하나씩 관리.
- **구조**:
  - 외부 클래스: 어노테이션 없음.
  - 내부 static 클래스: `@Data` 선언, 실제 데이터 필드 포함.
- **이름 규칙**:
  - Request 내부 클래스: **기능명** (예: `Save`, `Update`, `Login`)
  - Response 내부 클래스: **용도명** (예: `Detail`, `Items`, `Summary`)
- **변환**: Entity → DTO 변환은 DTO의 생성자 또는 정적 팩토리 메서드에서 처리한다.

---

## 7. 네이밍 및 공통 제약 (Naming & Constraints)

| 대상 | 컨벤션 | 예시 |
| :--- | :--- | :--- |
| 클래스 / 파일 | **PascalCase** | `BoardService.java` |
| 메서드 / 변수 | **camelCase** | `findAll()`, `userId` |
| 테이블명 | **snake_case + _tb** | `user_tb` |
| 패키지명 | **lowercase** | `board`, `_core` |

- **OSIV**: `spring.jpa.open-in-view=false` (항상 비활성화)
- **Batch Size**: `default_batch_fetch_size=10` 고정 (N+1 최적화)
- **인증**: 별도 요청이 없으면 `HttpSession` 기반 세션 방식을 사용한다.
- **공통 응답**: REST API는 반드시 `_core/utils/Resp.java`를 사용하여 `Resp.ok(body)` 또는 `Resp.fail(status, msg)` 형식을 반환한다.
