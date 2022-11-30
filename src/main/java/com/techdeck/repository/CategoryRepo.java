package com.techdeck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techdeck.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer>{
	public Category findByType(String type);
}
