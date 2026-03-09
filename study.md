# 아이디 중복체크 기능 구현 학습 가이드 (Study Note)

이 문서는 Spring Boot + Mustache 환경에서 Ajax(fetch)를 이용한 아이디 중복체크 기능을 구현하는 전체 과정을 정리한 학습 자료입니다.

---

## 1. 사전 준비 및 규칙 확인 (Research)
구현 전, 프로젝트의 공통 규칙(`common-rule.md`)과 기술 스택을 확인합니다.

- **패키지 구조**: 도메인 기반 플랫 구조 (`user` 폴더 안에 모든 관련 파일 위치)
- **어노테이션 순서**:
    - **Service**: `@Transactional(readOnly = true)` → `@RequiredArgsConstructor` → `@Service`
    - **RestController**: `@RequiredArgsConstructor` → `@RestController`
- **응답 방식**: REST API는 반드시 `Resp<T>` 공통 래퍼 클래스를 사용하여 JSON 응답

---

## 2. 백엔드 구현 단계 (Execution)

### 1단계: Repository 확인
`UserRepository.java`에 `findByUsername` 메서드가 있는지 확인합니다. Spring Data JPA의 쿼리 메서드 기능을 활용합니다.
```java
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
```

### 2단계: Service 로직 구현
`UserService.java`에 중복 여부를 판단하는 비즈니스 로직을 추가합니다.
- **핵심**: `isPresent()`를 사용하여 데이터 존재 여부를 `boolean`으로 반환합니다.
- **규칙**: 조회 작업이므로 클래스 레벨의 `@Transactional(readOnly = true)`를 적용합니다.
```java
public boolean usernameCheck(String username) {
    return userRepository.findByUsername(username).isPresent();
}
```

### 3단계: REST API 컨트롤러 생성
`/api` 접두사를 사용하는 `UserApiController.java`를 생성합니다.
- **핵심**: `Resp.ok(isDuplicate)`를 통해 결과를 반환합니다.
```java
@GetMapping("/api/users/username-check")
public ResponseEntity<?> usernameCheck(@RequestParam("username") String username) {
    boolean isDuplicate = userService.usernameCheck(username);
    return Resp.ok(isDuplicate);
}
```

### 4단계: SSR 컨트롤러 연결
회원가입 페이지를 보여주기 위해 `UserController.java`에 GET 매핑을 추가합니다.
```java
@GetMapping("/join-form")
public String joinForm() {
    return "user/join-form"; // mustache 템플릿 경로
}
```

---

## 3. 프론트엔드 구현 단계 (Ajax Skill)

### 5단계: Mustache & JavaScript 구현
`join-form.mustache` 파일에서 Ajax(fetch)를 사용하여 서버와 통신합니다.

**중요 규칙 (Ajax Skill):**
1. **async/await 사용**: 비동기 처리를 깔끔하게 관리하기 위해 `.then()` 대신 사용합니다.
2. **DOM 접근**: `document.querySelector`를 사용하여 요소를 선택합니다.
3. **응답 분기**: `result.body` 값(백엔드에서 넘겨준 boolean)에 따라 화면을 갱신합니다.

```javascript
async function checkUsername() {
    // 1. 값 수집
    let username = document.querySelector("#username").value;
    let msgBox = document.querySelector("#username-msg");

    // 2. fetch 호출 (async/await)
    try {
        let response = await fetch(`/api/users/username-check?username=${username}`);
        let result = await response.json(); // Resp<T> 구조로 받음

        // 3. result.body 값으로 중복 여부 확인 및 DOM 조작
        if (result.body === true) {
            msgBox.innerText = "이미 존재하는 아이디입니다.";
            msgBox.style.color = "red";
        } else {
            msgBox.innerText = "사용 가능한 아이디입니다.";
            msgBox.style.color = "green";
        }
    } catch (error) {
        alert("통신 오류가 발생했습니다.");
    }
}
```

---

## 4. 최종 검증 (Validation)
- **빌드 확인**: `./gradlew classes` 명령어로 컴파일 오류가 없는지 확인합니다.
- **기능 테스트**: 브라우저에서 `/join-form`에 접속하여 아이디 입력 후 '중복확인' 버튼을 클릭해 메시지가 정상적으로 출력되는지 확인합니다.

---

## 요약 (Key Takeaways)
- **REST API**와 **SSR**은 컨트롤러 파일을 분리한다.
- **Service**는 DTO 또는 기본 타입을 반환하며, `@Transactional` 설정에 유의한다.
- **JavaScript**에서는 `async/await`를 사용하여 가독성을 높이고, 서버의 공통 응답 구조(`Resp`)를 정확히 파악하여 로직을 짠다.
