package com.ctoutweb.dlc.security.authentication;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.ctoutweb.dlc.repository.UserRepositoryImp;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ctoutweb.dlc.exception.custom.UserNotFoundException;
import com.ctoutweb.dlc.model.User;
import com.ctoutweb.dlc.model.UserRole;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailService implements UserDetailsService {	
	
	private final UserRepositoryImp userRepository;

	public UserPrincipalDetailService(UserRepositoryImp userRepository) {
		super();		
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findUserByEmail(username).orElseThrow(()->new UserNotFoundException("email ou mot de passe invalid"));
		
		if(user.getAccount() == null) throw new UserNotFoundException("email ou mot de passe invalid");
		
		return UserPrincipal.builder()
				.withId(user.getId())
				.withEmail(user.getEmail())
				.withPassword(user.getAccount().getPassword())
				.withIsAccountActive(user.getAccount().getIsAccountActive())
				.withAuthorities(convertListOfRoleToSimpleGrantedAuth(user.getRoles()))
				.withMaximumAccountActivationDate(user.getAccount().getMaximumAccountActivationDate())
				.build();
	}
	
	private Collection<SimpleGrantedAuthority> convertListOfRoleToSimpleGrantedAuth(List<UserRole> roles) {
		return roles
				.stream()
				.map(role->new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
	}

}
