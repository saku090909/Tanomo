package com.example.demo.controller;


import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Item;
import com.example.demo.form.CustomerForm;
import com.example.demo.form.ItemForm;
import com.example.demo.service.ItemService;
import com.example.demo.util.FileUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BentouManagementController {

	private final ItemService itemService;
	
	@GetMapping("/bentouManagement")
	public String doGet(@ModelAttribute CustomerForm form, Model model, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		System.out.println(session.getAttribute("loginId"));
		model.addAttribute("loginId",session.getAttribute("loginId"));
		System.out.println(session.getAttribute("placeName"));
		model.addAttribute("placeName",session.getAttribute("placeName"));
		System.out.println(session.getAttribute("authority"));
		if(session.getAttribute("authority") != null) {
			if(session.getAttribute("authority").equals("USER")) {
				model.addAttribute("authority","一般ユーザー");
			} else {
				model.addAttribute("authority","★管理者★");
			}
		} else if(form.getExpired() != 1) {
			model.addAttribute("LoginCheck",true);
			return "loginExpired";
		}
		
		ArrayList<Item> list = new ArrayList<Item>();
		list = itemService.retrieve();
		model.addAttribute("ItemList",list);
		return "bentouManagementOutput";
	}
	
	@PostMapping("/bentouManagement")
	public String doPost(@ModelAttribute CustomerForm form, ItemForm itemForm, Model model, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		System.out.println(session.getAttribute("loginId"));
		model.addAttribute("loginId",session.getAttribute("loginId"));
		System.out.println(session.getAttribute("placeName"));
		model.addAttribute("placeName",session.getAttribute("placeName"));
		System.out.println(session.getAttribute("authority"));
		if(session.getAttribute("authority") != null) {
			if(session.getAttribute("authority").equals("USER")) {
				model.addAttribute("authority","一般ユーザー");
			} else {
				model.addAttribute("authority","★管理者★");
			}
		} else if(form.getExpired() != 1) {
			model.addAttribute("LoginCheck",true);
			return "loginExpired";
		}
		
		if(itemForm.getPage() == 0) {
			ArrayList<Item> list = new ArrayList<Item>();
			list = itemService.retrieve();
			model.addAttribute("ItemList",list);
			return "bentouManagementOutput";
			
		} else if(itemForm.getPage() == 1) {
			System.out.println(itemForm);
			model.addAttribute("ItemForm",itemForm);
			return "bentouManagementInput";
		} else if(itemForm.getPage() == 2){
			if(itemForm.getFileCheck() == 1) {
				FileUtil.fileUpload(itemForm.getFile());
			}
			System.out.println(itemForm);
			model.addAttribute("ItemForm",itemForm);
			return "bentouManagementInputConfirmation";
		} else if(itemForm.getPage() == 3){
			Item Entity = new Item();
			Entity.setItemName(itemForm.getItemName());
			Entity.setPrice(itemForm.getPrice());
			Entity.setWeekDay(itemForm.getWeekDay());
			Entity.setInventory(itemForm.getInventory());
			Entity.setViews(itemForm.getViews());
			int maxItemId = itemService.regist(Entity);
			FileUtil.fileUpdate(maxItemId);
			itemService.itemImageUpdate(maxItemId);
			return "bentouManagementInputDecision";
			
		} else if(itemForm.getPage() == 4){
			System.out.println(itemForm);
			model.addAttribute("ItemForm",itemForm);
			return "bentouManagementUpdate";
		} else if(itemForm.getPage() == 5) {
			if(itemForm.getFileCheck() == 1) {
				FileUtil.fileUpload(itemForm.getFile());
			}
			System.out.println(itemForm);
			model.addAttribute("ItemForm",itemForm);
			return "bentouManagementUpdateConfirmation";
		} else if(itemForm.getPage() == 6){
			FileUtil.fileUpdate(itemForm.getItemId());
			System.out.println(itemForm);
			Item Entity = new Item();
			Entity.setItemId(itemForm.getItemId());
			Entity.setItemName(itemForm.getItemName());
			Entity.setPrice(itemForm.getPrice());
			Entity.setWeekDay(itemForm.getWeekDay());
			Entity.setInventory(itemForm.getInventory());
			Entity.setViews(itemForm.getViews());
			itemService.update(Entity);
			return "bentouManagementUpdateDecision";
			
		} else if(itemForm.getPage() == 7) {
			System.out.println(itemForm);
			model.addAttribute("ItemForm",itemForm);
			return "bentouManagementDeletionConfirmation";
		} else if(itemForm.getPage() == 8) {
			System.out.println(itemForm);
			itemService.delete(itemForm.getItemId());
			return "bentouManagementDeletionDecision";
		} else if(itemForm.getPage() == 9) {
			ArrayList<Item> list = new ArrayList<Item>();
			list = itemService.deletionRetrieve();
			model.addAttribute("ItemList",list);
			return "bentouManagementDeletionOutput";
		} else if(itemForm.getPage() == 10){
			System.out.println(itemForm);
			model.addAttribute("ItemForm",itemForm);
			return "bentouManagementDeletionRestoration";
		} else if(itemForm.getPage() == 11){
			if(itemForm.getFileCheck() == 1) {
				FileUtil.fileUpload(itemForm.getFile());
			}
			System.out.println(itemForm);
			model.addAttribute("ItemForm",itemForm);
			return "bentouManagementDeletionRestorationConfirmation";
		} else {
			FileUtil.fileUpdate(itemForm.getItemId());
			System.out.println(itemForm);
			Item Entity = new Item();
			Entity.setItemId(itemForm.getItemId());
			Entity.setItemName(itemForm.getItemName());
			Entity.setPrice(itemForm.getPrice());
			Entity.setWeekDay(itemForm.getWeekDay());
			Entity.setInventory(itemForm.getInventory());
			Entity.setViews(itemForm.getViews());
			itemService.update(Entity);
			return "bentouManagementDeletionRestorationDecision";
		}
	}
}
