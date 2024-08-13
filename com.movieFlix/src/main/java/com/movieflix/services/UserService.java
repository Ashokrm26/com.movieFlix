package com.movieflix.services;

import java.util.List;

import com.movieflix.entities.User;

public interface UserService {
	
	public String addUsers(User user);
	
	public boolean emailExists(String email);
	
	public boolean checkResgister(String email);
	
	public User getUserId(String email);
	
	public boolean checkUser(String email, String password);
	
	public List<User> viewUsers();

	public User getUser(String email);
	
	public void updateUser(User user);
	
	public void deleteUser(String email);
	
	public void getDetailsById(int id);
}
