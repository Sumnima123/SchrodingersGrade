package net.code.flightplan.service;

import net.code.flightplan.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import net.code.flightplan.entity.Student;
import net.code.flightplan.model.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	public User getUserbyEmail(String email) {
		User user = userRepo.getUserbyEmail(email);
		return user;
	}
	
	public User get(int id) {
		return userRepo.findById(id).get();
	}
	
}
