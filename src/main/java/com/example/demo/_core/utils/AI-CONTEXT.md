<!-- Parent: ../AI-CONTEXT.md -->

# utils

## 목적
공통 응답 래퍼 및 유틸리티 클래스 모음.

## 주요 파일
| 파일명 | 설명 |
|--------|------|
| Resp.java | REST API 공통 응답 포맷 (성공/실패 처리) |

## AI 작업 지침
- 모든 REST API는 반드시 `Resp.ok()` 또는 `Resp.fail()`을 사용하여 응답을 래핑함.
- `Resp` 객체는 HTTP 상태 코드, 메시지, 그리고 본문 데이터를 포함함.
