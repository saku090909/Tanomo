package com.example.demo.repository;

import java.util.ArrayList;

import com.example.demo.entity.Customer;

public interface CustomerRepository {
	
	boolean login(Customer customer);
	
	boolean userIdCheck(Customer customer);
	
	ArrayList<Customer> userRetrieve(String userId);
}
