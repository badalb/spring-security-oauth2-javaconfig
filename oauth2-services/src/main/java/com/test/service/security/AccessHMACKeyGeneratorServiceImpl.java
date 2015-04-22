package com.test.service.security;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Component;

@Component
public class AccessHMACKeyGeneratorServiceImpl {

	private static final char[] symbols = new char[36];
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	private static final String SHA1PRNG_ALGORITHM="SHA1PRNG";
	private static final String SHA1_ALGORITHM="SHA-1";
	private final char[] buf;
	private final Random random = new Random();
	
	static {
		for (int idx = 0; idx < 10; ++idx)
			symbols[idx] = (char) ('0' + idx);
		for (int idx = 10; idx < 36; ++idx)
			symbols[idx] = (char) ('a' + idx - 10);
	}

	AccessHMACKeyGeneratorServiceImpl() {
		buf = new char[20];
	}

	public String generateAccessKey() {
		for (int idx = 0; idx < buf.length; ++idx)
			buf[idx] = symbols[random.nextInt(symbols.length)];
		return new String(buf).toUpperCase();
	}
	
	public String generateHMACKey() throws GeneralSecurityException {
			SecureRandom prng = SecureRandom.getInstance(SHA1PRNG_ALGORITHM);
			String randomNum = new Integer(prng.nextInt()).toString();
			MessageDigest sha = MessageDigest.getInstance(SHA1_ALGORITHM);
			byte[] result = sha.digest(randomNum.getBytes());
			return hexEncode(result);
	}
	
	private String hexEncode(byte[] aInput) {
		StringBuilder result = new StringBuilder();
		char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		for (int idx = 0; idx < aInput.length; ++idx) {
			byte b = aInput[idx];
			result.append(digits[(b & 0xf0) >> 4]);
			result.append(digits[b & 0x0f]);
		}
		return result.toString();
	}
	
	public String generateHMAC(String data, String hexEncodedKey) throws GeneralSecurityException,IOException {
		String result = "";
			byte[] keyBytes = hexEncodedKey.getBytes();
			SecretKeySpec signingKey = new SecretKeySpec(keyBytes,HMAC_SHA1_ALGORITHM);
			Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(data.getBytes());
			byte[] hexBytes = new Hex().encode(rawHmac);
			result = new String(hexBytes, "UTF-8");
		return result;
	}

	/*private byte[] convertHexStr2Bytes(String hex) {

		byte[] bArray = new BigInteger("10" + hex, 16).toByteArray();

		byte[] ret = new byte[bArray.length - 1];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = bArray[i + 1];
		}
		return ret;
	}*/
}
