package com.provider.lead_management.Config;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000","https://lead-management-system-5g0aj0r1r-shrinidhichellamcs-projects.vercel.app")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}