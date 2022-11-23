package com.techdeck.service;

import com.techdeck.exception.LoginException;
import com.teckdeck.model.LoginDTO;


public interface LoginService {
	public String logIntoAccount(LoginDTO dto) throws LoginException;
	public String logOutFromAccount(String key) throws LoginException;
}
