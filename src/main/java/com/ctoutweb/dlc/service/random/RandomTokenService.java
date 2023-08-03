package com.ctoutweb.dlc.service.random;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.stereotype.Service;

import com.ctoutweb.dlc.entity.RandomTokenUserEntity;
import com.ctoutweb.dlc.model.SaveEncryptedRandomToken;
import com.ctoutweb.dlc.model.VerifyClientToken;
import com.ctoutweb.dlc.model.encryption.EncryptRandomWordResponse;
import com.ctoutweb.dlc.repository.RandomTokenUserRepository;
import com.ctoutweb.dlc.service.AesEncryptionService;

@Service
public class RandomTokenService {
	
	private final AesEncryptionService aesEncyptionService;
	private final RandomTokenUserRepository randomTextUserRepository;
			
	public RandomTokenService(AesEncryptionService aesEncyptionService, RandomTokenUserRepository randomTextUserRepository) {
		super();
		this.aesEncyptionService = aesEncyptionService;
		this.randomTextUserRepository = randomTextUserRepository;
	}

	public String generateRandom(int wordLength) {
		Random rand = new Random();
		String str = rand.ints(48, 123)
				.filter(num->(num < 58 || num > 64) && (num < 91 || num > 96))
				.limit(wordLength)
				.mapToObj(c->(char) c).collect(StringBuffer::new , StringBuffer::append, StringBuffer::append)
				.toString();		
		return str.toLowerCase();
	}
	
	public EncryptRandomWordResponse encryptRandomWord(String text, boolean isUrlBase64) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
		byte[] iv = aesEncyptionService.generateRandomByte();
		String ivToString = Base64.getEncoder().encodeToString(iv);
		String encryptRandomWord = aesEncyptionService.encrypt(text, iv, isUrlBase64);		
		return EncryptRandomWordResponse.builder().withEncryptRandomWord(encryptRandomWord).withIvString(ivToString).build();
	}
	
	public void saveEncryptedRandomWord(SaveEncryptedRandomToken encryptedRandomWord) {
		
		randomTextUserRepository.findByUserIdAndCategoryId(encryptedRandomWord.getUserId(), encryptedRandomWord.getRandomTokenCategory().getIndex()).ifPresent(data->randomTextUserRepository.delete(data.getId()));			
	
		RandomTokenUserEntity randomTextUserEntity = RandomTokenUserEntity.builder()
		.withCategoryId(encryptedRandomWord.getRandomTokenCategory().getIndex())
		.withIv(encryptedRandomWord.getEncryptedRandomWord().getIvString())
		.withRandomText(encryptedRandomWord.getEncryptedRandomWord().getEncryptRandomWord())
		.withUserId(encryptedRandomWord.getUserId())
		.build();
		
		randomTextUserRepository.save(randomTextUserEntity);
	}
	
	public boolean isDecryptedRandomTokenValid(VerifyClientToken tokenClientToVerify) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {		
		String decryptTokenFromDatabase = decryptToken(
				tokenClientToVerify.getTokenFromDatabase().getToken(), 
				tokenClientToVerify.getTokenFromDatabase().getIv(), 
				tokenClientToVerify.getIsBase64Url());		
		return tokenClientToVerify.getClientToken().equals(decryptTokenFromDatabase);
	}
	
	public boolean isEncryptedRandomTokenValid(VerifyClientToken tokenClientToVerify) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
		//String encryptedRandomWordFromClient, String encryptedRandomWordFromDatabase, String ivFromDatabase, boolean isUrlBase6
		String decryptTokenFromClient = decryptToken(
				tokenClientToVerify.getClientToken(),
				tokenClientToVerify.getTokenFromDatabase().getIv(),
				tokenClientToVerify.getIsBase64Url());
		
		String decryptTokenFromDatabase = decryptToken(
				tokenClientToVerify.getTokenFromDatabase().getToken(),
				tokenClientToVerify.getTokenFromDatabase().getIv(),
				tokenClientToVerify.getIsBase64Url());
		
		return decryptTokenFromClient.equals(decryptTokenFromDatabase);
	}
	
	private String decryptToken(String encryptedRandomWord, String ivString, boolean isUrlBase64) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
		byte[] iv = Base64.getDecoder().decode(ivString.getBytes());
		String decryptRandomString = aesEncyptionService.decrypt(encryptedRandomWord, iv, isUrlBase64);
		return decryptRandomString;
	}
	
	
	
}
