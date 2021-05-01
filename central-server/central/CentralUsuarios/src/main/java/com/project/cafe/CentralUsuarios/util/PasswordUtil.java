package com.project.cafe.CentralUsuarios.util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordUtil {

	public static String getSalt(int length, String clave) {
		return clave + length + ConstantesValidaciones.LLAVE_ENCRIPTAR;
	}

	public static byte[] hash(char[] password, byte[] salt) {
		PBEKeySpec spec = new PBEKeySpec(password, salt, ConstantesValidaciones.ITERATIONS,
				ConstantesValidaciones.KEY_LENGTH);
		Arrays.fill(password, Character.MIN_VALUE);
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			return skf.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
		} finally {
			spec.clearPassword();
		}
	}

	public static String generateSecurePassword(String password, String salt) {
		String returnValue = null;
		byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
		returnValue = Base64.getEncoder().encodeToString(securePassword);

		return returnValue.replaceAll("/", "c");
	}

	public static boolean verifyUserPassword(String providedPassword, String securedPassword, String salt) {
		boolean returnValue = false;
		String newSecurePassword = generateSecurePassword(providedPassword, salt);
		returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);

		return returnValue;
	}

	public static String encriptarAES(String texto, String llaveSecreta) throws Exception {
		String textoEncriptado = "";
		// Generamos una KEY
		Key key = new SecretKeySpec(llaveSecreta.getBytes("UTF-8"), "AES");

		// Se obtiene un cifrador AES
		Cipher aes = Cipher.getInstance("AES");

		// Se inicializa para encriptacion y se encripta el texto,
		// que debemos pasar como bytes.
		aes.init(Cipher.ENCRYPT_MODE, key);
		byte[] encriptado = aes.doFinal(texto.getBytes());

		// Se escribe byte a byte en base64 el texto
		Base64.Encoder encoder = Base64.getEncoder();
		textoEncriptado = encoder.encodeToString(encriptado);
		return textoEncriptado;
	}

	public static String desencriptarAES(String textoEncriptado, String llaveSecreta) {
		try {
			// Generamos una clave de 128 bits adecuada para AES
			Key llave = new SecretKeySpec(llaveSecreta.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, llave);
			return new String(cipher.doFinal(Base64.getDecoder().decode(textoEncriptado)));
		} catch (Exception e) {
			System.out.println("Error desencriptando: " + e.toString());
		}
		return null;
	}
}