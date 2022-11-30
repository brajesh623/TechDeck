package com.techdeck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techdeck.model.Feedback;


@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer>{

}
