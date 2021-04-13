package com.trgt.mrl.myRetail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trgt.mrl.myRetail.entiry.User;

public interface UserRepository extends MongoRepository<User, String> {

	/**
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);
}
