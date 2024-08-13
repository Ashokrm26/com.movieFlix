package com.movieflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.movieflix.entities.Movie;
import com.movieflix.services.MovieService;
@Controller
public class MovieController {
	@Autowired
	MovieService movserv;

	public MovieController(MovieService movserv) {
		super();
		this.movserv = movserv;
	}
	
	@PostMapping("addmovie")
	public String addMovie(@ModelAttribute Movie mov) {
		movserv.addMovie(mov);
		return "addmoviesuccess";
	}
	
	@GetMapping("view-movie")
	public String viewMovie(Model model) {
		List<Movie> movieList = movserv.viewMovie();
		model.addAttribute("movie", movieList);
		return "viewmovies";
	}
	
	@GetMapping("view-movies-users")
	public String viewUsersMovie(Model model) {
		List<Movie> movieList = movserv.viewMovie();
		model.addAttribute("movie", movieList);
		return "viewusermovies";
	}
	
	
}
