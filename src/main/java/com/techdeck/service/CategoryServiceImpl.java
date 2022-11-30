package com.techdeck.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techdeck.exception.CategoryException;
import com.techdeck.exception.LoginException;
import com.techdeck.model.Category;
import com.techdeck.model.CurrentUserSession;
import com.techdeck.repository.CategoryRepo;
import com.techdeck.repository.SessionRepo;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo cRepo;
	
	@Autowired
	private SessionRepo sRepo;
	
	@Override
	public Category addCategory(Category category,String key) throws CategoryException,LoginException{
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new LoginException("Please provide a valid key to add category.");
		}
		if(loggedInUser.getType()=="Admin") {
			Category c =cRepo.findByType(category.getType());
			if(c!=null) {
				throw new CategoryException("Category already exist of this type.");
			}
			return cRepo.save(category);
		}else throw new LoginException("Access denied.");
		
		
		
	}

	@Override
	public Category updateCategory( Category category,String key) throws CategoryException, LoginException {
		
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new LoginException("Please provide a valid key to update category.");
		}
		if(loggedInUser.getType()=="Admin") {
			Category c = cRepo.findById(category.getCId()).orElseThrow(() -> new CategoryException("Category with Id " + category.getCId() + " does not exist"));
			
			if (category.getType() != null) c.setType(category.getType());
			if (category.getProductList() != null) c.setProductList(category.getProductList());
			
			Category updated = cRepo.save(c);
			
			return updated;
		}else throw new LoginException("Access denied");
		
	}
	
	@Override
	public Category deleteCategory(Integer cId,String key) throws CategoryException, LoginException {
		
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new LoginException("Please provide a valid key to update category.");
		}
		if(loggedInUser.getType()=="Admin") {
			Category c = cRepo.findById(cId).orElseThrow(() -> new CategoryException("Category with Id " +cId + " does not exist"));
			cRepo.delete(c);
			
			return c;
		}else throw new LoginException("Access denied.");
		
	}

	@Override
	public Category viewCategory(Integer cId,String key) throws CategoryException, LoginException {
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new LoginException("Please provide a valid key to update category.");
		}
		if(loggedInUser.getType()=="Admin") {
			Category c = cRepo.findById(cId).orElseThrow(() -> new CategoryException("Category with Id " + cId + " does not exist"));
			return c;
		}else throw new LoginException("Access denied.");
		
	}

	@Override
	public List<Category> viewAllCategory(String key) throws CategoryException, LoginException {
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new LoginException("Please provide a valid key to update category.");
		}
		if(loggedInUser.getType()=="Admin") {
			List<Category> c= cRepo.findAll();
			if (!c.isEmpty()) return c;
			else throw new CategoryException("No Category found");
		}else throw new LoginException("Access denied.");
		
	}

	
	
	
}
