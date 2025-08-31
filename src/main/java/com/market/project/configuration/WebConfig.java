package com.market.project.configuration;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Autowired
    private Environment env;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] profiles = env.getActiveProfiles();
        String profile = profiles.length > 0 ? profiles[0] : "development";
        Dotenv dotenv = Dotenv.configure().filename(".env." + profile).load();
        
        registry.addMapping("/**") // todos los endpoints
                .allowedOrigins(dotenv.get("ALLOWED_ORIGINS").split(";"))
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
