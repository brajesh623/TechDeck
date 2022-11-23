package com.techdeck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teckdeck.model.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	public User findByUserName(String username);
}
