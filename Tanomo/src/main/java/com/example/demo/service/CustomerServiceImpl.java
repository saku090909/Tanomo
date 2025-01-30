package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository repository;
	
	@Override
	public boolean login(Customer customer) {
		return repository.login(customer);
	}
	
	@Override
	public boolean signUp(Customer customer) {
		return repository.userIdCheck(customer);
	}
	
	@Override
	public ArrayList<Customer> userRetrieve(String userId) {
		return repository.userRetrieve(userId);
	}
}
