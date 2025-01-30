package com.example.demo.controller;


import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Customer;
import com.example.demo.form.CustomerForm;
import com.example.demo.service.CustomerService;
import com.example.demo.util.PasswordHashingUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final CustomerService customerService;

	@GetMapping("/login")
	public String doGet(@ModelAttribute CustomerForm form, Model model, 
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
		
		
		model.addAttribute("LoginCheck",true);
		return "login";
	}
	
	@PostMapping("/login")
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
			return "loginExpired";
		}
		
		
		if(form.getUserId() == null && form.getPassword() == null) {
			model.addAttribute("LoginCheck",true);
			return "login";
		}
		System.out.println(form);
		String hashPassword = PasswordHashingUtil.encryption(form.getPassword());
		Customer Entity = new Customer();
		Entity.setUserId(form.getUserId());
		Entity.setPassword(hashPassword);
		boolean check = customerService.login(Entity);
		
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
			model.addAttribute("LoginCheck",check);
			return "loginDecision";
		} else {
			model.addAttribute("LoginCheck",check);
			if(session.getAttribute("authority") != null) {
				return "login";
			} else {
				return "loginExpired";
			}
		} 
	}
}
