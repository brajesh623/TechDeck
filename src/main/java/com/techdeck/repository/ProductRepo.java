package com.techdeck.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teckdeck.model.Product;

public interface ProductRepo extends JpaRepository<Product,Integer>{

}
