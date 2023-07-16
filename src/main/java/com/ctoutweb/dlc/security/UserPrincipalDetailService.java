package com.ctoutweb.dlc.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ctoutweb.dlc.exception.custom.UserNotFoundException;
import com.ctoutweb.dlc.model.User;
import com.ctoutweb.dlc.model.UserRole;
import com.ctoutweb.dlc.repository.UserRepository;

@Component
public class UserPrincipalDetailService implements UserDetailsService {	
	
	private final UserRepository userRepository;	

	public UserPrincipalDetailService(UserRepository userRepository) {
		super();		
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findUserByEmail(username).orElseThrow(()->new UserNotFoundException("email ou mot de passe invalid"));
		
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
