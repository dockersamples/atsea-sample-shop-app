package com.docker.atsea.configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.util.FileCopyUtils;

public class SecretsEnvPostProcessor implements EnvironmentPostProcessor {

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment,
			SpringApplication springApplication) {

		if(environment.acceptsProfiles("postgresql")) {
			try(BufferedReader br = new BufferedReader(new FileReader("/run/secrets/postgres_password"))) {
				String password = FileCopyUtils.copyToString(br);
				Map<String, Object> properties = new HashMap();
				properties.put("spring.datasource.password", password);
				PropertySource<?> propertySource = new MapPropertySource("docker", properties);
				environment.getPropertySources().addLast(propertySource);

			} catch (IOException e) {
				throw new IllegalStateException("Could not read database password from /run/secrets/postgres_password", e);
			}
		}
	}
}
