package com.techdeck.service;

import java.util.List;

import com.techdeck.exception.LoginException;
import com.techdeck.exception.ProductException;
import com.techdeck.model.Product;

public interface ProductService {

	public Product addProduct(Product product,String key) throws ProductException, LoginException;
	
	public Product updateProduct(Product product,String key) throws ProductException,LoginException;
	
	public Product deleteProduct( Integer productId,String key) throws ProductException, LoginException;
	
	public Product viewProduct(Integer productId,String key) throws ProductException,LoginException;
	
	public List<Product> viewAllProduct(String key) throws ProductException,LoginException;
}
