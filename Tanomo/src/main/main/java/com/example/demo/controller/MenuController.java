package com.example.demo.controller;


import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.CustomerForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MenuController {
	
	@GetMapping("/")
	public String doGetIndex(@ModelAttribute CustomerForm form, Model model, 
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
			if(session.getAttribute("authority").equals("ROOT")) {
				return "rootMenu";
			} else {
				return "menu";
			}
		} else {
			return "loginExpired";
		}
	}
	
	@GetMapping("/menu")
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
			if(session.getAttribute("authority").equals("ROOT")) {
				return "rootMenu";
			} else {
				return "menu";
			}
		} else {
			return "loginExpired";
		}
	}
	
	@PostMapping("/menu")
	public String doPost(@ModelAttribute CustomerForm form, Model model, 
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
			if(session.getAttribute("authority").equals("ROOT")) {
				return "rootMenu";
			} else {
				return "menu";
			}
		} else {
			return "loginExpired";
		}
	}
}
