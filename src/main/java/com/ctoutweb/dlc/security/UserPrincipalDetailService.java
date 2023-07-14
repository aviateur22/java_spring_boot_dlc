package com.ctoutweb.dlc.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ctoutweb.dlc.model.User;
import com.ctoutweb.dlc.model.UserRole;
import com.ctoutweb.dlc.service.UserService;

@Component
public class UserPrincipalDetailService implements UserDetailsService {
	
	private final UserService userService;
	

	public UserPrincipalDetailService(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = (User) userService.findUserInformation(username);
		System.out.println(user.getIsAccountActive());
		
		return UserPrincipal.builder()
				.withId(user.getId())
				.withEmail(user.getEmail())
				.withPassword(user.getPassword())
				.withIsAccountActive(user.getIsAccountActive())
				.withAuthorities(convertListOfRoleToSimpleGrantedAuth(user.getRoles()))
				.build();
	}
	
	private Collection<SimpleGrantedAuthority> convertListOfRoleToSimpleGrantedAuth(List<UserRole> roles) {
		return roles
				.stream()
				.map(role->new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
	}

}
