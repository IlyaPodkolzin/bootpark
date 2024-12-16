package com.ilyamorozov.bootpark.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Разрешить credentials (например, cookies)
        config.addAllowedOrigin("http://localhost:4000"); // Разрешённые источники
        config.addAllowedHeader("*"); // Разрешить все заголовки
        config.addAllowedMethod("*"); // Разрешить все методы (GET, POST, и т.д.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Применить ко всем URL

        return new CorsFilter(source);
    }
}