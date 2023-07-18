package com.ctoutweb.dlc.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ctoutweb.dlc.security.token.JwtDecoder;
import com.ctoutweb.dlc.security.token.JwtToUserPrincipalConverter;
import com.ctoutweb.dlc.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtDecoder jwtDecoder;
	private final JwtToUserPrincipalConverter jwtToUserPrincipal;
	private final TokenService tokenService;

	public JwtAuthenticationFilter(JwtDecoder jwtDecoder, JwtToUserPrincipalConverter jwtToUserPrincipal, TokenService tokenService) {
		super();
		this.jwtDecoder = jwtDecoder;
		this.jwtToUserPrincipal = jwtToUserPrincipal;
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException{
		extractTokenFromHeaders(request)		
		.map(token->jwtDecoder.decode(token))
		.map(decodeJwt->jwtToUserPrincipal.convert(decodeJwt))		
		.map(userPrincipal->new UserPrincipalAutenticationToken(userPrincipal))		
		.ifPresent(auth->{
			SecurityContextHolder.getContext().setAuthentication(auth);
			this.validateJwt(request);
		});
		
		
		filterChain.doFilter(request, response);
		
	}
	
	private void validateJwt(HttpServletRequest request) {
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String token = extractTokenFromHeaders(request).get();
		tokenService.validateToken(token, user.getId());
	}
	
	private Optional<String> extractTokenFromHeaders(HttpServletRequest request) {
		var token = request.getHeader("authorization");
		
		if(!StringUtils.hasText(token) || !token.startsWith("Bearer ")) return Optional.empty();
		
		return Optional.of(token.substring(7));
		
	}

}
