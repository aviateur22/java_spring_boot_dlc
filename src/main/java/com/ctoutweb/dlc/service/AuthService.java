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
import com.ctoutweb.dlc.exception.custom.ActivateAccountException;
import com.ctoutweb.dlc.exception.custom.CreateAccountException;
import com.ctoutweb.dlc.exception.custom.EmailException;
import com.ctoutweb.dlc.exception.custom.EncryptionException;
import com.ctoutweb.dlc.exception.custom.TokenException;
import com.ctoutweb.dlc.exception.custom.UserFindException;
import com.ctoutweb.dlc.model.RandomConfirmationToken;
import com.ctoutweb.dlc.model.SaveEncryptedRandomToken;
import com.ctoutweb.dlc.model.TokenIssue;
import com.ctoutweb.dlc.model.User;
import com.ctoutweb.dlc.model.VerifyClientToken;
import com.ctoutweb.dlc.model.auth.ActivateAccountRequest;
import com.ctoutweb.dlc.model.auth.ActivateAccountResponse;
import com.ctoutweb.dlc.model.auth.CreateAccountRequest;
import com.ctoutweb.dlc.model.auth.CreateAccountResponse;
import com.ctoutweb.dlc.model.auth.LoginRequest;
import com.ctoutweb.dlc.model.auth.LoginResponse;
import com.ctoutweb.dlc.model.auth.LogoutResponse;
import com.ctoutweb.dlc.model.auth.RegisterMailing;
import com.ctoutweb.dlc.model.encryption.EncryptRandomWordResponse;
import com.ctoutweb.dlc.model.auth.RegisterEmailRequest;
import com.ctoutweb.dlc.model.auth.RegisterEmailResponse;
import com.ctoutweb.dlc.repository.AccountRepository;
import com.ctoutweb.dlc.repository.RandomTokenUserRepository;
import com.ctoutweb.dlc.repository.UserRepository;
import com.ctoutweb.dlc.security.authentication.UserPrincipal;
import com.ctoutweb.dlc.security.token.JwtIssuer;
import com.ctoutweb.dlc.service.mail.EmailSubject;
import com.ctoutweb.dlc.service.mail.MailService;
import com.ctoutweb.dlc.service.random.RandomImageService;
import com.ctoutweb.dlc.service.random.RandomTokenCategory;
import com.ctoutweb.dlc.service.random.RandomTokenService;

@Service
public class AuthService {	
	
	private final MailService mailService;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	private final JwtIssuer jwtIssuer;
	private final RandomImageService randomImageService;
	private final RandomTokenService randomTokenService;
	private final RandomTokenUserRepository randomTokenUserRepository;
	private final AccountRepository accountRepository;
	

	public AuthService(
			UserRepository userRepository, 
			AuthenticationManager authenticationManager, 
			JwtIssuer jwtIssuer, 
			TokenService tokenService, 
			PasswordEncoder passwordEncoder, 
			MailService mailService, 			
			RandomImageService randomImageService, 
			RandomTokenService randomTokenService, 
			RandomTokenUserRepository randomTokenUserRepository, 
			AccountRepository accountRepository) {
		super();
		this.mailService = mailService;
		this.passwordEncoder = passwordEncoder;		
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.tokenService = tokenService;
		this.jwtIssuer = jwtIssuer;
		this.randomImageService = randomImageService;
		this.randomTokenService = randomTokenService;
		this.randomTokenUserRepository = randomTokenUserRepository;
		this.accountRepository = accountRepository;
	}
	
