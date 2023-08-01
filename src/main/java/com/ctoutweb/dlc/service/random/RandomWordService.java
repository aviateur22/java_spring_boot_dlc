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
	

	
	public EncryptRandomWordResponse encryptRandomWord(String text) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
		byte[] iv = aesEncyptionService.generateRandomByte();
		String ivToString = Base64.getEncoder().encodeToString(iv);
		String encryptRandomWord = aesEncyptionService.encrypt(text, iv);		
		return EncryptRandomWordResponse.builder().withEncryptRandomWord(encryptRandomWord).withIvString(ivToString).build();
	}
	
	public void saveEncryptedRandomWord(int userId, String EncryptedRandomWord, String iv, RandomCategory randomCategory) {
		
		randomTextUserRepository.findByUserIdAndCategoryId(userId, randomCategory.getIndex()).ifPresent(data->randomTextUserRepository.delete(data.getId()));			
	
		RandomTextUserEntity randomTextUserEntity = RandomTextUserEntity.builder()
		.withCategoryId(randomCategory.getIndex())
		.withIv(iv)
		.withUserId(userId)
		.withRandomText(EncryptedRandomWord)
		.build();
		
		randomTextUserRepository.save(randomTextUserEntity);
	}
	
	public boolean isDecryptedRandomWordValid(String decryptedRandomWordFromClient, String encryptedRandomWordFromDatabase, String ivFromDatabase) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {		
		String decryptRandomWordFromDatabase = decryptRandomWord(encryptedRandomWordFromDatabase, ivFromDatabase);		
		System.out.println("debut");
		System.out.println(decryptedRandomWordFromClient);
		System.out.println(encryptedRandomWordFromDatabase);
		System.out.println(decryptRandomWordFromDatabase);
		System.out.println(decryptedRandomWordFromClient.equals(decryptRandomWordFromDatabase));
		System.out.println("Fin");
		return decryptedRandomWordFromClient.equals(decryptRandomWordFromDatabase);
	}
	
	public boolean isEncryptedRandomWordValid(String encryptedRandomWordFromClient, String encryptedRandomWordFromDatabase, String ivFromDatabase) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
		String decryptRandomWordFromClient = decryptRandomWord(encryptedRandomWordFromClient, ivFromDatabase);
		String decryptRandomWordFromDatabase = decryptRandomWord(encryptedRandomWordFromDatabase, ivFromDatabase);
		
		return decryptRandomWordFromClient.equals(decryptRandomWordFromDatabase);
	}
	
	private String decryptRandomWord(String encryptedRandomWord, String ivString) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
		byte[] iv = Base64.getDecoder().decode(ivString.getBytes());
		String decryptRandomString = aesEncyptionService.decrypt(encryptedRandomWord, iv);
		return decryptRandomString;
	}
	
	
	
}
