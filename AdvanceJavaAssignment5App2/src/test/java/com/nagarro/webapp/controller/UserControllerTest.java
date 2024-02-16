package com.nagarro.webapp.controller;

/**
 * @author rishabhsinghla
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.nagarro.webapp.model.User;
import com.nagarro.webapp.service.UserService;

public class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@Mock
	private HttpSession session;

	@Mock
	private Model model;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testShowLoginPage() {
		String result = userController.showLoginPage();
		assertEquals("login", result);
	}

	@Test
	public void testShowRegisterPage() {

		String result = userController.showRegisterPage();

		assertEquals("register", result);
	}

	@Test
	public void testRegisterUser_ShouldRedirectToLoginPage() {

		User user = new User(1, "testuser", "password");

		String result = userController.registerUser(user);

		assertEquals("redirect:/login", result);
	}

	@Test
	public void testLogout_ShouldRedirectToLoginPage() {

		String result = userController.logout(session);

		assertEquals("redirect:/login", result);
	}

}
