package com.movieflix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {
	
	@GetMapping("map-register")
	public String mapRegister() {
		return "register";
	}
	
	@GetMapping("map-login")
	public String mapLogin() {
		return "login";
	}
	
	@GetMapping("user-home")
	public String userHome() {
		return "home";
	}
	
	@GetMapping("add-movie")
	public String addMovie() {
		return "addmovie";
	}
//	@GetMapping("back")
//	public String backToAdminHome() {
//		return "adminhome";
//	}
//	@GetMapping("userlist")
//	public String backToUserList() {
//		return "userdetails";
//	}
	
//	@GetMapping("delete-user")
//	public String deleteUser() {
//		return "deleteuser";
//	}
	
//	@GetMapping("update-user")
//	public String updateUser() {
//		return "updateuser";
//	}
}
