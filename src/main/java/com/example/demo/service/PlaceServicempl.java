package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Place;
import com.example.demo.repository.PlaceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceServicempl implements PlaceService {

	private final PlaceRepository repository;
	
	@Override
	public ArrayList<Place> retrieve() {
		return repository.retrieve();
	}
}
