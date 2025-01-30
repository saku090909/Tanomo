package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.entity.Item;

public interface ItemService {

	int regist(Item item);
	
	
	void update(Item item);
	void itemImageUpdate(int maxItemId);
	void delete(int itemId);
	
	
	ArrayList<Item> retrieve();
	ArrayList<Item> viwesRetrieve();
	ArrayList<Item> deletionRetrieve();
}
