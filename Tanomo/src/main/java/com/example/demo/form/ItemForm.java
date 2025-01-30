package com.example.demo.form;


import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ItemForm {
	private int itemId;
	private String itemName;
	private String itemImage;
	private MultipartFile file;
	private byte fileCheck;
	private int price;
	private String weekDay;
	private int inventory;
	private byte views;
	private int page;
}
