package com.it.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.repository.UserEntityRepository;
import com.it.todo.entity.UserEntity;

@Service
public class UserServiceImple implements UserService {

	// inject UserEntityRepository dependency
	@Autowired
	private UserEntityRepository userRepo;

	@Override
	public boolean saveUser(UserEntity entity) {
		UserEntity save = userRepo.save(entity);
		if (save != null) {
			return true;
		}
		return false;
	}

	@Override
	public UserEntity getLogingUser(String email, String password) {
		UserEntity logingUser = userRepo.getLogingUser(email, password);
		return logingUser;
	}

}