	public RegisterEmailResponse registerEmail(RegisterEmailRequest request) {
		try {
			// Vérifier si selection image utilisateur correcte 
			if(!randomImageService.isUserImageSelectionValid(request.getRegisterId(), request.getRegisterRandomText(), request.getSelectedImageRandomWords())) throw new RuntimeException("selection image invalide");
			
			// si ok: vérifier l'existrence de l'email en base de données
			userRepository.findUserByEmail(request.getEmail()).ifPresent(user->{
				// On valide un email utilisé uniquement si le compte n'est pas créé ou
				if(user.getIsAccountCreated() && user.getAccount().getMaximumAccountActivationDate().compareTo(new Date()) > 0) throw new UserFindException("cet email est déja utilisé");
				
				// Suppression de l'ancien email
				userRepository.deleteByEmail(request.getEmail());
			});
			
			// si ok; enregistrer l'email
			UserEntity user = UserEntity.builder().withEmail(request.getEmail()).build();		
			int userId = userRepository.saveUser(user);
			
			// générer code de confirmation de 5 lettres en claire et chiffré
			String randomString = randomTokenService.generateRandom(5);		
			EncryptRandomWordResponse encryptedRandomString = randomTokenService.encryptRandomWord(randomString, false);
			
			// générer un code de 45 lettres chiffrés
			EncryptRandomWordResponse emailConfirmationString = randomTokenService.encryptRandomWord(randomTokenService.generateRandom(45), true);
			
			SaveEncryptedRandomToken saveEncryptedRandomString = SaveEncryptedRandomToken.builder()
					.withUserId(userId)
					.withEncryptedRandomWord(encryptedRandomString)
					.withRandomTokenCategory(RandomTokenCategory.REGISTEREMAILTOKEN)
					.build();
			
			SaveEncryptedRandomToken saveEmailConfirmationString = SaveEncryptedRandomToken.builder()
					.withUserId(userId)
					.withEncryptedRandomWord(emailConfirmationString)
					.withRandomTokenCategory(RandomTokenCategory.URLTOKEN)
					.build();
			
			// save mot chiffré et info du mail en bdd
			randomTokenService.saveEncryptedRandomWord(saveEncryptedRandomString);
			randomTokenService.saveEncryptedRandomWord(saveEmailConfirmationString);
			
			Map <String, String> listWordsToReplaceInHtmlTemplate = new HashMap<>();			
			listWordsToReplaceInHtmlTemplate.put("token", randomString);
			listWordsToReplaceInHtmlTemplate.put("email", request.getEmail());
			listWordsToReplaceInHtmlTemplate.put("link", "http://localhost:4200/auth/create-account/user/"+ request.getEmail() + "/confirmation/" + emailConfirmationString.getEncryptRandomWord());
			
			// envoyer un l'email
			mailService.sendEmail(RegisterMailing.builder()
					.withWordsToReplaceInHtmlTemplate(listWordsToReplaceInHtmlTemplate)
					.withEmail(request.getEmail())
					.withEmailSubject(EmailSubject.REGISTER)
					.withExceptionMessage("erreur lors de l'envoie de l'email d'inscription")
					.build());
			
			//envoyer la réponse au client			
			RegisterEmailResponse response = RegisterEmailResponse.builder()
					.withMessage("vous allez recevoir un email pour terminer votre inscription")
					.withUserId(userId)
					.build();
			
			return response;
		} catch (IllegalArgumentException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | 				InvalidKeySpecException e) {
			userRepository.deleteByEmail(request.getEmail());
			throw new EncryptionException();
		}	catch(EmailException e) {
			userRepository.deleteByEmail(request.getEmail());
			throw new EmailException("erreur lors de l'envoie de l'email d'inscription, merci de recommencer");
		}
	}	
	
