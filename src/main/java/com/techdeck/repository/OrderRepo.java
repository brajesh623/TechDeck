package com.techdeck.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teckdeck.model.Order;

public interface OrderRepo extends JpaRepository<Order,Integer>{

}
