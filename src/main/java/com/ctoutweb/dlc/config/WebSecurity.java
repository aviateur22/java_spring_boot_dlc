package com.ctoutweb.dlc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ctoutweb.dlc.security.JwtAuthenticationFilter;
import com.ctoutweb.dlc.security.UserPrincipalDetailService;

@Configuration
@EnableWebSecurity
public class WebSecurity {
	
	private final UserPrincipalDetailService userPrincipalDetailService;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationEntryPoint authenticationEntryPoint;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		http
			.csrf(csrf->csrf.disable())
			.exceptionHandling(exception->exception.authenticationEntryPoint(authenticationEntryPoint))
			.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.formLogin(login->login.disable())
			.authorizeHttpRequests(httpRequest->httpRequest
					.requestMatchers("/api/v1/auth/**").permitAll()					
					.anyRequest().authenticated());
			
		return http.build();
	}
	
	public WebSecurity(
			UserPrincipalDetailService userPrincipalDetailService, 
			JwtAuthenticationFilter jwtAuthenticationFilter, 
			AuthenticationEntryPoint authenticationEntryPoint) {
		super();
		this.userPrincipalDetailService = userPrincipalDetailService;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}

	@Bean
	PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userPrincipalDetailService)
				.and()
				.build();
	}

}
