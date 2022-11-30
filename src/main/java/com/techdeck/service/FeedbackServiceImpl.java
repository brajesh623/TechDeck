package com.techdeck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techdeck.exception.FeedbackException;
import com.techdeck.exception.ProductException;
import com.techdeck.exception.UserException;
import com.techdeck.model.CurrentUserSession;
import com.techdeck.model.Feedback;
import com.techdeck.model.Product;
import com.techdeck.model.User;
import com.techdeck.repository.FeedbackRepo;
import com.techdeck.repository.ProductRepo;
import com.techdeck.repository.SessionRepo;
import com.techdeck.repository.UserRepo;

@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	private FeedbackRepo fRepo;
	
	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private ProductRepo pRepo;
	
	@Autowired
	private SessionRepo sRepo;
	
	@Override
	public Feedback addFeedback( Integer productId, Feedback feedback,String key) throws FeedbackException, UserException, ProductException {
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to add feedback.");
		}
		User user = uRepo.findById(loggedInUser.getUserId()).orElseThrow(() -> new UserException("User with Id " + loggedInUser.getUserId() + " not found"));
		if(user.getUserLoginId()==loggedInUser.getUserId()) {
			Product p =pRepo.findById(productId).orElseThrow(() -> new ProductException("Product with Id " + productId + " not found"));
			
			feedback.setProduct(p);
			feedback.setUser(user);
			
			Feedback f = fRepo.save(feedback);
			
			return f;
		}else throw new UserException("Invalid User Id");
		
		
		
	}

	@Override
	public Feedback updateFeedback( Feedback feedback,String key) throws FeedbackException, UserException {
		
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to update feedback.");
		}
		User user = uRepo.findById(loggedInUser.getUserId()).orElseThrow(() -> new UserException("User with Id " + loggedInUser.getUserId() + " not found"));
		if(user.getUserLoginId()==loggedInUser.getUserId()) {
			Feedback f = fRepo.findById(feedback.getFeedbackId()).orElseThrow(() -> new FeedbackException("Feedback with Id " + feedback.getFeedbackId() + " does not exist"));
			
			if (feedback.getComments() != null) f.setComments(feedback.getComments());
			if (feedback.getProductRating() != null) f.setProductRating(feedback.getProductRating());
			if (feedback.getServiceRating() != null) f.setServiceRating(feedback.getServiceRating());
			if (feedback.getOverallRating() != null) f.setOverallRating(feedback.getOverallRating());
			
			Feedback updated = fRepo.save(f);
			
			return updated;
		}else throw new UserException("Invalid User Id");
		
	}

	@Override
	public Feedback viewFeedback(Integer feedbackId,String key) throws FeedbackException, UserException {
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to view feedback.");
		}
		User user = uRepo.findById(loggedInUser.getUserId()).orElseThrow(() -> new UserException("User with Id " + loggedInUser.getUserId() + " not found"));
		if(user.getUserLoginId()==loggedInUser.getUserId()) {
			Feedback f = fRepo.findById(feedbackId).orElseThrow(() -> new FeedbackException("Feedback with Id " + feedbackId + " does not exist"));
			return f;
		}else throw new UserException("Invalid User Id");
		
	}

	@Override
	public List<Feedback> viewAllFeedback(String key) throws FeedbackException, UserException {
		CurrentUserSession loggedInUser=sRepo.findByUuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to view all feedback.");
		}
		User user = uRepo.findById(loggedInUser.getUserId()).orElseThrow(() -> new UserException("User with Id " + loggedInUser.getUserId() + " not found"));
		if(user.getUserLoginId()==loggedInUser.getUserId()) {
			List<Feedback> f= fRepo.findAll();
			
			if (!f.isEmpty()) return f;
			else throw new FeedbackException("Feedback not found");
		}else throw new UserException("Invalid User Id");
		
	}

	
	
	
}
