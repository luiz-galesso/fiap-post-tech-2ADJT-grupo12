package com.fase2.techchallenge.fiap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiNotificacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiNotificacaoApplication.class, args);
    }

}