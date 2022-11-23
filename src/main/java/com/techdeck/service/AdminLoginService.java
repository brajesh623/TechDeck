package com.techdeck.service;

import com.techdeck.exception.LoginException;
import com.teckdeck.model.AdminDto;

public interface AdminLoginService {
	public String logIntoAccount(AdminDto dto) throws LoginException;
	public String logOutFromAccount(String key) throws LoginException;
}