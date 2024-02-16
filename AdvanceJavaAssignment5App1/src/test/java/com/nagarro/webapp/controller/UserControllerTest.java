package com.nagarro.webapp.controller;

/**
 * @author rishabhsinghla
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nagarro.webapp.dao.UserRepository;
import com.nagarro.webapp.model.User;

public class UserControllerTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserController userController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllUsers() {
		List<User> users = new ArrayList<>();
		users.add(new User(1, "User1", "password1"));
		users.add(new User(2, "User2", "password2"));

		when(userRepository.findAll()).thenReturn(users);

		List<User> result = userController.getAllUsers();

		assertEquals(users.size(), result.size());
	}

	@Test
	public void testCreateUser() {
		User user = new User(1, "User1", "password1");

		when(userRepository.save(any(User.class))).thenReturn(user);

		User createdUser = userController.createUser(user);

		assertEquals(user.getName(), createdUser.getName());
	}

	@Test
	public void testGetUserByName() {
		User user = new User(1, "User1", "password1");

		when(userRepository.findByName("User1")).thenReturn(user);

		User result = userController.getUserByName("User1");

		assertEquals(user.getName(), result.getName());
	}

	@Test
	public void testGetUserByNameAndPassword() {
		User user = new User(1, "User1", "password1");

		when(userRepository.findByNameAndPassword("User1", "password1")).thenReturn(user);

		User result = userController.getUserByNameAndPassword("User1", "password1");

		assertEquals(user.getName(), result.getName());
		assertEquals(user.getPassword(), result.getPassword());
	}

	@Test
	public void testGetUserByName_NotFound() {
	    when(userRepository.findByName("NonExistingUser")).thenReturn(null);
	    
	    User result = userController.getUserByName("NonExistingUser");
	    
	    assertNull(result);
	}

	@Test
	public void testGetUserByNameAndPassword_NotFound() {
	    when(userRepository.findByNameAndPassword("NonExistingUser", "password")).thenReturn(null);
	    
	    User result = userController.getUserByNameAndPassword("NonExistingUser", "password");
	    
	    assertNull(result);
	}

	@Test
	public void testGetAllUsers_Empty() {
	    when(userRepository.findAll()).thenReturn(Collections.emptyList());
	    
	    List<User> result = userController.getAllUsers();
	    
	    assertEquals(0, result.size());
	}

}
