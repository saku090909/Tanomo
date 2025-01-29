package com.example.demo.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class Order {
	private Date date;
	private String userId;
	private int itemId;
	private String itemName;
	private int price;
	private int placeId;
	private String placeName;
	private int orderId;
	private byte buy;
	private byte cancel;
	
	public Order(){
	}
	
	public Order(Date date, String userId, int itemId, String itemName, int price, 
			int placeId, String placeName, int orderId, byte buy, byte cancel){
		this.date = date;
		this.userId = userId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
		this.placeId = placeId;
		this.placeName = placeName;
		this.orderId = orderId;
		this.buy = buy;
		this.cancel = cancel;
	}
	
	public Order(Date date, String userId, int itemId, String itemName, int price, 
			int placeId, String placeName, int orderId){
		this.date = date;
		this.userId = userId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
		this.placeId = placeId;
		this.placeName = placeName;
		this.orderId = orderId;
	}
	
	public Order(byte buy, byte cancel){
		this.buy = buy;
		this.cancel = cancel;
	}
}
