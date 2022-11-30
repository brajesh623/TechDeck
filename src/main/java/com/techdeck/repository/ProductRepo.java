package com.techdeck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techdeck.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer>{
	public Product findByName(String name);
}
