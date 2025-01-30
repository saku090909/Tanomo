package com.example.demo.repository;

import java.util.ArrayList;

import com.example.demo.entity.Item;

public interface ItemRepository {
	
	int regist(Item item);
	
	
	void update(Item item);
	void itemImageUpdate(int maxItemId);
	void delete(int itemId);
	
	
	ArrayList<Item> retrieve();
	ArrayList<Item> viwesRetrieve();
	ArrayList<Item> deletionRetrieve();
}
