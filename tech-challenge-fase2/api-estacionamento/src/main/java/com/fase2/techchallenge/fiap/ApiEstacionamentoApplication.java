package com.fase2.techchallenge.fiap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableFeignClients
public class ApiEstacionamentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiEstacionamentoApplication.class, args);
    }

}