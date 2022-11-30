package com.techdeck.service;

import com.techdeck.exception.CartException;
import com.techdeck.exception.ProductException;
import com.techdeck.exception.UserException;
import com.techdeck.model.Cart;
import com.techdeck.model.Product;

public interface CartService {
	public Cart addProductToCart(Product product, String key) throws ProductException, CartException, UserException;
	public Cart increaseQuantity(Integer productId,Integer qantity, String key) throws ProductException, CartException, UserException;
	public Cart decreaseQuantity(Integer productId,Integer qantity, String key) throws ProductException, UserException, CartException;
	public Cart deleteProductFromCart(Integer productId, String key) throws ProductException, UserException, CartException;
	public Cart viewCart(String key)throws UserException, CartException;
}
