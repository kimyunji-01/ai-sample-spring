<!-- Parent: ../AI-CONTEXT.md -->

# reply

## 목적
댓글(Reply) 도메인에 대한 CRUD 및 게시글과의 연관 관계 처리.

## 주요 파일
| 파일명 | 설명 |
|--------|------|
| Reply.java | 댓글 엔티티 |
| ReplyController.java | SSR용 컨트롤러 (Mustache) |
| ReplyService.java | 댓글 비즈니스 로직 |
| ReplyRepository.java | DB 접근 레이어 |
| ReplyRequest.java | 요청 DTO 그룹 |
| ReplyResponse.java | 응답 DTO 그룹 |

## AI 작업 지침
- 댓글은 게시글(`Board`)에 종속적인 구조를 가짐.
- 연관 관계 매핑 시 `FetchType.LAZY`를 준수할 것.
