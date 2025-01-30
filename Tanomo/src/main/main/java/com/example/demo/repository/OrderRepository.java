package com.example.demo.repository;

import java.util.ArrayList;

import com.example.demo.entity.Order;

public interface OrderRepository {

	void rootRegist(Order order);
	
	
	void rootBuy(Order order);
	void rootCancel(Order order);
	
	
	ArrayList<Order> rootRetrieve();
	ArrayList<Order> userRetrieve(String loginId);
	ArrayList<Order> rootMatch(int orderId);
}
