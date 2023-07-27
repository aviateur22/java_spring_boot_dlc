package com.ctoutweb.dlc.service;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.exception.custom.UserFindException;
import com.ctoutweb.dlc.model.TokenIssue;
import com.ctoutweb.dlc.model.auth.LoginRequest;
import com.ctoutweb.dlc.model.auth.LoginResponse;
import com.ctoutweb.dlc.model.auth.LogoutResponse;
import com.ctoutweb.dlc.model.auth.RegisterMailingRequest;
import com.ctoutweb.dlc.model.auth.RegisterMailingResponse;
import com.ctoutweb.dlc.model.auth.RegisterRequest;
import com.ctoutweb.dlc.model.auth.RegisterResponse;
import com.ctoutweb.dlc.repository.UserRepository;
import com.ctoutweb.dlc.security.AesEncryption;
import com.ctoutweb.dlc.security.authentication.UserPrincipal;
import com.ctoutweb.dlc.security.token.JwtIssuer;
import com.ctoutweb.dlc.service.mail.MailService;

@Service
public class AuthService {	
	
	private final MailService mailService;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	private final JwtIssuer jwtIssuer;
	private final AesEncryption aesEncryption;
	

	public AuthService(
			UserRepository userRepository, 
			AuthenticationManager authenticationManager, 
			JwtIssuer jwtIssuer, 
			TokenService tokenService, 
			PasswordEncoder passwordEncoder, 
			MailService mailService, 
			AesEncryption aesEncryption) {
		super();
		this.mailService = mailService;
		this.passwordEncoder = passwordEncoder;		
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.tokenService = tokenService;
		this.jwtIssuer = jwtIssuer;
		this.aesEncryption = aesEncryption;
	}
	
	public RegisterMailingResponse registerMailingLink(RegisterMailingRequest request) {
		try {
			String encryptData = aesEncryption.encrypt("sdsdsdsdsdsdsfdfdfdfdffd fjldjdgmfgfmgfmdgkfdgkfdgkmfd jdfdfkdfkdfkdùkggùmfgkfsg dmùkgùfgkfkgfùgkfùgkfgfgkfgkfùgkfùdgkfùg ùfgkfgfd*g*fgkù*dfgkdg");
			String decryptData = aesEncryption.decrypt(encryptData);
			System.out.println(encryptData);
			System.out.println(decryptData);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		mailService.sendEmail(request.getSubject(), request.getRecipientMail());			
		return RegisterMailingResponse.builder().withMessage("ddd").build();
	}

	public RegisterResponse register(RegisterRequest request) {			
		userRepository.findUserByEmail(request.getEmail()).ifPresent(user->{
			throw new UserFindException("cet email est déja utilisé");
		});
		
		request.setPassword(passwordEncoder.encode(request.getPassword()));
		int userId = userRepository.saveUser(request);
		
		RegisterResponse response = RegisterResponse.builder()
		.withMessage("votre inscription est confirmée")
		.withUserId(userId)
		.build();
		
		return response;
	}
	
	public LoginResponse authenticate(LoginRequest request) {		
		Authentication auth =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));		
		SecurityContextHolder.getContext().setAuthentication(auth);		
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
		
		// gestion du token a jouter en bdd
		TokenIssue tokenIssue = jwtIssuer.issue(userPrincipal);		
		tokenService.saveToken(userPrincipal.getId(), tokenIssue);
		
		return new LoginResponse(tokenIssue.getToken());
	}
	
	public LogoutResponse logout(int userId) {
		tokenService.deleteToken(userId);
		return LogoutResponse.builder().withMessage("à bientôt").build();
	}
	
	
	
}
