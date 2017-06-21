package com.docker.atsea;

import com.docker.atsea.security.JwtFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class AtSeaApp {

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/purchase/*");

		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(AtSeaApp.class, args);
	}
}
