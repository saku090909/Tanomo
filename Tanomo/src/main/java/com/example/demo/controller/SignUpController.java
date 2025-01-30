package com.example.demo.controller;


import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Place;
import com.example.demo.form.CustomerForm;
import com.example.demo.service.CustomerService;
import com.example.demo.service.PlaceService;
import com.example.demo.util.PasswordHashingUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignUpController {

	private final CustomerService customerService;
	private final PlaceService PlaceService;

	@GetMapping("/signUp")
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
		} else {
			return "loginExpired";
		}
		
		ArrayList<Place> list = new ArrayList<Place>();
		list = PlaceService.retrieve();
		model.addAttribute("PlaceList",list);
		model.addAttribute("SignUpCheck",true);
		return "signUp";
	}
	
	@PostMapping("/signUp")
	public String doPost(@ModelAttribute CustomerForm form, Model model, HttpServletRequest request, HttpServletResponse response) 
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
		} else if(form.getExpired() != 1){
			ArrayList<Place> list = new ArrayList<Place>();
			list = PlaceService.retrieve();
			model.addAttribute("PlaceList",list);
			return "signUpExpired";
		}
		
		if(form.getUserId() == null && form.getPassword() == null) {
			ArrayList<Place> list = new ArrayList<Place>();
			list = PlaceService.retrieve();
			model.addAttribute("PlaceList",list);
			model.addAttribute("SignUpCheck",true);
			return "signUp";
		}
		System.out.println(form);
		String hashPassword = PasswordHashingUtil.encryption(form.getPassword());
		Customer Entity = new Customer();
		Entity.setUserId(form.getUserId());
		Entity.setPassword(hashPassword);
		Entity.setPlaceId(form.getPlaceId());
		boolean check = customerService.signUp(Entity);
		
		if(check == true) {
			session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			session = request.getSession(true);
			session.setAttribute("loginId", form.getUserId());
			ArrayList<Customer> list = new ArrayList<Customer>();
			list = customerService.userRetrieve(form.getUserId());
			session.setAttribute("placeId", list.get(0).getPlaceId());
			session.setAttribute("placeName", list.get(0).getPlaceName());
			session.setAttribute("authority", list.get(0).getAuthority());
			model.addAttribute("SignUpCheck",check);
			return "signUpDecision";
		} else {
			model.addAttribute("SignUpCheck",check);
			ArrayList<Place> list = new ArrayList<Place>();
			list = PlaceService.retrieve();
			model.addAttribute("PlaceList",list);
			if(session.getAttribute("authority") != null) {
				return "signUp";
			} else {
				return "signUpExpired";
			}
		}
	}
}
