package com.docker.atsea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.docker.atsea.configuration.JpaConfiguration;
import com.docker.atsea.controller.LoginController;
import com.docker.atsea.security.JwtFilter;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.docker.atsea"})
@EntityScan("com.docker.atsea.model")
@EnableJpaRepositories("com.docker.atsea.repository")
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
