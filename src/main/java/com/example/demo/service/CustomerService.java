package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.entity.Customer;

public interface CustomerService {

	boolean login(Customer customer);
	
	boolean signUp(Customer customer);
	
	ArrayList<Customer> userRetrieve(String userId);
}
