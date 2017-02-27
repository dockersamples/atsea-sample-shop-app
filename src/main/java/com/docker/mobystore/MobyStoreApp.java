package com.docker.mobystore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.docker.mobystore.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.docker.mobystore"})
public class MobyStoreApp {

	public static void main(String[] args) {
		SpringApplication.run(MobyStoreApp.class, args);
	}
}
