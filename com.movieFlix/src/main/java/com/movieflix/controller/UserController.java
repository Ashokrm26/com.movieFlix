package com.movieflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.movieflix.entities.Movie;
import com.movieflix.entities.User;
import com.movieflix.services.MovieService;
import com.movieflix.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserService userv;
	@Autowired
	MovieService movserv;

//	@Autowired
//	UserRepository urepo;

	public UserController(UserService userv) {
		super();
		this.userv = userv;
	}

	@PostMapping("register-user")
	public String addUsers(@ModelAttribute User usr) {
		boolean status = userv.emailExists(usr.getEmail());
		if(status==true) {
			return "regfail";
		}
		else {
			userv.addUsers(usr);
		}
		return "registersuccess";
	}

	@GetMapping("update-user")
	public String updateUser(Model model, HttpSession session) {
		String email = (String)session.getAttribute("email");
		User usr=userv.getUser(email);
		model.addAttribute("users", usr);
		return "updateuser";
	}

	@PostMapping("update-profile")
	public String updateProfile(@ModelAttribute User usr) {
		User user_id = userv.getUserId(usr.getEmail());
		String dp_pass = user_id.getPassword();

		int id = user_id.getId();
		usr.setId(id);
		usr.setPassword(dp_pass);
		userv.addUsers(usr);
		return "home";
	}
	
	@PostMapping("updatinguserdetail")
	public String updatingUserPofile(@ModelAttribute User usr, Model model) {
		User user_id = userv.getUserId(usr.getEmail());
		String dp_pass = user_id.getPassword();

		int id = user_id.getId();
		usr.setId(id);
		usr.setPassword(dp_pass);
		userv.addUsers(usr);
		List<User> user_list = userv.viewUsers();
		model.addAttribute("users", user_list);
		return "userdetails";
	}
//	@GetMapping("map_updateuser")
//	public String updatingUsersDetails(@RequestParam String id, Model model) {
//		User user = userv.getUser(id);
//		model.addAttribute(id, user);
//		return "updateuser";
//	}
	
	@PostMapping("login-user")
	public String validateUser(@RequestParam String email,@RequestParam String password, HttpSession session, Model model) {

		//		boolean alreadyPresent = userv.checkResgister(email);
		boolean status = userv.emailExists(email);
		if(status == false) {
			return "register";
		}
		else {
			boolean loginStatus = userv.checkUser(email, password);

			if(loginStatus == true) {

				session.setAttribute("email", email);

				if(email.equals("admin@gmail.com")) {
					List<Movie> movieList = movserv.viewMovie();
					model.addAttribute("movie", movieList);
					return "adminhome";
				}
				else {
					return "home";
				}
			}else {
				return "login";
			}
		}
	}

	@GetMapping("view-user")
	public String viewUser(Model model) {
		List<User> user_list = userv.viewUsers();
		model.addAttribute("users", user_list);
		return "userdetails";
	}
	@GetMapping("userlist")
	public String backToUserList(Model model) {
		List<User> user_list = userv.viewUsers();
		model.addAttribute("users", user_list);
		return "userdetails";
	}
	
	@Transactional
	@GetMapping("map_deleteuser")
	public String deleteUserDetails(@RequestParam String id, Model model) {
		userv.deleteUser(id);
		List<User> user_list = userv.viewUsers();
		model.addAttribute("users", user_list);
		return "userdetails";
	}
	
	
	@GetMapping("map_updateuser")
	public String updatingUsersDetails(@RequestParam String id, Model model) {
		User user = userv.getUser(id);
		model.addAttribute("user", user);
		return "updatinguserdetails";
	}
	
	//	@GetMapping("exploremovies")
	//	public String exploreMovie(Model model, HttpSession session) {
	//		//getting email from session
	//		String email = (String)session.getAttribute("email");
	//		// getting the user details for email
	//		User usr = userv.getUser(email);
	//		//checking whether user is premium
	//		if(usr.isPremium() == true) {
	//			//getting the list of movies 
	//			List<Movie> movieList = movserv.viewMovie();
	//			// adding the movie details in model
	//			model.addAttribute("movie", movieList);
	//			// if premium, show movies
	//			return "viewusermovies";
	//		}
	//		else {
	//			return "payment";
	//		}
	//	}

	@GetMapping("exploremovies")
	public String exploreMovie(Model model, HttpSession session) {

		String email = (String)session.getAttribute("email");

		User usr=userv.getUser(email);

		boolean status = usr.isPremium();

		if(status == true)
		{
			List<Movie> movieList = movserv.viewMovie();
			model.addAttribute("movie", movieList);
			return "viewusermovies";
		}
		else
		{
			return "payment";
		}
	}
	
	@GetMapping("back")
	public String backToAdminHome(Model model) {
		List<Movie> movieList = movserv.viewMovie();
		model.addAttribute("movie", movieList);
		return "adminhome";
	}

	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
}
