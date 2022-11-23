package com.techdeck.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techdeck.exception.FeedbackException;
import com.techdeck.exception.ProductException;
import com.techdeck.exception.UserException;
import com.techdeck.service.FeedbackServiceImpl;
import com.teckdeck.model.Feedback;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackServiceImpl fservice;
	
	@PostMapping("/add/{productid}")
    public ResponseEntity<Feedback> addFeedbackHandler( @PathVariable("productid") Integer productId,@Valid @RequestBody Feedback feedback,@RequestParam String key) throws FeedbackException, UserException, ProductException {
		
		Feedback f = fservice.addFeedback( productId, feedback,key);
		
		return new ResponseEntity<Feedback>(f, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Feedback> updateFeedbackHandler( @Valid @RequestBody Feedback feedback,@RequestParam String key) throws FeedbackException,UserException{
		
		Feedback f = fservice.updateFeedback(feedback,key);
		
		return new ResponseEntity<Feedback>(f, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/view/{feedbackId}")
	public ResponseEntity<Feedback> viewFeedbackHandler(@PathVariable("feedbackId") Integer feedbackId,@RequestParam String key) throws FeedbackException,UserException{
		
		Feedback f = fservice.viewFeedback(feedbackId,key);
		
		return new ResponseEntity<Feedback>(f, HttpStatus.FOUND);
	}
	
	@GetMapping("/viewAll")
	public ResponseEntity<List<Feedback>> viewAllFeedbackHandler(@RequestParam String key) throws FeedbackException,UserException{
		
		List<Feedback> f = fservice.viewAllFeedback(key);
		
		return new ResponseEntity<List<Feedback>>(f, HttpStatus.FOUND);
	}
	
}
