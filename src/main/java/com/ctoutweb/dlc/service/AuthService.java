package com.ctoutweb.dlc.service;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.entity.AccountEntity;
import com.ctoutweb.dlc.entity.UserEntity;
import com.ctoutweb.dlc.exception.custom.CreateAccountException;
import com.ctoutweb.dlc.exception.custom.EmailException;
import com.ctoutweb.dlc.exception.custom.EncryptionException;
import com.ctoutweb.dlc.exception.custom.UserFindException;
import com.ctoutweb.dlc.model.Account;
import com.ctoutweb.dlc.model.RandomTextUser;
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
import com.ctoutweb.dlc.repository.AccountRepository;
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
	private final AccountRepository accountRepository;
	

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
			RandomTextUserRepository randomTextUserRepository, 
			AccountRepository accountRepository) {
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
		this.accountRepository = accountRepository;
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
			EncryptRandomWordResponse encryptedRandomString = randomWordService.encryptRandomWord(randomString, false);
			
			// générer un code de 45 lettres chiffrés
			EncryptRandomWordResponse emailConfirmationString = randomWordService.encryptRandomWord(randomWordService.generateRandom(45), true);
			
			// save mot chiffré et info du mail en bdd
			randomWordService.saveEncryptedRandomWord(userId, encryptedRandomString.getEncryptRandomWord(), encryptedRandomString.getIvString(), RandomCategory.REGISTEREMAILTOKEN);
			randomWordService.saveEncryptedRandomWord(userId, emailConfirmationString.getEncryptRandomWord(), emailConfirmationString.getIvString(), RandomCategory.URLTOKEN);
			
			Map <String, String> listWordsToReplaceInHtmlTemplate = new HashMap<>();			
			listWordsToReplaceInHtmlTemplate.put("token", randomString);
			listWordsToReplaceInHtmlTemplate.put("email", request.getEmail());
			listWordsToReplaceInHtmlTemplate.put("link", "auth/create-account/user/"+ request.getEmail() + "/confirmation/" + emailConfirmationString.getEncryptRandomWord());
			
			// envoyer un l'email
			mailService.sendEmail(RegisterMailingRequest.builder()
					.withWordsToReplaceInHtmlTemplate(listWordsToReplaceInHtmlTemplate)
					.withEmail(request.getEmail())
					.withEmailSubject(EmailSubject.REGISTER).build());
			
			//envoyer la réponse au client			
			RegisterEmailResponse response = RegisterEmailResponse.builder()
					.withMessage("vous allez recevoir un email pour terminer votre inscription")
					.withUserId(userId)
					.build();
			
			return response;
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | 				InvalidKeySpecException e) {
			userRepository.deleteByEmail(request.getEmail());
			throw new EncryptionException();
		}	catch(EmailException e) {
			userRepository.deleteByEmail(request.getEmail());
			throw new EmailException("erreur lors de l'envoie de l'email d'inscription");
		}
	}
	
	public CreateAccountResponse createAccount(CreateAccountRequest request) {
		try {
			
			
			// récupérer les infos sur l'utilisateur en bdd (token email + token registerurl)
			User user = userRepository.findUserByEmail(request.getEmail()).orElseThrow(()->new CreateAccountException("impossible de vérifier le code de confirmation"));
			
			// Vérification si compte déja créé
			if(user.getIsAccountCreated()) throw new CreateAccountException("votre compte est déja créé");
				
			// Récupération des randomTexts
			List<RandomTextUser> randomTextUser = user.getRandomTexts();
			
			// vérifier le 'encryptedRandomURLToken	
			this.createAccountHelper(randomTextUser, RandomCategory.URLTOKEN, true, request.getUrlToken(), true);
			
			// Vérifier Token de 5 characteres
			this.createAccountHelper(randomTextUser, RandomCategory.REGISTEREMAILTOKEN, false, request.getToken(), false);			
						
			// créer le compte
			accountRepository.findAccountByUserId(user.getId()).ifPresent(account->accountRepository.deleteAccountByUserId(user.getId()));
			accountRepository.saveAccount(AccountEntity.builder()
					.withPassword(passwordEncoder.encode(request.getPassword()))
					.withUserId(user.getId())
					.build());
			
			// Mise a jour de user
			UserEntity userEntity = UserEntity.builder()
					.withId(user.getId())
					.withUpdatedAt(new Date())
					.withIsAccountCreated(true)
					.build();
			
			userRepository.updateUserByUserId(userEntity);
			
			// supprimer les données dans random table
			randomTextUserRepository.deleteByUserId(user.getId());
			
			// renvoyer la réponse
			CreateAccountResponse response = CreateAccountResponse.builder().withMessage("votre compte est créé").build();
			return response;
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | 				InvalidKeySpecException e) {
			//userRepository.deleteByEmail(request.getEmail());
			e.printStackTrace();
			throw new EncryptionException(e.getMessage());
		} catch(RuntimeException ex) {
			throw new CreateAccountException(ex.getMessage());
		}
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
	
	private void createAccountHelper(List<RandomTextUser> randomTextUser, RandomCategory randomCategory, boolean isRandomWordEncrypted, String clientToken, boolean isUrlBase64) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
		// vérifier le 'encryptedRandomURLToken			
		RandomTextUser token = randomTextUser.stream()
				.filter(random->random.getCategoryId() == randomCategory.getIndex())
				.findFirst()
				.orElseThrow(()-> new CreateAccountException("impossible de vérifier le code de confirmation"));
		
		// Vérifier expiredAt
		if(token.getExpiredAt().compareTo(new Date()) < 0) throw new CreateAccountException("le delais de création d'un compte est expiré. Merci de vous réinscrire");
		
		//Vérification urlToken  avec le code présent en base de donnée
		if(isRandomWordEncrypted) {
			if(!randomWordService.isEncryptedRandomWordValid(clientToken, token.getRandomText(), token.getIv(), isUrlBase64)) throw new CreateAccountException("le code de confirmation n'est pas correcte");			
		} else {
			if(!randomWordService.isDecryptedRandomWordValid(clientToken, token.getRandomText(), token.getIv(), isUrlBase64)) throw new CreateAccountException("le code de confirmation n'est pas correcte");
		}
	}
	
}
