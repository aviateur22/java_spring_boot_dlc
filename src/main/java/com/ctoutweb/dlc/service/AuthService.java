package com.ctoutweb.dlc.service;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.entity.UserEntity;
import com.ctoutweb.dlc.exception.custom.UserFindException;
import com.ctoutweb.dlc.model.TokenIssue;
import com.ctoutweb.dlc.model.User;
import com.ctoutweb.dlc.model.auth.CreateAccountRequest;
import com.ctoutweb.dlc.model.auth.CreateAccountResponse;
import com.ctoutweb.dlc.model.auth.LoginRequest;
import com.ctoutweb.dlc.model.auth.LoginResponse;
import com.ctoutweb.dlc.model.auth.LogoutResponse;
import com.ctoutweb.dlc.model.auth.RegisterMailingRequest;
import com.ctoutweb.dlc.model.encryption.EncryptRandomWordResponse;
import com.ctoutweb.dlc.model.auth.RegisterEmailRequest;
import com.ctoutweb.dlc.model.auth.RegisterEmailResponse;
import com.ctoutweb.dlc.repository.RandomTextUserRepository;
import com.ctoutweb.dlc.repository.UserRepository;
import com.ctoutweb.dlc.security.authentication.UserPrincipal;
import com.ctoutweb.dlc.security.token.JwtIssuer;
import com.ctoutweb.dlc.service.mail.EmailSubject;
import com.ctoutweb.dlc.service.mail.MailService;
import com.ctoutweb.dlc.service.random.RandomCategory;
import com.ctoutweb.dlc.service.random.RandomImageService;
import com.ctoutweb.dlc.service.random.RandomWordService;

@Service
public class AuthService {	
	
	private final MailService mailService;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	private final JwtIssuer jwtIssuer;
	private final AesEncryptionService aesEncryption;
	private final RandomImageService randomImageService;
	private final RandomWordService randomWordService;
	private final RandomTextUserRepository randomTextUserRepository;
	

	public AuthService(
			UserRepository userRepository, 
			AuthenticationManager authenticationManager, 
			JwtIssuer jwtIssuer, 
			TokenService tokenService, 
			PasswordEncoder passwordEncoder, 
			MailService mailService, 
			AesEncryptionService aesEncryption, 
			RandomImageService randomImageService, 
			RandomWordService randomWordService, 
			RandomTextUserRepository randomTextUserRepository) {
		super();
		this.mailService = mailService;
		this.passwordEncoder = passwordEncoder;		
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.tokenService = tokenService;
		this.jwtIssuer = jwtIssuer;
		this.aesEncryption = aesEncryption;
		this.randomImageService = randomImageService;
		this.randomWordService = randomWordService;
		this.randomTextUserRepository = randomTextUserRepository;
	}
	
	public RegisterEmailResponse registerMailingLink(RegisterEmailRequest request) {
		try {
			String user = User.builder().withId(1).build().toString();
			
			String[] imageName = new String[] {"avion", "ciseau", "poile"};
			
			//String encryptData = aesEncryption.encrypt(imageName);
			String encryptData = aesEncryption.encryptArray(imageName);
			String decryptData = aesEncryption.decrypt(encryptData);
			String[] data = decryptData.split(",");
			
			System.out.println(encryptData);
			System.out.println(decryptData);
			System.out.println(data[1]);
			
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
		
		//mailService.sendEmail(request.getSubject(), request.getRecipientMail());			
		return RegisterEmailResponse.builder().withMessage("ddd").build();
	}

	
	public RegisterEmailResponse registerEmail(RegisterEmailRequest request) {
		try {
			// Vérifier que les données atttendu sont présentes
			
			// Vérifier si selection image utilisateur correcte 
			if(!randomImageService.isUserImageSelectionValid(request.getRegisterId(), request.getRegisterRandomText(), request.getSelectedImageRandomWords())) throw new RuntimeException("selection image invalide");
			
			// si ok: vérifier l'existrence de l'email en base de données
			userRepository.findUserByEmail(request.getEmail()).ifPresent(user->{
				throw new UserFindException("cet email est déja utilisé");
			});
			
			// si ok; enregistrer l'email
			UserEntity user = UserEntity.builder().withEmail(request.getEmail()).build();		
			int userId = userRepository.saveUser(user);
			
			// générer code de confirmation de 5 lettres en claire et chiffré
			String randomString = randomWordService.generateRandom(5);		
			EncryptRandomWordResponse encryptedRandomString = randomWordService.encryptRandomWord(randomString);
			
			// générer un code de 45 lettres chiffrés
			EncryptRandomWordResponse emailConfirmationString = randomWordService.encryptRandomWord(randomWordService.generateRandom(45));
			
			// save mot chiffré et info du mail en bdd
			randomWordService.saveEncryptedWord(userId, encryptedRandomString.getEncryptRandomWord(), encryptedRandomString.getIvString(), RandomCategory.REGISTER);
			randomWordService.saveEncryptedWord(userId, emailConfirmationString.getEncryptRandomWord(), emailConfirmationString.getIvString(), RandomCategory.EMAILCONFIRMATION);
			
			Map <String, String> replaceWords = new HashMap<>();			
			replaceWords.put("token", randomString);
			replaceWords.put("email", request.getEmail());
			replaceWords.put("link", "auth/create-account/user/"+ request.getEmail() + "/confirmation/" + emailConfirmationString.getEncryptRandomWord());
			
			// envoyer un l'email
			mailService.sendEmail(RegisterMailingRequest.builder()
					.withWordsToReplaceInHtmlTemplate(replaceWords)
					.withEmail(request.getEmail())
					.withEmailSubject(EmailSubject.REGISTER).build());
			
			//envoyer la réponse au client			
			RegisterEmailResponse response = RegisterEmailResponse.builder()
					.withMessage("votre inscription est confirmée")
					.withUserId(userId)
					.build();
			
			return response;
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException e) {
			return null;
		} 
		
	}
	
	public CreateAccountResponse createAccount(CreateAccountRequest request) {
		return null;
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
