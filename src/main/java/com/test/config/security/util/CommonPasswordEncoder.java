package com.test.config.security.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CommonPasswordEncoder implements PasswordEncoder {
	
	@Override
	public String encode(CharSequence rawPassword) {
		String rawpswd = (String) rawPassword;
		return BCrypt.hashpw(rawpswd, BCrypt.gensalt());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String rawpasswd = (String) rawPassword;
		boolean status =  BCrypt.checkpw(rawpasswd, encodedPassword);
		return status;
	}
	
	
}
