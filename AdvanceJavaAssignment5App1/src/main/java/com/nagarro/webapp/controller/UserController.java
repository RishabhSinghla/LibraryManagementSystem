package com.nagarro.webapp.controller;

/**
 * @author rishabhsinghla
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.webapp.dao.UserRepository;
import com.nagarro.webapp.model.User;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	// get all users
	@GetMapping
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	// create user
	@PostMapping
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}

	// get user by username
	@GetMapping("/{name}")
	public User getUserByName(@PathVariable("name") String userName) {
		return (User) this.userRepository.findByName(userName);
	}

	// get user by name and password
	@GetMapping("/{name}/{password}")
	public User getUserByNameAndPassword(@PathVariable("name") String userName,
			@PathVariable("password") String userPassword) {
		return (User) this.userRepository.findByNameAndPassword(userName, userPassword);
	}

}
