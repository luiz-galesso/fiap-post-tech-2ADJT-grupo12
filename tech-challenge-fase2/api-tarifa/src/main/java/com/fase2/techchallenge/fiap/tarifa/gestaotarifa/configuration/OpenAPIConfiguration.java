package com.fase2.techchallenge.fiap.tarifa.gestaotarifa.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI myOpenAPI() {

        Info info = new Info()
                .title("API-TARIFA")
                .version("1.0.0")
                .description("API de tarifas do sistema de parquímetro");

        return new OpenAPI().info(info);
    }
}