package com.nagarro.webapp.controller;

/**
 * @author rishabhsinghla
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.nagarro.webapp.model.User;
import com.nagarro.webapp.service.UserService;

public class LoginControllerTest {

	@Mock
	private UserService userService;

	@Mock
	private HttpSession session;

	@InjectMocks
	private LoginController loginController;

	@Mock
	private Model model;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testValidateUser_ValidCredentials() {
		String username = "testuser";
		String password = "password";
		User user = new User(1, username, password);
		when(userService.getUserByUsernameAndPassword(username, password)).thenReturn(user);

		String result = loginController.validateUser(username, password, model, session);

		assertEquals("redirect:/bookList", result);
		verify(session).setAttribute("user", user);
	}

	@Test
	public void testValidateUser_InvalidCredentials() {
		String username = "testuser";
		String password = "password";
		when(userService.getUserByUsernameAndPassword(username, password)).thenReturn(null);

		String result = loginController.validateUser(username, password, model, session);

		assertEquals("login", result);
		verify(model).addAttribute("message", "Invalid Login Credentials!!");
	}

	@Test
	public void testValidateUser_WhenUsernameIsTooShort_ShouldReturnLoginPageWithErrorMessages() {

		String username = "usr";
		String password = "password";

		String result = loginController.validateUser(username, password, model, session);

		assertEquals("login", result);

		verify(model).addAttribute("validation_message", "Min size should be greater than 5");
	}

	@Test
	public void testValidateUser_WhenPasswordIsTooShort_ShouldReturnLoginPageWithErrorMessages() {

		String username = "testuser";
		String password = "pwd";

		String result = loginController.validateUser(username, password, model, session);

		assertEquals("login", result);
		verify(model).addAttribute("validation_message", "Min size should be greater than 5");
	}

	@Test
	public void testValidateUser_WhenUsernameIsTooLong_ShouldReturnLoginPageWithErrorMessages() {
		String username = "thisisaverylongusernameexceedingfiftycharactersforvalidation";
		String password = "password";

		String result = loginController.validateUser(username, password, model, session);

		assertEquals("login", result);
		verify(model).addAttribute("validation_message", "Max size should be less than 50");
	}

	@Test
	public void testValidateUser_WhenUsernameIsTooLong_AndPasswordIsTooShort_ShouldReturnLoginPageWithErrorMessages() {
		String username = "thisisaverylongusernameexceedingfiftycharactersforvalidation";
		String password = "pwd";

		String result = loginController.validateUser(username, password, model, session);

		assertEquals("login", result);
		verify(model).addAttribute("validation_message", "Max size should be less than 50");
	}

}
