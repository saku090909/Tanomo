package com.example.demo.entity;

import lombok.Data;

@Data
public class Customer {
	private String userId;
	private String password;
	private int placeId;
	private String placeName;
	private String authority;
	
	public Customer(){
	}
	
	public Customer(String userId, String password, int placeId){
		this.userId = userId;
		this.password = password;
		this.placeId = placeId;
	}
	
	public Customer(int placeId, String placeName, String authority){
		this.placeId = placeId;
		this.placeName = placeName;
		this.authority = authority;
	}
}