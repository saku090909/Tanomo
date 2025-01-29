package com.example.demo.controller;


import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Order;
import com.example.demo.entity.Place;
import com.example.demo.form.CustomerForm;
import com.example.demo.form.OrderForm;
import com.example.demo.service.OrderService;
import com.example.demo.service.PlaceService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderOutputController {

	private final OrderService orderService;
	private final PlaceService PlaceService;

	@GetMapping("/orderOutput")
	public String doGet(@ModelAttribute OrderForm orderForm, CustomerForm customerForm, Model model, 
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
		
		ArrayList<Order> list = new ArrayList<Order>();
		if(session.getAttribute("authority").equals("USER")) {
			list = orderService.userRetrieve(session.getAttribute("loginId").toString());
			model.addAttribute("OrderList",list);
			return "orderOutput";
		} else {
			list = orderService.rootRetrieve();
			model.addAttribute("OrderList",list);
			model.addAttribute("OrderCheck",true);
			model.addAttribute("OrderBuyCheck",0);
			model.addAttribute("OrderCancelCheck",0);
			ArrayList<Place> placelist = new ArrayList<Place>();
			placelist = PlaceService.retrieve();
			model.addAttribute("PlaceList",placelist);
			return "rootOrderOutput";
			}
	}
	
	@PostMapping("/orderOutput")
	public String doPost(@ModelAttribute OrderForm orderForm, CustomerForm customerForm, Model model, 
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		System.out.println(String.valueOf(session.getAttribute("loginId")));
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
		
		ArrayList<Order> list = new ArrayList<Order>();
		if(orderForm.getPage() == 0) {
			if(session.getAttribute("authority").equals("USER")) {
				list = orderService.userRetrieve(session.getAttribute("loginId").toString());
				model.addAttribute("OrderList",list);
				return "orderOutput";
			} else {
				list = orderService.rootRetrieve();
				model.addAttribute("OrderList",list);
				model.addAttribute("OrderCheck",true);
				model.addAttribute("OrderBuyCheck",0);
				model.addAttribute("OrderCancelCheck",0);
				ArrayList<Place> placelist = new ArrayList<Place>();
				placelist = PlaceService.retrieve();
				model.addAttribute("PlaceList",placelist);
				return "rootOrderOutput";
			}
		} else if (orderForm.getPage() == 1) {
			if(session.getAttribute("authority").equals("USER")) {
				System.out.println(orderForm);
				model.addAttribute("OrderForm",orderForm);
				return "orderCancelConfirmation";
			} else {
				ArrayList<Order> orderString = new ArrayList<Order>();
				orderString = orderService.rootMatch(orderForm.getOrderId());
				if((orderString.size() != 0) && 
						(orderString.get(0).getBuy() == 0) && (orderString.get(0).getCancel() == 0)) {
					model.addAttribute("OrderList",orderString);
					return "orderConfirmation";
				} else if(orderString.size() == 0){
					list = orderService.rootRetrieve();
					model.addAttribute("OrderList",list);
					model.addAttribute("OrderCheck",false);
					model.addAttribute("OrderBuyCheck",0);
					model.addAttribute("OrderCancelCheck",0);
					ArrayList<Place> placelist = new ArrayList<Place>();
					placelist = PlaceService.retrieve();
					model.addAttribute("PlaceList",placelist);
					return "rootOrderOutput";
				} else {
					list = orderService.rootRetrieve();
					model.addAttribute("OrderList",list);
					model.addAttribute("OrderCheck",true);
					model.addAttribute("OrderBuyCheck",orderString.get(0).getBuy());
					model.addAttribute("OrderCancelCheck",orderString.get(0).getCancel());
					ArrayList<Place> placelist = new ArrayList<Place>();
					placelist = PlaceService.retrieve();
					model.addAttribute("PlaceList",placelist);
					return "rootOrderOutput";
				}
			}
		} else if(orderForm.getPage() == 2){
			System.out.println(orderForm);
			Order Entity = new Order();
			Entity.setDate(orderForm.getDate());
			Entity.setUserId(orderForm.getUserId());
			Entity.setItemId(orderForm.getItemId());
			Entity.setOrderId(orderForm.getOrderId());
			orderService.cancel(Entity);
			return "orderCancelDecision";
		} else {
			System.out.println(orderForm);
			Order Entity = new Order();
			Entity.setDate(orderForm.getDate());
			Entity.setUserId(orderForm.getUserId());
			Entity.setItemId(orderForm.getItemId());
			Entity.setOrderId(orderForm.getOrderId());
			orderService.buy(Entity);
			return "orderBuyDecision";
		}
	}
}
