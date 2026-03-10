<!-- Parent: ../AI-CONTEXT.md -->

# board

## 목적
게시글(Board) 도메인에 대한 CRUD 및 비즈니스 로직 처리.

## 주요 파일
| 파일명 | 설명 |
|--------|------|
| Board.java | 게시글 엔티티 |
| BoardController.java | SSR용 컨트롤러 (Mustache) |
| BoardService.java | 게시글 비즈니스 로직 |
| BoardRepository.java | DB 접근 레이어 |
| BoardRequest.java | 요청 DTO 그룹 |
| BoardResponse.java | 응답 DTO 그룹 |

## AI 작업 지침
- 게시글 목록, 상세, 작성, 수정, 삭제 로직을 포함함.
- `common-rule.md`에 따라 REST API가 필요한 경우 `BoardApiController.java`를 추가로 생성함.
