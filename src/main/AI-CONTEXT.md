<!-- Parent: ../AI-CONTEXT.md -->

# main

## 목적
애플리케이션의 핵심 로직과 설정, 리소스를 포함하는 메인 소스 디렉토리.

## 주요 파일
- (하위 디렉토리 중심)

## 하위 디렉토리
- `java/` - Java 소스 파일 (`com.example.demo` 패키지)
- `resources/` - 설정 파일(properties), DB 초기화 스크립트(sql), 템플릿(mustache), 정적 리소스(static)

## AI 작업 지침
- 새로운 도메인 추가 시 `java/` 내에 새 패키지를 생성함.
- `resources/application.properties`의 설정 정보를 항상 확인하여 로컬 환경과 맞추어야 함.
