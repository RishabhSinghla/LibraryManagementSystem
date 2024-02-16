package com.nagarro.webapp.service;

/**
 * @author rishabhsinghla
 */

import java.util.List;

import com.nagarro.webapp.model.User;

public interface UserService {

	public void addUser(User user);

	public List<User> listUsers();

	public User getUserByUsername(String username);

	public User getUserByUsernameAndPassword(String username, String password);

}
