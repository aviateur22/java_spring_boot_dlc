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

import com.ctoutweb.dlc.entity.RandomTextUserEntity;
import com.ctoutweb.dlc.model.SaveEncryptedRandomWord;
import com.ctoutweb.dlc.model.encryption.EncryptRandomWordResponse;
import com.ctoutweb.dlc.repository.RandomTextUserRepository;
import com.ctoutweb.dlc.service.AesEncryptionService;

@Service
public class RandomWordService {
	
	private final AesEncryptionService aesEncyptionService;
	private final RandomTextUserRepository randomTextUserRepository;
			
	public RandomWordService(AesEncryptionService aesEncyptionService, RandomTextUserRepository randomTextUserRepository) {
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
	
	public void saveEncryptedRandomWord(SaveEncryptedRandomWord encryptedRandomWord) {
		
		randomTextUserRepository.findByUserIdAndCategoryId(encryptedRandomWord.getUserId(), encryptedRandomWord.getRandomCategory().getIndex()).ifPresent(data->randomTextUserRepository.delete(data.getId()));			
	
		RandomTextUserEntity randomTextUserEntity = RandomTextUserEntity.builder()
		.withCategoryId(encryptedRandomWord.getRandomCategory().getIndex())
		.withIv(encryptedRandomWord.getEncryptedRandomWord().getIvString())
		.withRandomText(encryptedRandomWord.getEncryptedRandomWord().getEncryptRandomWord())
		.withUserId(encryptedRandomWord.getUserId())
		.build();
		
		randomTextUserRepository.save(randomTextUserEntity);
	}
	
	public boolean isDecryptedRandomWordValid(String decryptedRandomWordFromClient, String encryptedRandomWordFromDatabase, String ivFromDatabase, boolean isUrlBase64) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {		
		String decryptRandomWordFromDatabase = decryptRandomWord(encryptedRandomWordFromDatabase, ivFromDatabase, isUrlBase64);		
		return decryptedRandomWordFromClient.equals(decryptRandomWordFromDatabase);
	}
	
	public boolean isEncryptedRandomWordValid(String encryptedRandomWordFromClient, String encryptedRandomWordFromDatabase, String ivFromDatabase, boolean isUrlBase64) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
		String decryptRandomWordFromClient = decryptRandomWord(encryptedRandomWordFromClient, ivFromDatabase, isUrlBase64);
		String decryptRandomWordFromDatabase = decryptRandomWord(encryptedRandomWordFromDatabase, ivFromDatabase, isUrlBase64);
		
		return decryptRandomWordFromClient.equals(decryptRandomWordFromDatabase);
	}
	
	private String decryptRandomWord(String encryptedRandomWord, String ivString, boolean isUrlBase64) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
		byte[] iv = Base64.getDecoder().decode(ivString.getBytes());
		String decryptRandomString = aesEncyptionService.decrypt(encryptedRandomWord, iv, isUrlBase64);
		return decryptRandomString;
	}
	
	
	
}
