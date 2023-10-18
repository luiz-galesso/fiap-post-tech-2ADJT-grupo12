package com.tech.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.tech.challenge.inscricao.gestaousuario.model"})
@EnableJpaRepositories(basePackages = {"com.tech.challenge.inscricao.gestaousuario.model"})
@ComponentScan(basePackages = {"com.tech.challenge.inscricao.gestaousuario.model",
		"com.tech.challenge.inscricao.gestaousuario.service",
		"com.tech.challenge.inscricao.gestaousuario.controller",
		"com.tech.challenge.inscricao.gestaousuario.repository"
})
public class ChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

}
