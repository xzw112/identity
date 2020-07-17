package com.tiptimes.identity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("*")
                        .allowedMethods("PUT","POST","GET","DELETE","OPTIONS")
                        .allowedHeaders("Content-Type", "Content-Length", "Authorization", "Accept", "X-Requested-With", "remember-me")
                        .allowCredentials(true).maxAge(3600);
            }
        };
    }
}
