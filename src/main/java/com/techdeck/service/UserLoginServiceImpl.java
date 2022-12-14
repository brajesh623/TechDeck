package com.techdeck.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techdeck.exception.LoginException;
import com.techdeck.model.CurrentUserSession;
import com.techdeck.model.LoginDTO;
import com.techdeck.model.User;
import com.techdeck.repository.SessionRepo;
import com.techdeck.repository.UserRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class UserLoginServiceImpl implements UserLoginService{
	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private SessionRepo sRepo;

	@Override
	public String logIntoAccount(LoginDTO dto) throws LoginException {
		User user=uRepo.findByUserName(dto.getUserName());
		if(user==null) {
			throw new LoginException("Please Enter a valid username.");
		}
		Optional<CurrentUserSession> validUserSessionOpt =sRepo.findById(user.getUserLoginId());
		if(validUserSessionOpt.isPresent()) {
			throw new LoginException("User already Logged in with this username.");
		}
		if(user.getPassword().equals(dto.getPassword())) {
			String key=RandomString.make(6);
			CurrentUserSession currentUserSession=new CurrentUserSession(user.getUserLoginId(),"user",key,LocalDateTime.now());
			sRepo.save(currentUserSession);
			return currentUserSession.toString();
		}else {
			throw new LoginException("Please Enter a valid password");
		}
	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		
		CurrentUserSession validUserSession=sRepo.findByUuid(key);
		
		if(validUserSession==null) {
			throw new LoginException("User not logged in with this username.");
		}
		sRepo.delete(validUserSession);
		return "Logged out successfully.";
	}
}
