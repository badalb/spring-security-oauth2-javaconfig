package com.test.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.test.persistence.entities.User;

/**
 * @author MaheshJ
 * 
 */
public interface UserRepository extends CrudRepository<User, String> {

	public User findByUserName(String userName);
	public List<User> findByTbluserRoles_tblrole_name(String roleName);
	public User findByUserIdAndIsDeleted(String userId, String isDeleted);

}
