package com.techdeck.service;

import java.util.List;

import com.techdeck.exception.UserException;
import com.teckdeck.model.User;


public interface UserService {
	public User addUser(User user) throws UserException;
	public User updateUser(User user,String key) throws UserException;
	public User deleteUser(Integer userId,String key) throws UserException;
	public User viewUser(Integer userId,String key) throws UserException;
	public List<User> viewAllUsers(String key) throws UserException;
}