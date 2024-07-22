## Sample project description

### 구조
* src/main/asciidoc : API 문서 작성 ([spring rest docs](https://docs.spring.io/spring-restdocs/docs/current/reference/html5/))


* src/main/java/.../common/filter : filterChain 로직 정의
* src/main/java/.../common/response : 기본 Response 구조 정의
* src/main/java/.../common/utils : 각종 Util 정의


* src/main/java/.../sample/config : 각종 Config 정의
* src/main/java/.../sample/controller : API 서비스 컨트롤러 정의
* src/main/java/.../sample/controller/dto : API 서비스 컨트롤러에 사용되는 Request DTO 정의
* src/main/java/.../sample/mapper : MyBatis 매퍼 정의 ([mybatis-spring-boot](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/))
* src/main/java/.../sample/service : API 서비스 기능 정의
* src/main/java/.../sample/service/dto : API 서비스 기능에 사용되는 DTO (Response 용도로도 사용)


* src/main/resources : 각종 설정 파일 정의 (properties, log4j2 등등)


* src/test/java/.../sample/controller : API 문서를 위한 테스트용 컨트롤러 정의 ([spring rest docs](https://docs.spring.io/spring-restdocs/docs/current/reference/html5/))
* src/test/java/.../sample/document : 빌드 테스트 및 API 문서 작성 내용 정의 ([spring rest docs](https://docs.spring.io/spring-restdocs/docs/current/reference/html5/))
* src/test/resources/org/springframework/restdocs/templates : API 문서에 대한 템플릿 정의 ([spring rest docs](https://docs.spring.io/spring-restdocs/docs/current/reference/html5/))


* pom.xml : 프로젝트 기본 설정 및 maven 설정

### 실행
#### spring.profiles.active
* live : application.properties > application-live.properties + log4j2.xml 반영
* test : application.properties > application-test.properties + log4j2.xml 반영
* local : application.properties > application-local.properties + log4j2-local.xml 반영

### API 서비스
* http://localhost:10001/{URL} : API 서비스 URL

### API 문서
* maven 빌드가 실행되었을 때 target 폴더 아래에 생성 (IDE 러닝/디버깅 으로는 기존에 생성된 문서로 표시됨)
* http://localhost:10001/docs/index.html 에서 확인 가능
