package com.it.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.it.todo.entity.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

	// get user data where email = email and password = password
	@Query(value = "from UserEntity where uEmail=:email and uPassword=:password")
	public UserEntity getLogingUser(String email, String password);

}