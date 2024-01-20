package com.fase2.techchallenge.fiap.gestaonotificacao.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI myOpenAPI() {

        Info info = new Info()
                .title("API-NOTIFICACAO")
                .version("1.0.0")
                .description("API de notificacoes do sistema de parquímetro");

        return new OpenAPI().info(info);
    }
}