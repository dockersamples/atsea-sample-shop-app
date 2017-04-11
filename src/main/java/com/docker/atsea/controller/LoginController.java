package com.docker.atsea.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/login/")
public class LoginController {
	
	private final LoginService loginService;
	private final JwtService jwtService;
	
	@SuppressWarnings("unused")
	public LoginController() {
		this(null, null);
	}
	
	@Autowired
	public LoginController(LoginService loginService, JwtService jwtService) {
		this.loginService = loginService;
		this.jwtService - jwtService;
	}
	
	@RequestMapping(path = "", method = POST, produces = APPLICATION_JSON_VALUE)
	public MinimalProfile login(@RequestBody LoginCredentials credentials, 
			                     HttpServletResponse response) {
		return loginService.login(credentials)
                .map(minimalProfile -> {
                    try {
                        response.setHeader("Token", jwtService.tokenFor(minimalProfile));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return minimalProfile;
                })
                .orElseThrow(() -> new FailedToLoginException(credentials.getUsername()));
	}

}
