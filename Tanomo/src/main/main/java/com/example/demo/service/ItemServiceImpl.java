package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

	private final ItemRepository repository;
	
	@Override
	public int regist(Item item) {
		return repository.regist(item);
	}
	
	
	@Override
	public void update(Item item) {
		repository.update(item);
	}
	
	@Override
	public void itemImageUpdate(int maxItemId) {
		repository.itemImageUpdate(maxItemId);
	}
	
	@Override
	public void delete(int itemId) {
		repository.delete(itemId);
	}
	
	
	@Override
	public ArrayList<Item> retrieve() {
		return repository.retrieve();
	}
	
	@Override
	public ArrayList<Item> viwesRetrieve() {
		return repository.viwesRetrieve();
	}
	
	@Override
	public ArrayList<Item> deletionRetrieve() {
		return repository.deletionRetrieve();
	}
}