	public CreateAccountResponse createAccount(CreateAccountRequest request) {
		
		User user;		
		User userCopy = null;		
		try {			
			// récupérer les infos sur l'utilisateur en bdd (token email + token registerurl)
			user = userRepository.findUserByEmail(request.getEmail()).orElseThrow(()->new CreateAccountException("impossible de vérifier le code de confirmation"));
			userCopy = new User(user);
			
			// Vérification si compte déja créé
			if(user.getIsAccountCreated()) throw new CreateAccountException("votre compte est déja créé");
				
			// Récupération des randomTexts
			List<RandomConfirmationToken> userRandomConfirmationTokens = user.getRandomConfirmationTokens();
			
			// vérifier le 'encryptedRandomURLToken	
			this.verifyClientTokenHelper(VerifyClientToken.builder() 
					.withUserRandomConfirmationTokens(userRandomConfirmationTokens)
					.withRandomTokenCategory(RandomTokenCategory.URLTOKEN)
					.withIsTokenEncrypted(true)
					.withClientToken(request.getUrlToken())
					.withIsBase64Url(true)
					.build());
			
			// Vérifier Token de 5 characteres
			this.verifyClientTokenHelper(VerifyClientToken.builder() 
					.withUserRandomConfirmationTokens(userRandomConfirmationTokens)
					.withRandomTokenCategory(RandomTokenCategory.REGISTEREMAILTOKEN)
					.withIsTokenEncrypted(false)
					.withClientToken(request.getToken())
					.withIsBase64Url(false)
					.build());						
			
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
			randomTokenUserRepository.deleteByUserId(user.getId());
			
			// générer un token pour activation account de 65 lettres chiffrés
			EncryptRandomWordResponse activationAccountToken = randomTokenService.encryptRandomWord(randomTokenService.generateRandom(65), true);
			
			SaveEncryptedRandomToken encryptedRandomWord = SaveEncryptedRandomToken.builder()
					.withUserId(user.getId())
					.withEncryptedRandomWord(activationAccountToken)
					.withRandomTokenCategory(RandomTokenCategory.ACTIVATEACCOUNTTOKEN)
					.build();
			
			// save mot chiffré et info du mail en bdd			
			randomTokenService.saveEncryptedRandomWord(encryptedRandomWord);
			
			Map <String, String> listWordsToReplaceInHtmlTemplate = new HashMap<>();			
			listWordsToReplaceInHtmlTemplate.put("email", user.getEmail());
			listWordsToReplaceInHtmlTemplate.put("link", "http://localhost:4200/auth/account-activation/user/"+ user.getEmail() + "/confirmation/" + activationAccountToken.getEncryptRandomWord());
			
			//Envoi d'un email d'activation de compte		
			mailService.sendEmail(RegisterMailing.builder()
					.withWordsToReplaceInHtmlTemplate(listWordsToReplaceInHtmlTemplate)
					.withEmail(user.getEmail())
					.withEmailSubject(EmailSubject.ACTIVATEACCOUNT)
					.withExceptionMessage("erreur lors de l'envoie de l'email d'activation de compte")
					.build());			
			
			// renvoyer la réponse
			CreateAccountResponse response = CreateAccountResponse.builder().withMessage("félicitation votre compte est créé, vous allez recevoir un email afin de pouvoir l'activer").build();
			
			return response;
			
		} catch (IllegalArgumentException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException e) {
			e.printStackTrace();			
			throw new EncryptionException("le code de confirmation n'est pas correcte");
		} catch(RuntimeException ex) {
			ex.printStackTrace();
			
			if(ex instanceof EmailException) {
				resetUserDataToStepCreateAccount(userCopy);
				throw new EmailException("erreur lors de l'envoie de l'email d'activation de compte, merci de recommencer");
			}			
			throw new CreateAccountException(ex.getMessage());
		}
	}

	public ActivateAccountResponse accountActivation(ActivateAccountRequest request) {
		User user;		
		try {
		// récupérer les infos sur l'utilisateur en bdd (token email + token registerurl)
		user = userRepository.findUserByEmail(request.getEmail()).orElseThrow(()->new ActivateAccountException("impossible d'activer votre compte"));

		if(user.getAccount() == null) throw new ActivateAccountException("impossible d'activer votre compte");

		if(user.getAccount() != null  && user.getAccount().getIsAccountActive()) throw new ActivateAccountException("votre compte est déja activé");

		// Récupération des randomTexts
		List<RandomConfirmationToken> userRandomConfirmationTokens = user.getRandomConfirmationTokens();
		
		// vérifier le 'encryptedRandomURLToken	
		this.verifyClientTokenHelper(VerifyClientToken.builder() 
				.withUserRandomConfirmationTokens(userRandomConfirmationTokens)
				.withRandomTokenCategory(RandomTokenCategory.ACTIVATEACCOUNTTOKEN)
				.withIsTokenEncrypted(true)
				.withClientToken(request.getActivationToken())
				.withIsBase64Url(true)
				.build());
		
		AccountEntity account = AccountEntity.builder()
				.withUserId(user.getId())
				.withAccountActivationAt(new Date())
				.withIsAccountActive(true)
				.withUpdatedAt(new Date())
				.build();
		
		accountRepository.updateAccountByUserId(account);
		
		// supprimer les données dans random table
		randomTokenUserRepository.deleteByUserId(user.getId());
		
		return ActivateAccountResponse.builder().withMessage("félicitation votre compte est activé").build();
		
		} catch (IllegalArgumentException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
			throw new EncryptionException("le code de confirmation n'est pas correcte");
		} catch(RuntimeException ex) {
			ex.printStackTrace();
			throw new ActivateAccountException(ex.getMessage());
		}		
	}
	
