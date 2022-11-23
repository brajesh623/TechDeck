package com.techdeck.service;

import java.util.List;

import com.techdeck.exception.FeedbackException;
import com.techdeck.exception.ProductException;
import com.techdeck.exception.UserException;
import com.teckdeck.model.Feedback;

public interface FeedbackService {

	public Feedback addFeedback( Integer productId, Feedback feedback,String key) throws FeedbackException, UserException, ProductException;
	
	public Feedback updateFeedback(Feedback feedback,String key) throws FeedbackException,UserException;
	
	public Feedback viewFeedback(Integer feedbackId,String key) throws FeedbackException,UserException;
	
	public List<Feedback> viewAllFeedback(String key) throws FeedbackException,UserException;
}
