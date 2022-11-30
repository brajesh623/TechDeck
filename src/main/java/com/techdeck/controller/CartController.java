package com.techdeck.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.techdeck.exception.CartException;
import com.techdeck.exception.ProductException;
import com.techdeck.exception.UserException;
import com.techdeck.model.Cart;
import com.techdeck.model.Product;
import com.techdeck.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cService;

	@PostMapping("/addproduct")
	public ResponseEntity<Cart> addProductToCartHandler(@Valid @RequestBody Product product,@RequestParam String key) throws ProductException, CartException, UserException {
		Cart c=cService.addProductToCart(product,key);
		return new ResponseEntity<Cart>(c,HttpStatus.CREATED);
	}
	
	@PostMapping("/increase/{productId}/{quantity}")
	public ResponseEntity<Cart> increaseQuantityHandler(@PathVariable("productId") Integer productId,@PathVariable("quantity") Integer quantity,@RequestParam String key) throws UserException, ProductException, CartException {
		Cart c=cService.increaseQuantity(productId,quantity,key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/decrease/{productId}/{quantity}")
	public ResponseEntity<Cart> decreaseQuantityHandler(@PathVariable("productId") Integer productId,@PathVariable("quantity") Integer quantity,@RequestParam String key) throws UserException, ProductException, CartException {
		Cart c=cService.decreaseQuantity(productId,quantity,key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("delete/{productId}")
	public ResponseEntity<Cart> deleteProductFromCartHandler(@PathVariable("productId") Integer productId,@RequestParam String key) throws ProductException, UserException, CartException {
		Cart c=cService.deleteProductFromCart(productId, key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/view}")
	public ResponseEntity<Cart> viewCartHandler(@RequestParam String key) throws UserException, CartException {
		Cart c=cService.viewCart(key);
		return new ResponseEntity<Cart>(c,HttpStatus.FOUND);
	}
	
}