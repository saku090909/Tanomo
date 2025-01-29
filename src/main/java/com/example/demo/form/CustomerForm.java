package com.example.demo.form;

import lombok.Data;

@Data
public class CustomerForm {
	private String userId;
	private String password;
	private int placeId;
	private int placeName;
	private byte expired;
}
