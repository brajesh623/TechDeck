package com.techdeck.service;

import java.util.List;

import com.techdeck.exception.LoginException;
import com.techdeck.exception.OrderException;
import com.techdeck.exception.UserException;
import com.techdeck.model.Order;

public interface OrderService {
	public Order placeOrder(String key) throws OrderException, UserException;
	public Order cancelOrder(Integer orderId, String key) throws OrderException, UserException;
	public List<Order> viewOrder(String key) throws OrderException, UserException;
	public Order viewOrderById(Integer orderId, String key) throws OrderException, LoginException;
	public List<Order> viewAllOrder(String key) throws OrderException, LoginException;
}
