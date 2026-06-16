package com.yiyang.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration // 必须有这个注解！
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*"); // 允许所有域名
        config.setAllowCredentials(true);    // 允许携带Cookie/Token
        config.addAllowedMethod("*");        // 允许所有方法 (GET, POST, OPTIONS等)
        config.addAllowedHeader("*");        // 允许所有头
        config.addExposedHeader("satoken");  // 如果后端要返回token在header里，需要暴露

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 对所有路径生效

        return new CorsWebFilter(source);
    }
}