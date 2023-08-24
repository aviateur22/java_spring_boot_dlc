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

import com.ctoutweb.dlc.security.authentication.JwtAuthenticationFilter;
import com.ctoutweb.dlc.security.authentication.UserPrincipalDetailService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurity {
	
	private final UserPrincipalDetailService userPrincipalDetailService;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationEntryPoint authenticationEntryPoint;
	
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
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		http
			.cors(cors->corsConfigurationSource())
			.csrf(csrf->csrf.disable())
			.exceptionHandling(exception->exception.authenticationEntryPoint(authenticationEntryPoint))
			.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.formLogin(login->login.disable())
			.authorizeHttpRequests(httpRequest->httpRequest
					.requestMatchers("/api/v1/dlc/auth/**").permitAll()
					.requestMatchers("/api/v1/dlc/admin/**").hasRole("ADMIN")
					.anyRequest().authenticated());
			
		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","http://localhost:4400"));
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
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
