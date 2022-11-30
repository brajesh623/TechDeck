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
import com.techdeck.exception.LoginException;
import com.techdeck.exception.ProductException;
import com.techdeck.model.Product;
import com.techdeck.service.ProductServiceImpl;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductServiceImpl pservice;
	
	@PostMapping("/add")
    public ResponseEntity<Product> addProductHandler(@Valid @RequestBody Product product,@RequestParam String key) throws ProductException, LoginException{
		
		Product p = pservice.addProduct(product,key);
		
		return new ResponseEntity<Product>(p, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Product> updateProductHandler( @Valid @RequestBody Product product,@RequestParam String key) throws ProductException, LoginException{
		
		Product p = pservice.updateProduct(product,key);
		
		return new ResponseEntity<Product>(p, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/view/{productId}")
	public ResponseEntity<Product> viewProductHandler(@PathVariable("productId") Integer productId,@RequestParam String key) throws ProductException, LoginException{
		
		Product p = pservice.viewProduct(productId,key);
		
		return new ResponseEntity<Product>(p, HttpStatus.FOUND);
	}
	
	@GetMapping("/viewAll")
	public ResponseEntity<List<Product>> viewAllProductHandler(@RequestParam String key) throws ProductException, LoginException{
		
		List<Product> p = pservice.viewAllProduct(key);
		
		return new ResponseEntity<List<Product>>(p, HttpStatus.FOUND);
	}
	
}
