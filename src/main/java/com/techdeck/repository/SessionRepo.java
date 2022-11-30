package com.techdeck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techdeck.model.CurrentUserSession;


@Repository
public interface SessionRepo extends JpaRepository<CurrentUserSession, Integer>{
	
	public CurrentUserSession findByUuid(String uuid);
}	
