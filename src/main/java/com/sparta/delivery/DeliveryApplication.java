package com.sparta.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * [역할]
 * - 스프링 부트 애플리케이션의 진입점(Entry Point)
 * - 전체 프로젝트의 실행 및 전역 설정의 시작 지점
 *
 * [주요 기능]
 * - @SpringBootApplication:
 *     ├─ @Configuration : 스프링 설정 클래스로 등록
 *     ├─ @EnableAutoConfiguration : 스프링 부트 자동 설정 활성화
 *     └─ @ComponentScan : 하위 패키지 전체를 스캔하여 Bean 등록
 *
 * - @ConfigurationPropertiesScan:
 *     └─ application.yml / properties 파일에 정의된 @ConfigurationProperties 클래스를 자동 감지
 *        (예: AWS, JWT, DB 설정 등 외부 구성값을 객체로 매핑)
 *
 * - @EnableJpaAuditing:
 *     └─ BaseEntity에 선언된 @CreatedDate, @LastModifiedDate 등 JPA Auditing 기능 활성화
 *        (엔티티 생성/수정 시각을 자동으로 관리)
 *
 * [실행 흐름]
 * 1. main() 메서드 실행
 * 2. SpringApplication.run() → 스프링 컨테이너 및 내장 톰캣 구동
 * 3. @ComponentScan 기준인 com.sparta.delivery 이하의 모든 컴포넌트 탐색
 */
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableJpaAuditing
public class DeliveryApplication {

    /**
     * [기능]
     * - 스프링 부트 애플리케이션을 구동시키는 main() 메서드.
     * - SpringApplication.run()이 스프링 컨텍스트를 초기화하고 내장 톰캣 서버를 실행한다.
     *
     * @param args 커맨드라인 인자 (일반적으로 사용되지 않음)
     */
    public static void main(String[] args) {
        SpringApplication.run(DeliveryApplication.class, args);
    }
}
