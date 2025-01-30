package com.example.demo.form;

import java.sql.Date;

import lombok.Data;

@Data
public class OrderForm {
	private Date date;
	private String userId;
	private int itemId;
	private String itemName;
	private int price;
	private int placeId;
	private String placeName2;
	private int orderId;
	private int page;
}
