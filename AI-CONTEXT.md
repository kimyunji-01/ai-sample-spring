# demo

## 목적
Spring Boot와 Mustache를 이용한 SSR(Server Side Rendering) 및 REST API 데모 프로젝트.

## 주요 파일
| 파일명 | 설명 |
|--------|------|
| build.gradle | 프로젝트 의존성 및 빌드 설정 |
| AI-GUIDE.md | AI 가이드 및 스킬 정의 |
| GEMINI.md | Gemini CLI 전용 지침 (최상위 원칙) |
| settings.gradle | 프로젝트 설정 |
| CLAUDE.md | Claude 전용 지침 |
| gradlew | Gradle 래퍼 (Unix) |
| gradlew.bat | Gradle 래퍼 (Windows) |

## 하위 디렉토리
- `.ai/` - AI 에이전트 전용 설정 및 스킬 정의
- `.person/` - 개인화된 워크플로우 및 규칙 (사용자 전용 데이터)
- `src/` - 소스 코드 및 리소스
- `gradle/` - Gradle 래퍼 관련 설정

## AI 작업 지침
- 모든 코드는 `.ai/rules/common-rule.md` 컨벤션을 준수해야 함.
- 도메인 기반 플랫 구조를 사용하며, Entity, Service, Controller(SSR/REST 분리) 규칙을 따름.
- PK 타입은 `Integer`를 사용함.

## 테스트
- `./gradlew test` (Windows: `gradlew.bat test`)

## 의존성
- 내부: Java 17+, Spring Boot 3.x
- 외부: Spring Web, Spring Data JPA, Mustache, H2 Database
