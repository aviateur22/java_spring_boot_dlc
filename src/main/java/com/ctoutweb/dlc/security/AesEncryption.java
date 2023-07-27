package com.ctoutweb.dlc.security;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.ctoutweb.dlc.properties.AesProperties;

@Component
public class AesEncryption {
	
	private final AesProperties aesProperties;
	String algorithm = "AES/CBC/PKCS5Padding";
	String salt = "fdgfggbhh";
	IvParameterSpec iv = generateIv();
    
    public AesEncryption(AesProperties aesProperties) {
		super();
		this.aesProperties = aesProperties;
	}
	
	private SecretKey generateSecretKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	    KeySpec spec = new PBEKeySpec(aesProperties.getKey().toCharArray(), salt.getBytes(), 65536, 256);
	    SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
	        .getEncoded(), "AES");
	    return secret;
	}
	
	private IvParameterSpec generateIv() {
	    byte[] iv = new byte[16];
	    new SecureRandom().nextBytes(iv);
	    return new IvParameterSpec(iv);
	}
	
    

	public String encrypt(String text) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
		Cipher cipher = Cipher.getInstance(this.algorithm);
		SecretKey key = this.generateSecretKey();
		System.out.println(iv);
		cipher.init(Cipher.ENCRYPT_MODE,key, this.iv);
		byte[] cipherText = cipher.doFinal(text.getBytes());		
	    return Base64.getEncoder()
	        .encodeToString(cipherText);
		
	}
	
	public String decrypt(String cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException,
		    InvalidAlgorithmParameterException, InvalidKeyException,
		    BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
		
			SecretKey key = this.generateSecretKey();
			System.out.println(iv);
		    Cipher cipher = Cipher.getInstance(algorithm);
		    cipher.init(Cipher.DECRYPT_MODE, key, this.iv);
		    byte[] plainText = cipher.doFinal(Base64.getDecoder()
		        .decode(cipherText));
		    return new String(plainText);
	}
}
