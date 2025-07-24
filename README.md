# 🕒 Work Log Tracker
Spring Boot 기반 출퇴근 기록 웹 애플리케이션입니다.

직원의 출퇴근 시간을 기록하고, 날짜별 근무 기록을 조회할 수 있습니다.

<br>

## 🔧 기술스택
- **Front-End** : Thymeleaf
- **Back-End** : Spring Boot 3.5.3, Spring Security, JWT, Spring Data JPA
- **Database** : H2 (in-memory)

- > ✅ Java 17 이상이 설치되어 있어야 합니다.

<br>

## 📂 주요기능
- 회원인증 (로그인/회원가입/로그아웃)
- 출퇴근 기록 (오늘 날짜에만 기록)
- 로그인한 사용자 날짜별 근무 내역 조회
- 모든 사용자 날짜별 근무 내역 조회

<br>

## 🚀 실행 방법


### 1. 프로젝트 클론
```bash
git clone https://github.com/hyeon48615/work-log-tracker.git
cd work-log-tracker
```

<br>

### 2. 빌드 및 실행
```bash
./gradlew bootRun
```

<br>

### 3. 접속 URL
- http://localhost:8080

