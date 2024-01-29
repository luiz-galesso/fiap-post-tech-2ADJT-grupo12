package com.fase2.techchallenge.fiap.fiscalizacao.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI myOpenAPI() {

        Info info = new Info()
                .title("API-FISCALIZACAO")
                .version("1.0.0")
                .description("API de fiscalizacao do sistema de parquímetro");

        return new OpenAPI().info(info);
    }
}