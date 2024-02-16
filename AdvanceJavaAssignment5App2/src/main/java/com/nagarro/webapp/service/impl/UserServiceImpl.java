package com.nagarro.webapp.service.impl;

/**
 * @author rishabhsinghla
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.webapp.model.User;
import com.nagarro.webapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void addUser(User user) {
		this.restTemplate.postForObject("http://localhost:9001/api/users", user, User.class);
	}

	@Override
	public List<User> listUsers() {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) this.restTemplate.getForObject("http://localhost:9001/api/users", List.class);
		return users;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = this.restTemplate.getForObject("http://localhost:9001/api/users/" + username, User.class);
		return user;
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		User user = this.restTemplate.getForObject("http://localhost:9001/api/users/" + username + "/" + password,
				User.class);
		return user;
	}

}
