package com.techdeck.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.techdeck.exception.CategoryException;
import com.techdeck.exception.LoginException;
import com.techdeck.model.Category;
import com.techdeck.service.CategoryServiceImpl;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl cservice;
	
	@PostMapping("/add")
    public ResponseEntity<Category> addCategoryHandler(@Valid @RequestBody Category category,@RequestParam String key) throws CategoryException, LoginException {
		
		Category c = cservice.addCategory(category,key);
		
		return new ResponseEntity<Category>(c, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Category> updateCategoryHandler( @Valid @RequestBody Category category,@RequestParam String key) throws CategoryException, LoginException{
		
		Category c = cservice.updateCategory(category,key);
		
		return new ResponseEntity<Category>(c, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/view/{cId}")
	public ResponseEntity<Category> viewCategoryHandler(@PathVariable("cId") Integer cId,@RequestParam String key) throws CategoryException, LoginException {
		
		Category c = cservice.viewCategory(cId,key);
		
		return new ResponseEntity<Category>(c, HttpStatus.FOUND);
	}
	
	@GetMapping("/viewAll")
	public ResponseEntity<List<Category>> viewAllCategoryHandler(@RequestParam String key) throws LoginException, CategoryException{
		
		List<Category> c = cservice.viewAllCategory(key);
		
		return new ResponseEntity<List<Category>>(c, HttpStatus.FOUND);
	}
	
}
