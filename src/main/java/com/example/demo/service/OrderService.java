package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.entity.Order;

public interface OrderService {
	
	void regist(Order order);
	
	
	void buy(Order order);
	void cancel(Order order);
	
	
	ArrayList<Order> rootRetrieve();
	ArrayList<Order> userRetrieve(String loginId);
	ArrayList<Order> rootMatch(int orderId);
}
