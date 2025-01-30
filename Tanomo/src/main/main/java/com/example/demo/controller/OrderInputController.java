package com.example.demo.controller;


import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.form.CustomerForm;
import com.example.demo.form.ItemForm;
import com.example.demo.form.OrderForm;
import com.example.demo.service.ItemService;
import com.example.demo.service.OrderService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderInputController {

	private final OrderService orderService;
	private final ItemService itemService;
	
	@GetMapping("/orderInput")
	public String doGet(@ModelAttribute ItemForm itemForm, CustomerForm form, Model model, 
			HttpServletRequest request, HttpServletResponse response) 
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
		} else {
			return "loginExpired";
		}
		
		ArrayList<Item> list = new ArrayList<Item>();
		list = itemService.viwesRetrieve();
		model.addAttribute("ItemList",list);
		return "orderInput";
	}
	
	@PostMapping("/orderInput")
	public String doPost(@ModelAttribute OrderForm orderForm, ItemForm itemForm, CustomerForm form, Model model, 
			HttpServletRequest request, HttpServletResponse response) 
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
		} else {
			return "loginExpired";
		}
		
		if(orderForm.getPage() == 0) {
			ArrayList<Item> list = new ArrayList<Item>();
			list = itemService.viwesRetrieve();
			model.addAttribute("ItemList",list);
			return "orderInput";
		} else if (orderForm.getPage() == 1) {
			orderForm.setPlaceId(((Number) session.getAttribute("placeId")).intValue());
			System.out.println(orderForm);
			model.addAttribute("OrderForm",orderForm);
			return "orderInputConfirmation";
		} else {
			System.out.println(orderForm);
			Order Entity = new Order();
			Entity.setDate(orderForm.getDate());
			Entity.setUserId(orderForm.getUserId());
			Entity.setItemId(orderForm.getItemId());
			Entity.setItemName(orderForm.getItemName());
			Entity.setPrice(orderForm.getPrice());
			Entity.setPlaceId(orderForm.getPlaceId());
			Entity.setPlaceName(orderForm.getPlaceName2());
			orderService.regist(Entity);
			return "orderInputDecision";
		}
	}
}
