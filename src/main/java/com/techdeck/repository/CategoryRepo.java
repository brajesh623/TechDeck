package com.techdeck.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teckdeck.model.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{

}
