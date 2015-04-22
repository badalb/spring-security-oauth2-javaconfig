package com.test.service;

import org.springframework.stereotype.Service;

import com.test.persistence.entities.User;

@Service
public interface UserService {
	public User findByUserName(String userName);

	public User findUserById(String userId);

}
