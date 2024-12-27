package jkkim.space_reservation.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// CORS 해제란 Cross-Origin Resource Sharing (CORS) 정책을 비활성화하거나 완화하여
// 특정 도메인 또는 모든 도메인에서 요청을 허용하도록 설정하는 것
//클라이언트(프론트엔드)와 서버(백엔드)가 서로 다른 도메인 또는 포트를 사용할 때 발생하는 CORS 문제를 해결하기 위해 사용

// CORS 설정
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*"); // 모든 ip에 응답 허용
        config.addAllowedMethod("*"); // 모든 HTTP Method 에 응답 허용
        config.addAllowedHeader("*"); // 모든 HTTP Header 에 응답 허용

        // 특정 경로(/vi/api/**)에만 CORS 설정을 적용
        source.registerCorsConfiguration("/vi/api/**", config);

        return new CorsFilter();
    }
}
