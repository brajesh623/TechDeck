package com.techdeck.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techdeck.exception.LoginException;
import com.techdeck.exception.OrderException;
import com.techdeck.exception.UserException;
import com.techdeck.model.Cart;
import com.techdeck.model.CurrentUserSession;
import com.techdeck.model.Order;
import com.techdeck.model.User;
import com.techdeck.repository.CartRepo;
import com.techdeck.repository.OrderRepo;
import com.techdeck.repository.SessionRepo;
import com.techdeck.repository.UserRepo;

@Service
public class OrderServiceImpl implements OrderService{
		
	@Autowired
	private UserRepo uRepo;
	
	@Autowired 
	private CartRepo cRepo;
	
	@Autowired
	private SessionRepo sRepo;
	
	@Autowired
	 private OrderRepo oRepo;

	@Override
	public Order placeOrder(String key) throws OrderException, UserException {
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to place Order.");
		}
		User user = uRepo.findById(loggedInUser.getUserId()).orElseThrow(() -> new UserException("User with Id " + loggedInUser.getUserId() + " not found"));
		if(user.getUserLoginId()==loggedInUser.getUserId()) {
			Order o=new Order();
			Cart c=user.getCart();
			c.setCartTotal();
			o.setProductList(c.getProductList());
			o.setAmount(c.getCartTotal());
			o.setStatus("Placed");
			c.setCartTotal(null);
			c.setProductList(null);
			cRepo.save(c);
			return oRepo.save(o);		
		}else throw new UserException("Invalid User Id");
	}

	@Override
	public Order cancelOrder(Integer orderId, String key) throws OrderException, UserException {
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to cancel order.");
		}
		User user = uRepo.findById(loggedInUser.getUserId()).orElseThrow(() -> new UserException("User with Id " + loggedInUser.getUserId() + " not found"));
		if(user.getUserLoginId()==loggedInUser.getUserId()) {
			List<Order> orders=user.getOrders();
			for(Order o:orders) {
				if(o.getOrderId()==orderId) {
					o.setStatus("Canceled");
					return oRepo.save(o);
				}
			}
			throw new OrderException("No order found for Order Id "+orderId);		
		}else throw new UserException("Invalid User Id");
	}

	@Override
	public List<Order> viewOrder(String key) throws OrderException, UserException {
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to view order.");
		}
		User user = uRepo.findById(loggedInUser.getUserId()).orElseThrow(() -> new UserException("User with Id " + loggedInUser.getUserId() + " not found"));
		if(user.getUserLoginId()==loggedInUser.getUserId()) {
			return user.getOrders();
		}else throw new UserException("Invalid User Id.");
	}

	@Override
	public Order viewOrderById(Integer orderId, String key) throws OrderException, LoginException {
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new LoginException("Please provide a valid key to view order.");
		}
		if(loggedInUser.getType()=="Admin") {
			Order o=oRepo.findById(orderId).orElseThrow(()-> new OrderException("No order with order Id "+orderId+" found."));
			return o;
		}else throw new LoginException("Access denied.");
	}

	@Override
	public List<Order> viewAllOrder(String key) throws OrderException, LoginException {
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new LoginException("Please provide a valid key to view order.");
		}
		if(loggedInUser.getType()=="Admin") {
			List<Order> o=oRepo.findAll();
			if (!o.isEmpty()) return o;
			else throw new OrderException("No orders found.");
		}else throw new LoginException("Access denied.");
	}
	
}
