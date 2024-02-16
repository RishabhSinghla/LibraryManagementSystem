package com.nagarro.webapp.controller;

/**
 * @author rishabhsinghla
 */

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nagarro.webapp.model.User;
import com.nagarro.webapp.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping("/validate")
	public String validateUser(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model, HttpSession session) {
		System.out.println("Inside login controller");
		if (username.length() < 5) {
			model.addAttribute("validation_message", "Min size should be greater than 5");
			return "login";
		}
		if (username.length() > 50) {
			model.addAttribute("validation_message", "Max size should be less than 50");
			return "login";
		}
		if (password.length() < 5) {
			model.addAttribute("validation_message", "Min size should be greater than 5");
			return "login";
		}
		if (username.length() > 50) {
			model.addAttribute("validation_message", "Max size should be less than 50");
			return "login";
		}
		User user = this.userService.getUserByUsernameAndPassword(username, password);
		System.out.println(user);
		if (user == null) {

			model.addAttribute("message", "Invalid Login Credentials!!");
			return "login";
		}
		session.setAttribute("user", user);
		return "redirect:/bookList";

	}

}
