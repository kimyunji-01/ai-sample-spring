<skill_definition>
  <name>프로젝트 코드 컨벤션 스킬</name>
  <description>
    Spring Boot 프로젝트를 위한 프로젝트별 명명 규칙, 패키지 구조, DTO 사용 및 JPA 엔티티 규칙을 강제합니다.
  </description>

  <instructions>
    ### 1. 명명 규칙 (엄격 준수)
    - **Controller**: `{Domain}Controller` (예: `BoardController`)
    - **Service**: `{Domain}Service` (예: `BoardService`)
    - **Repository**: `{Domain}Repository` (예: `BoardRepository`)
    - **Entity**: `{Domain}` (예: `Board`)
    - **요청 DTO**: `{Domain}Request.{Action}DTO` (예: `UserRequest.JoinDTO`)
    - **응답 DTO**: `{Domain}Response.{Name}DTO` (예: `UserResponse.LoginDTO`)
      - 상세 보기의 경우, `DetailDTO` 접미사를 사용합니다 (예: `BoardResponse.DetailDTO`).
    - **메서드 및 변수**: 항상 `camelCase`를 사용합니다.

    ### 2. 패키지 구조
    - `com.example.demo._core`: 공통/글로벌 로직.
    - `com.example.demo.{domain}`: 도메인별 로직 (예: `board`, `user`).

    ### 3. DTO 및 서비스 계층 규칙
    - **DTO 생성**: DTO는 반드시 `Service` 계층에서 생성되어야 합니다.
    - **Controller 내 Entity 금지**: `Controller`에 `Entity`를 반환하거나 전달하지 마십시오. 모든 외부 통신에는 DTO를 사용합니다.
    - **DTO 명명**: 요청 DTO는 기능을 반영해야 합니다 (예: `JoinDTO`). 상세 정보를 위한 응답 DTO는 `DetailDTO`로 끝납니다.

    ### 4. JPA 및 엔티티 가이드라인
    - **지연 로딩 (Lazy Loading)**: 모든 연관관계는 반드시 `FetchType.LAZY`를 사용해야 합니다.
      - 예: `@ManyToOne(fetch = FetchType.LAZY)`
    - **OSIV**: `open-in-view=false`를 가정합니다.
    - **컬렉션**: 생성자에서 컬렉션을 초기화하지 마십시오.

    ### 5. Lombok 및 어노테이션
    - **표준 세트**: `@NoArgsConstructor`, `@Data`, `@Builder`, `@RequiredArgsConstructor`(final 필드용)를 사용합니다.
    - **표준 어노테이션**: 
      - `@Entity`, `@Table(name = "...")`
      - `@Id`, `@GeneratedValue(strategy = GenerationType.IDENTITY)`
      - `@CreationTimestamp`
      - 서비스 클래스 레벨에서 `@Transactional(readOnly = true)`를 적용합니다.
  </instructions>

  <validation_checklist>
    - [ ] Controller가 Entity 대신 DTO를 반환합니까?
    - [ ] 모든 JPA 관계가 `FetchType.LAZY`로 설정되어 있습니까?
    - [ ] 명명 규칙이 `{Domain}Request.{Action}DTO` 패턴을 따릅니까?
    - [ ] Service 클래스에 `@Transactional(readOnly = true)`가 적용되었습니까?
    - [ ] 패키지 구조가 도메인 주도 폴더 구조와 일치합니까?
  </validation_checklist>
</skill_definition>
