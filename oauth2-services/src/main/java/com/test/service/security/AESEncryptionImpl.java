package com.test.service.security;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AESEncryptionImpl {
	
	private static final String AES_ALORITHM = "AES";

	private static final String MASTER_KEY_FILE_NAME="masterKey.txt";

	private static final String ENCRYPTED_KEY_FILE_NAME="encryptedDataKey.txt";
	//This is the MEK key spec
	private SecretKeySpec masterKeySpec;

	//This is the DEC key spec
	private SecretKeySpec originalKeySpec;

	@Value("${encryption.key.path}")
	private String keyPath;

	private SecretKeySpec getOriginalKeySpec() throws GeneralSecurityException, FileNotFoundException, IOException {

		if (masterKeySpec == null) {
			masterKeySpec = getMasterKeySpec();
		}
		// get encrypted key from file and decrypt the key
		byte[] bytes = new byte[32];
		File f = new File(keyPath+ENCRYPTED_KEY_FILE_NAME);
		FileInputStream is= new FileInputStream(f);
		if (f.exists()) {
			is.read(bytes);
		}
		if(is!=null){
			is.close();
		}

		bytes=decryptDataEncKey(bytes,masterKeySpec);
		originalKeySpec = new SecretKeySpec(bytes
				, AES_ALORITHM);
		return originalKeySpec;
	}

	private SecretKeySpec getMasterKeySpec() throws GeneralSecurityException {
		if (masterKeySpec == null) {
			try {
				byte[] bytes = new byte[16];
				File f = new File(keyPath+MASTER_KEY_FILE_NAME);
				if (f.exists()) {
					new FileInputStream(f).read(bytes);
				}
				masterKeySpec = new SecretKeySpec(bytes, AES_ALORITHM);
			} catch (IOException ex) {
				throw new GeneralSecurityException(ex.getMessage());
			}
		}
		return masterKeySpec;
	}

	public String encryptDataEncKey(byte[] bytes)
			throws GeneralSecurityException {
		Cipher cipher = Cipher.getInstance(AES_ALORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, getMasterKeySpec());
		byte[] encodedText = cipher.doFinal(bytes);
		return new String(Hex.encodeHexString(encodedText));
	}

	public byte[] decryptDataEncKey(byte[] encodedText, SecretKeySpec keySpec)
			throws GeneralSecurityException {
		Cipher cipher = Cipher.getInstance(AES_ALORITHM);
		cipher.init(Cipher.DECRYPT_MODE, keySpec);
		byte[] decodedText = cipher.doFinal(encodedText);
		return decodedText;
	}

	public String encrypt(String text) throws GeneralSecurityException, FileNotFoundException, IOException {
		Cipher cipher = Cipher.getInstance(AES_ALORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, getOriginalKeySpec());
		byte[] encodedText = cipher.doFinal(text.getBytes());
		return new String(Hex.encodeHexString(encodedText));
	}

	public String decrypt(String encodedText) throws GeneralSecurityException, FileNotFoundException, IOException {
		Cipher cipher = Cipher.getInstance(AES_ALORITHM);
		cipher.init(Cipher.DECRYPT_MODE, getOriginalKeySpec());
		//byte[] decodedText = cipher.doFinal(CommonUtil.convertHexStr2Bytes(encodedText));
		byte[] decodedText = cipher.doFinal(convertHexStr2Bytes(encodedText));
		return new String(decodedText);
	}
	
	private byte[] convertHexStr2Bytes(String hex) {
		byte[] bArray = new BigInteger("10" + hex, 16).toByteArray();
		byte[] ret = new byte[bArray.length - 1];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = bArray[i + 1];
		}
		return ret;
	}

}
