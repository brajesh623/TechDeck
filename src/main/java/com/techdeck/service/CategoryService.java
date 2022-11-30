package com.techdeck.service;

import java.util.List;

import com.techdeck.exception.CategoryException;
import com.techdeck.exception.LoginException;
import com.techdeck.model.Category;

public interface CategoryService {

	public Category addCategory(Category category,String key) throws CategoryException, LoginException;
	
	public Category updateCategory(Category product,String key) throws CategoryException,LoginException;
	
	public Category deleteCategory( Integer cId,String key) throws CategoryException, LoginException;
	
	public Category viewCategory(Integer cId,String key) throws CategoryException,LoginException;
	
	public List<Category> viewAllCategory(String key) throws CategoryException,LoginException;
}
