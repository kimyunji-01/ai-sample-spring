<!-- Parent: ../AI-CONTEXT.md -->

# resources

## 목적
애플리케이션의 설정 정보, 정적 리소스, 템플릿 파일들을 관리하는 공간.

## 주요 파일
| 파일명 | 설명 |
|--------|------|
| application.properties | Spring Boot 애플리케이션 설정 (포트, DB, 뷰 리졸버 등) |

## 하위 디렉토리
- `db/` - DB 초기화 스크립트 (`data.sql`)
- `static/` - JavaScript, CSS, 이미지 등 정적 리소스
- `templates/` - Mustache 템플릿 파일

## AI 작업 지침
- HTML 화면 변경 시 `templates/` 하위의 Mustache 파일을 수정할 것.
- 초기 데이터 설정이 필요할 경우 `db/data.sql`을 업데이트할 것.
