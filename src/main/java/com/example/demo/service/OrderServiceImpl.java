package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository repository;
	
	@Override
	public void regist(Order order) {
		repository.rootRegist(order);
	}
	
	
	@Override
	public void buy(Order order) {
		repository.rootBuy(order);
	}
	
	@Override
	public void cancel(Order order) {
		repository.rootCancel(order);
	}

	
	@Override
	public ArrayList<Order> rootRetrieve() {
		return repository.rootRetrieve();
	}
	
	@Override
	public ArrayList<Order> userRetrieve(String loginId) {
		return repository.userRetrieve(loginId);
	}
	
	@Override
	public ArrayList<Order> rootMatch(int orderId) {
		return repository.rootMatch(orderId);
	}
	
	
}
