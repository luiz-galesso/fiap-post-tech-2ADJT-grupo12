package com.fase2.techchallenge.fiap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiCadastroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiCadastroApplication.class, args);
    }

}