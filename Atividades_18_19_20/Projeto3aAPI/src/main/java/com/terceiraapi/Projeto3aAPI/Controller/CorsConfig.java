package com.terceiraapi.Projeto3aAPI.Controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Os endpoints desta API sao /produtos (e nao /api/...), por isso "/**"
        registry.addMapping("/**")
                .allowedOriginPatterns(
                        "https://*.onrender.com",   // frontend publicado no Render
                        "http://localhost:*"        // testes locais (8080, 3000...)
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
