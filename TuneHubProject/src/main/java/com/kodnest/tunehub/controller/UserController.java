package com.kodnest.tunehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute User user) {
		
		//to check a user with a mail is present or not
		boolean userExists=userService.emailExists(user);
		
		if(userExists==false) {
			userService.saveUser(user);	
			System.out.println("user added succesfully");
		}
		else {
			System.out.println("Duplicate user");
		}
		return "login";
	}

	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,
			@RequestParam("password") String password,HttpSession session) {
		
		if(userService.validUser(email, password)==true) {
			
			session.setAttribute("email", email);
			
			String role=userService.getRole(email);
			if(role.equals("admin")) {
				return "adminhome";
              }else {
            	  return "customerhome"; 
              }
		}
		else {
			return "login";
		}
		
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}


}