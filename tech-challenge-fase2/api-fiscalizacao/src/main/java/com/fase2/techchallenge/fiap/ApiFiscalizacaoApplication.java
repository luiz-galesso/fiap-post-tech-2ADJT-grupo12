package com.fase2.techchallenge.fiap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableMongoRepositories
public class ApiFiscalizacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiFiscalizacaoApplication.class, args);
    }

}