<!-- Parent: ../AI-CONTEXT.md -->

# gradle

## 목적
Gradle 빌드 도구의 래퍼 및 설정 파일을 보관하는 디렉토리.

## 주요 파일
| 파일명 | 설명 |
|--------|------|
| (폴더 중심) | Gradle 래퍼 관련 설정 |

## 하위 디렉토리
- `wrapper/` - Gradle 래퍼의 실행 바이너리 및 설정 (`gradle-wrapper.properties`)

## AI 작업 지침
- 빌드 도구의 버전이나 설정을 변경해야 할 경우 `wrapper/gradle-wrapper.properties`를 확인해야 함.

## 테스트
- `./gradlew --version`
