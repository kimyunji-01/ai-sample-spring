# Project Code Convention

## 1. Naming Convention

### 1.1. Class
- Controller: `{Domain}Controller`
  - ex) `BoardController`, `UserController`
- Service: `{Domain}Service`
  - ex) `BoardService`, `UserService`
- Repository: `{Domain}Repository`
  - ex) `BoardRepository`, `UserRepository`
- Entity: `{Domain}`
  - ex) `Board`, `User`
- Request DTO: `{Domain}Request.{Action}`
  - ex) `UserRequest.JoinDTO`, `BoardRequest.SaveDTO`
- Response DTO: `{Domain}Response.{DTO명}`
  - ex) `UserResponse.LoginDTO`, `BoardResponse.DetailDTO`

### 1.2. Method
- camelCase를 사용한다.
- ex) `findAll`, `findById`

### 1.3. Variable
- camelCase를 사용한다.
- ex) `title`, `content`

## 2. Package Structure

- `com.example.demo`
  - `_core`: 프로젝트 전역에서 사용되는 Core 로직
  - `board`: 게시판 도메인
  - `user`: 유저 도메인

## 3. DTO (Data Transfer Object)

- **요청 DTO**: 기능명을 적는다.
  - `UserRequest.JoinDTO`
- **응답 DTO**: 상세 정보를 담는 DTO는 `Detail`을 접미사로 사용한다.
  - `BoardResponse.DetailDTO`
- DTO는 Service에서 만들고, Entity를 Controller에 전달하지 않는다.

## 4. Entity

- 모든 연관관계는 LAZY로 설정한다. (OSIV는 false)
  - `@ManyToOne(fetch = FetchType.LAZY)`
- 컬렉션은 생성자에 넣지 않는다.

## 5. Lombok Usage

- `@NoArgsConstructor`: 기본 생성자
- `@Data`: Getter, Setter, `toString`, `equals`, `hashCode`
- `@Builder`: 빌더 패턴
- `@RequiredArgsConstructor`: `final` 필드에 대한 생성자

## 6. Annotations

- `@Entity`: JPA Entity
- `@Table(name = "...")`: 테이블명 지정
- `@Id`, `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Primary Key
- `@CreationTimestamp`: 생성 시간 자동 기록
- `@Controller`, `@Service`, `@Repository`: Spring Bean 등록
- `@Transactional(readOnly = true)`: 서비스 클래스 레벨에서 사용
