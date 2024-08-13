package com.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieflix.entities.User;
import com.movieflix.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	@Autowired
	UserRepository urepo;
	
	public UserServiceImplementation() {
		super();
	}

	@Override
	public String addUsers(User user) {
		urepo.save(user);
		return "user is created";
	}

	@Override
	public boolean emailExists(String email) {
		if(urepo.findByEmail(email) == null) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public boolean checkUser(String email, String password) {
		User user = urepo.findByEmail(email);
		String db_pass = user.getPassword();
		if(db_pass.equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public List<User> viewUsers() {
		List<User> userObject = urepo.findAll();
		return userObject;
	}

	@Override
	public User getUser(String email) {
		User user = urepo.findByEmail(email);
		return user;
	}

	@Override
	public void updateUser(User user) {
		urepo.save(user);
	}

	@Override
	public User getUserId(String email) {
		User user_id = urepo.findByEmail(email);
		return user_id;
	}

	@Override
	public boolean checkResgister(String email) {
		if(email == null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public void deleteUser(String email) {
//		urepo.findByEmail(email);
		urepo.deleteByEmail(email);
	}

	@Override
	public void getDetailsById(int id) {
		urepo.deleteById(id);;
	}

}
