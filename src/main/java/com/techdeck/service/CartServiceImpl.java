package com.techdeck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techdeck.exception.CartException;
import com.techdeck.exception.ProductException;
import com.techdeck.exception.UserException;
import com.techdeck.repository.CartRepo;
import com.techdeck.repository.ProductRepo;
import com.techdeck.repository.SessionRepo;
import com.techdeck.repository.UserRepo;
import com.teckdeck.model.Cart;
import com.teckdeck.model.CurrentUserSession;
import com.teckdeck.model.Product;
import com.teckdeck.model.User;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	private CartRepo cRepo;
	
	@Autowired
	private ProductRepo pRepo;
	
	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private SessionRepo sRepo;

	@Override
	public Cart addProductToCart(Product product, String key) throws ProductException, CartException, UserException {
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to add product to cart.");
		}
		User user = uRepo.findById(loggedInUser.getUserId()).orElseThrow(() -> new UserException("User with Id " + loggedInUser.getUserId() + " not found"));
		if(user.getUserLoginId()==loggedInUser.getUserId()) {
			Product p =pRepo.findById(product.getProductId()).orElseThrow(() -> new ProductException("Product with Id " + product.getProductId() + " not found"));
			Cart c=user.getCart();
			if(c==null) {
				c=new Cart();
				user.setCart(c);
			}
			List<Product> list=c.getProductList();
			list.add(p);
			c.setProductList(list);
			c.setCartTotal();
			Cart cart=cRepo.save(c);
			return cart;
		}else throw new UserException("Invalid User Id");
	}

	@Override
	public Cart increaseQuantity(Integer productId, Integer qantity, String key) throws ProductException, UserException, CartException {
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to add product to cart.");
		}
		User user = uRepo.findById(loggedInUser.getUserId()).orElseThrow(() -> new UserException("User with Id " + loggedInUser.getUserId() + " not found"));
		if(user.getUserLoginId()==loggedInUser.getUserId()) {
			Product p =pRepo.findById(productId).orElseThrow(() -> new ProductException("Product with Id " + productId + " not found"));
			Cart c=user.getCart();
			if(c==null) {
				throw new CartException("Cart is Empty.");
			}
			List<Product> list=c.getProductList();
			if(list.size()==0) {
				throw new CartException("Cart is Empty.");
			}
			boolean flag=true;
			for(int i=0;i<list.size();i++) {
				
				if(list.get(i).getProductId()==p.getProductId()) {
					list.get(i).setQuantity(qantity);
					flag=false;
				}
			}
			if(flag) {
				throw new ProductException("Product with "+ productId +" not found in cart.");
			}
			c.setProductList(list);
			c.setCartTotal();
			Cart cart=cRepo.save(c);
			return cart;
		}else throw new UserException("Invalid User Id");
	}

	@Override
	public Cart decreaseQuantity(Integer productId, Integer qantity, String key) throws ProductException, UserException, CartException {
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to add product to cart.");
		}
		User user = uRepo.findById(loggedInUser.getUserId()).orElseThrow(() -> new UserException("User with Id " + loggedInUser.getUserId() + " not found"));
		if(user.getUserLoginId()==loggedInUser.getUserId()) {
			Product p =pRepo.findById(productId).orElseThrow(() -> new ProductException("Product with Id " + productId + " not found"));
			Cart c=user.getCart();
			if(c==null) {
				throw new CartException("Cart is Empty.");
			}
			List<Product> list=c.getProductList();
			if(list.size()==0) {
				throw new CartException("Cart is Empty.");
			}
			boolean flag=true;
			for(int i=0;i<list.size();i++) {
				
				if(list.get(i).getProductId()==p.getProductId()) {
					list.get(i).setQuantity(qantity);
					flag=false;
				}
			}
			if(flag) {
				throw new ProductException("Product with "+ productId +" not found in cart.");
			}
			c.setProductList(list);
			c.setCartTotal();
			Cart cart=cRepo.save(c);
			return cart;
		}else throw new UserException("Invalid User Id");
	}

	@Override
	public Cart deleteProductFromCart(Integer productId, String key) throws ProductException, UserException, CartException {
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to add product to cart.");
		}
		User user = uRepo.findById(loggedInUser.getUserId()).orElseThrow(() -> new UserException("User with Id " + loggedInUser.getUserId() + " not found"));
		if(user.getUserLoginId()==loggedInUser.getUserId()) {
			Product p =pRepo.findById(productId).orElseThrow(() -> new ProductException("Product with Id " + productId + " not found"));
			Cart c=user.getCart();
			if(c==null) {
				throw new CartException("Cart is Empty.");
			}
			List<Product> list=c.getProductList();
			if(list.size()==0) {
				throw new CartException("Cart is Empty.");
			}
			boolean flag=true;
			for(int i=0;i<list.size();i++) {
				
				if(list.get(i).getProductId()==p.getProductId()) {
					list.remove(i);
				}
			}
			if(flag) {
				throw new ProductException("Product with "+ productId +" not found in cart.");
			}
			c.setProductList(list);
			c.setCartTotal();
			Cart cart=cRepo.save(c);
			return cart;
		}else throw new UserException("Invalid User Id");
	}
	
}
