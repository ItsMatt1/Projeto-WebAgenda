package com.WebAgenda.WebAgenda;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Aplica para todos os endpoints
                .allowedOrigins("*")  // Permite todas as origens
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")  // Permite métodos específicos
                .allowedHeaders("*")  // Permite todos os cabeçalhos
                .allowCredentials(false);  // Desabilita o envio de credenciais
    }
}