	public LoginResponse authenticate(LoginRequest request) {		
		Authentication auth =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));		
		SecurityContextHolder.getContext().setAuthentication(auth);		
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
		
		// gestion du token a jouter en bdd
		TokenIssue tokenIssue = jwtIssuer.issue(userPrincipal);		
		tokenService.saveToken(userPrincipal.getId(), tokenIssue);
		
		return new LoginResponse.LoginResponseBuilder()
				.withMessage("bonjour " + request.getEmail())
				.withToken(tokenIssue.getToken())
				.withRoles(userPrincipal.getAuthorities().stream().map(x->x.getAuthority().toString()).collect(Collectors.toList()))
				.withEmail(request.getEmail())
				.withId(userPrincipal.getId())
				.build();
	}
	public LogoutResponse logout(int userId) {
		tokenService.deleteToken(userId);
		return LogoutResponse.builder().withMessage("à bientôt").build();
	}

	private void verifyClientTokenHelper(VerifyClientToken verifyClientToken) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
		// vérifier le 'encryptedRandomURLToken			
		RandomConfirmationToken tokenFromDatabase = verifyClientToken.getUserRandomConfirmationTokens()
				.stream()
				.filter(random->random.getCategoryId() == verifyClientToken.getRandomTokenCategory().getIndex())
				.findFirst()
				.orElseThrow(()-> new TokenException("impossible de vérifier le code de confirmation"));
		
		verifyClientToken.setTokenFromDatabase(tokenFromDatabase);
		// Vérifier expiredAt
		if(tokenFromDatabase.getExpiredAt().compareTo(new Date()) < 0) throw new TokenException("temps d'activation dépassé, merci de vous réinscrire");
		
		//Vérification urlToken  avec le code présent en base de donnée
		if(verifyClientToken.getIsTokenEncrypted()) {
			if(!randomTokenService.isEncryptedRandomTokenValid(verifyClientToken)) throw new TokenException("le code de confirmation n'est pas correcte");			
		} else {
			if(!randomTokenService.isDecryptedRandomTokenValid(verifyClientToken)) throw new TokenException("le code de confirmation n'est pas correcte");
		}
	}
	
	private void resetUserDataToStepCreateAccount(User user) {
		
		// supprimer les données dans randomToken table
		if(randomTokenUserRepository.findByUserI(user.getId()).size() > 0) randomTokenUserRepository.deleteByUserId(user.getId());
		
		// Mise a jour des données utilisateur
		UserEntity userEntity = UserEntity.builder()
				.withId(user.getId())
				.withUpdatedAt(new Date())
				.withIsAccountCreated(false)
				.build();		
		userRepository.updateUserByUserId(userEntity);
		
		// Rajout des randomToken initiaux 
		user.getRandomConfirmationTokens().forEach(token->{			
			SaveEncryptedRandomToken encryptedToken = SaveEncryptedRandomToken.builder()
					.withUserId(user.getId())
					.withRandomTokenCategory(RandomTokenCategory.valueOf(token.getCategory()))
					.withEncryptedRandomWord(EncryptRandomWordResponse.builder()
							.withEncryptRandomWord(token.getToken())
							.withIvString(token.getIv())
							.build())
					.build();
			randomTokenService.saveEncryptedRandomWord(encryptedToken);
		});
	}
}
