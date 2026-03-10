<!-- Parent: ../AI-CONTEXT.md -->

# java

## 목적
Spring Boot 애플리케이션의 실행 로직과 도메인 모델이 포함된 Java 소스 루트.

## 주요 파일
| 파일명 | 설명 |
|--------|------|
| DemoApplication.java | Spring Boot 애플리케이션의 진입점 (Main Class) |

## 하위 디렉토리
- `com/example/demo/board/` - 게시판 도메인
- `com/example/demo/reply/` - 댓글 도메인
- `com/example/demo/user/` - 사용자(회원) 도메인
- `com/example/demo/_core/` - 공통 유틸리티 및 예외 처리

## AI 작업 지침
- `.ai/rules/common-rule.md`의 도메인 기반 플랫 구조를 엄격히 준수할 것.
- SSR Controller와 REST ApiController를 분리하여 작성할 것.
