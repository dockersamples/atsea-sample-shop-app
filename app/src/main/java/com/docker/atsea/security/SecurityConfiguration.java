package com.docker.atsea.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		
	// -----Basic Authentication implemented but not used -------
	
    @Autowired
    DataSource dataSource;
 
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM customer WHERE username = ?")
               .authoritiesByUsernameQuery("SELECT username, role FROM customer WHERE username=?");
    }
	
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
//		      .antMatchers(HttpMethod.POST, "/login/**").authenticated()
//		      .antMatchers(HttpMethod.DELETE, "/admin/**").authenticated()
//		      .antMatchers(HttpMethod.GET, "/order/**").authenticated()
//		      .antMatchers(HttpMethod.GET, "/customer/**").authenticated()
			.anyRequest().permitAll()
			.and().httpBasic()
			.and().csrf().disable();
	}	

}
