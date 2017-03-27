package com.docker.mobystore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.docker.mobystore.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.docker.mobystore"})
@EntityScan("com.docker.mobystore.model")
@EnableJpaRepositories("com.docker.mobystore.repository")
public class MobyStoreApp {

	public static void main(String[] args) {
		SpringApplication.run(MobyStoreApp.class, args);
	}
}
