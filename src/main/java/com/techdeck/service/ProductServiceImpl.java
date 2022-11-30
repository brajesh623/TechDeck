package com.techdeck.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techdeck.exception.LoginException;
import com.techdeck.exception.ProductException;
import com.techdeck.model.CurrentUserSession;
import com.techdeck.model.Product;
import com.techdeck.repository.ProductRepo;
import com.techdeck.repository.SessionRepo;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo pRepo;
	
	@Autowired
	private SessionRepo sRepo;
	
	@Override
	public Product addProduct(Product product,String key) throws ProductException,LoginException{
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new LoginException("Please provide a valid key to add product.");
		}
		if(loggedInUser.getType()=="Admin") {
			Product p =pRepo.findByName(product.getName());
			if(p!=null) {
				throw new ProductException("Product already exist with this name.");
			}
			return pRepo.save(product);
		}else throw new LoginException("Access denied.");
		
		
		
	}

	@Override
	public Product updateProduct( Product product,String key) throws ProductException, LoginException {
		
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new LoginException("Please provide a valid key to update product.");
		}
		if(loggedInUser.getType()=="Admin") {
			Product p = pRepo.findById(product.getProductId()).orElseThrow(() -> new ProductException("Product with Id " + product.getProductId() + " does not exist"));
			
			if (product.getName() != null) p.setName(product.getName());
			if (product.getPrice() != null) p.setPrice(product.getPrice());
			if (product.getQuantity() != null) p.setQuantity(product.getQuantity());
			if (product.getCategory() != null) p.setCategory(product.getCategory());
			
			Product updated = pRepo.save(p);
			
			return updated;
		}else throw new LoginException("Access denied");
		
	}
	
	@Override
	public Product deleteProduct(Integer productId,String key) throws ProductException, LoginException {
		
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new LoginException("Please provide a valid key to update product.");
		}
		if(loggedInUser.getType()=="Admin") {
			Product p = pRepo.findById(productId).orElseThrow(() -> new ProductException("Product with Id " +productId + " does not exist"));
			pRepo.delete(p);
			
			return p;
		}else throw new LoginException("Access denied.");
		
	}

	@Override
	public Product viewProduct(Integer productId,String key) throws ProductException, LoginException {
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new LoginException("Please provide a valid key to update product.");
		}
		if(loggedInUser.getType()=="Admin") {
			Product p = pRepo.findById(productId).orElseThrow(() -> new ProductException("Product with Id " + productId + " does not exist"));
			return p;
		}else throw new LoginException("Access denied.");
		
	}

	@Override
	public List<Product> viewAllProduct(String key) throws ProductException, LoginException {
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new LoginException("Please provide a valid key to update product.");
		}
		if(loggedInUser.getType()=="Admin") {
			List<Product> p= pRepo.findAll();
			
			if (!p.isEmpty()) return p;
			else throw new ProductException("Product not found");
		}else throw new LoginException("Access denied.");
		
	}

	
	
	
}
