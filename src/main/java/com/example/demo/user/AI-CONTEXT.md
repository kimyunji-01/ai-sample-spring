<!-- Parent: ../AI-CONTEXT.md -->

# user

## 목적
사용자(User) 관리, 회원가입, 로그인 등 인증/인가 도메인 처리.

## 주요 파일
| 파일명 | 설명 |
|--------|------|
| User.java | 사용자 엔티티 |
| UserController.java | SSR용 컨트롤러 (Mustache) |
| UserService.java | 사용자 비즈니스 로직 |
| UserRepository.java | DB 접근 레이어 |
| UserRequest.java | 요청 DTO 그룹 |
| UserResponse.java | 응답 DTO 그룹 |

## AI 작업 지침
- 회원 정보 보호를 위해 민감한 정보는 `UserResponse` DTO에서 제외할 것.
- 세션 기반 인증 시 `HttpSession`을 주입받아 사용할 것.
