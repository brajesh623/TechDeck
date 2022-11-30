package com.techdeck.service;

import com.techdeck.exception.LoginException;
import com.techdeck.model.LoginDTO;

public interface UserLoginService {
	public String logIntoAccount(LoginDTO dto) throws LoginException;
	public String logOutFromAccount(String key) throws LoginException;
}
