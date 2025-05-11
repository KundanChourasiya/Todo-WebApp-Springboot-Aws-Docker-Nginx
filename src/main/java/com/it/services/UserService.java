package com.it.services;

import com.it.todo.entity.UserEntity;

public interface UserService {

	// save User data
	public boolean saveUser(UserEntity entity);

	// get user data where email = email and password = password
	public UserEntity getLogingUser(String email, String password);

}
