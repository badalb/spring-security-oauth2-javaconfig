package com.test.aouth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.test.persistence.entities.User;
import com.test.persistence.repositories.UserRepository;

@Component 
public class OAuthUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository users;



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = users.findByUserName(username);
		return new OAuthUser(user);
	}

	
}
