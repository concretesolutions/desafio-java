package com.khalilpan;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

//este classe pode ser usado para criar hashed senha //this class will be used to create hashed passwords
public class HashingPasswordWithPBKDF2 {

	// constructor private
	private HashingPasswordWithPBKDF2() {}

	private static HashingPasswordWithPBKDF2 hashingPasswordWithPBKDF2;

	// to Hashing
	public String toHash(String passwordToHash) {

		String generatedSecuredPasswordHash;

		try {
			generatedSecuredPasswordHash = generateStorngPasswordHash(passwordToHash);
			return generatedSecuredPasswordHash;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
//	        System.out.println(generatedSecuredPasswordHash);

		// ====validation======

//	        boolean matched = validatePassword("khalil123", generatedSecuredPasswordHash);
//	        System.out.println(matched); // ------> True
//	         
//	        matched = validatePassword("khalil122", generatedSecuredPasswordHash);
//	        System.out.println(matched); // -----> false
	}

	public Boolean toComparePasswords(String newPasswordToCompare, String existedHashedPasswordToCompareWith) {

		Boolean result;

		try {
			result = validatePassword(newPasswordToCompare, existedHashedPasswordToCompareWith);
			return result;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	// to get instance
	public static HashingPasswordWithPBKDF2 getInstance() {
		if (hashingPasswordWithPBKDF2 == null) {
			hashingPasswordWithPBKDF2 = new HashingPasswordWithPBKDF2();
			return hashingPasswordWithPBKDF2;
		} else {
			return hashingPasswordWithPBKDF2;
		}
	}

	// =========================hashing
	// methods==============================================
	private static String generateStorngPasswordHash(String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		int iterations = 1000;
		char[] chars = password.toCharArray();
		byte[] salt = getSalt();

		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = skf.generateSecret(spec).getEncoded();
		return iterations + ":" + toHex(salt) + ":" + toHex(hash);
	}

	private static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	private static String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	// ==================validation methods====================

	private static boolean validatePassword(String originalPassword, String storedPassword)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		String[] parts = storedPassword.split(":");
		int iterations = Integer.parseInt(parts[0]);
		byte[] salt = fromHex(parts[1]);
		byte[] hash = fromHex(parts[2]);

		PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] testHash = skf.generateSecret(spec).getEncoded();

		int diff = hash.length ^ testHash.length;
		for (int i = 0; i < hash.length && i < testHash.length; i++) {
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0;
	}

	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}

}
