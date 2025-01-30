package com.example.demo.entity;

import lombok.Data;

@Data
public class Item {
	private int itemId;
	private String ItemName;
	private String itemImage;
	private int price;
	private String weekDay;
	private int inventory;
	private byte views;
	
	public Item(){
	}
	
	public Item(int itemId, String ItemName, String itemImage, int price, String weekDay, int inventory, byte views){
		this.itemId = itemId;
		this.ItemName = ItemName;
		this.itemImage = itemImage;
		this.price = price;
		this.weekDay = weekDay;
		this.inventory = inventory;
		this.views = views;
	}
	
	public Item(int itemId, String ItemName,int price, String weekDay, int inventory, byte views){
		this.itemId = itemId;
		this.ItemName = ItemName;
		this.price = price;
		this.weekDay = weekDay;
		this.inventory = inventory;
		this.views = views;
	}
	
	public Item(String ItemName,int price, String weekDay, int inventory, byte views){
		this.ItemName = ItemName;
		this.price = price;
		this.weekDay = weekDay;
		this.inventory = inventory;
		this.views = views;
	}
	
	public Item(int itemId, String itemImage){
		this.itemId = itemId;
		this.itemImage = itemImage;
	}
	
	public Item(int itemId){
		this.itemId = itemId;
	}
}