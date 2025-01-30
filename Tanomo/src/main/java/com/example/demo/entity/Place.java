package com.example.demo.entity;

import lombok.Data;

@Data
public class Place {
	private int placeId;
	private String placeName;
	
	public Place(){
	}
	
	public Place(int placeId, String placeName){
		this.placeId = placeId;
		this.placeName = placeName;
	}
}