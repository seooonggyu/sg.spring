# Spring Boot Starter Template

이 프로젝트는 새로운 Spring Boot 프로젝트를 시작할 때 기본 뼈대로 사용할 수 있는 **초기 보일러플레이트(Boilerplate) 코드**입니다. 
Spring, Spring Boot, Spring Security, JWT 등을 활용하여 웹 애플리케이션 개발에 필수적인 핵심 공통 기능들을 미리 구현해 두었습니다.

## 주요 기능 (Core Features)

1. **사용자 가입 및 인증**
   * 아이디(username), 비밀번호(password), 이름(name)을 입력하여 가입할 수 있는 기본 로직 및 화면 제공
2. **JWT 기반 인증 (Spring Security + JWT)**
   * Access Token 및 Refresh Token 발급, 검증 로직 구현
   * 상태를 유지하지 않는(Stateless) 안전한 REST API 인증 처리
3. **권한 관리 시스템 (Permission Management)**
   * 역할 및 상세 권한을 제어할 수 있는 엔티티(`Permission`, `PermissionDetail`, `PermissionUser`) 사전 구축
   * 사용자별로 세부적인 접근 권한을 관리하는 토대 마련

## 🛠 주요 기술 스택 (Tech Stack)

* **Language**: Java 17
* **Framework**: Spring Boot (Spring Web, Spring Data JPA, Spring Security)
* **Build Tool**: Gradle
* **Database / ORM**: MySQL, Spring Data JPA, MyBatis 혼용
  * **JPA**: 단순 CRUD 및 단일 식별자 조회 등 직관적이고 빠른 개발에 활용
  * **MyBatis**: 조인이 많거나 동적 쿼리가 필요한 복잡한 리스트 조회에 활용
* **View**: Thymeleaf (기본 뷰 페이지 및 관리자 페이지 렌더링)

## 📁 주요 아키텍처 특징
* **하이브리드 구조**: Thymeleaf 템플릿을 활용한 일반적인 웹 페이지 렌더링(`Controller`)과, 클라이언트 애플리케이션과 통신하기 위한 JSON 기반 API(`RestController`) 환경이 모두 세팅되어 있습니다.
