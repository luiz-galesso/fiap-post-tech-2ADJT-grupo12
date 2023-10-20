package com.tech.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.tech.challenge.inscricao.gestaousuario.entity",
		"com.tech.challenge.inscricao.gestaousuario.service",
		"com.tech.challenge.inscricao.gestaousuario.controller",
		"com.tech.challenge.inscricao.gestaousuario.repository",
		"com.tech.challenge.inscricao.gestaousuario.dto",
		"com.tech.challenge.inscricao.gestaoetapa.controller",
		"com.tech.challenge.inscricao.gestaoetapa.dto",
		"com.tech.challenge.inscricao.gestaoetapa.entity",
		"com.tech.challenge.inscricao.gestaoetapa.repository",
		"com.tech.challenge.inscricao.gestaoetapa.service"
})
@EntityScan(basePackages = {"com.tech.challenge.inscricao.gestaousuario.entity", "com.tech.challenge.inscricao.gestaoetapa.entity"})
@EnableJpaRepositories(basePackages = {"com.tech.challenge.inscricao.gestaousuario.repository","com.tech.challenge.inscricao.gestaoetapa.repository"})
public class ChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

}
