package com.docker.mobyartshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.docker.mobyartshop.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.docker.mobyartshop"})
@EntityScan("com.docker.mobyartshop.model")
@EnableJpaRepositories("com.docker.mobyartshop.repository")
public class MobyArtShopApp {

	public static void main(String[] args) {
		SpringApplication.run(MobyArtShopApp.class, args);
	}
}
