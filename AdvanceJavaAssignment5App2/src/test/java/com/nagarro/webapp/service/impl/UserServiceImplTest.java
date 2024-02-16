package com.nagarro.webapp.service.impl;

/**
 * @author rishabhsinghla
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.nagarro.webapp.model.User;

public class UserServiceImplTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private UserServiceImpl userService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testListUsers_Success() {
		List<User> expectedUsers = new ArrayList<>();
		expectedUsers.add(new User(1, "User1", "password1"));
		expectedUsers.add(new User(2, "User2", "password2"));
		when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(expectedUsers);

		List<User> users = userService.listUsers();

		assertEquals(expectedUsers.size(), users.size());
	}

	@Test
    public void testListUsers_Empty() {
        when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(new ArrayList<>());

        List<User> users = userService.listUsers();

        assertTrue(users.isEmpty());
    }

	@Test
	public void testAddUser_Success() {
		User user = new User(1, "User1", "password1");
		when(restTemplate.postForObject(anyString(), any(User.class), eq(User.class))).thenReturn(user);

		userService.addUser(user);

		verify(restTemplate, times(1)).postForObject(anyString(), any(User.class), eq(User.class));
	}

	@Test
    public void testAddUser_ExceptionThrown() {
        when(restTemplate.postForObject(anyString(), any(User.class), eq(User.class))).thenThrow(new RuntimeException("Service unavailable"));

        User user = new User(1, "User1", "password1");

        assertThrows(RuntimeException.class, () -> userService.addUser(user));
    }

	@Test
	public void testGetUserByUsername_Success() {
		User expectedUser = new User(1, "User1", "password1");
		when(restTemplate.getForObject(anyString(), eq(User.class))).thenReturn(expectedUser);

		User user = userService.getUserByUsername("User1");

		assertNotNull(user);
		assertEquals(expectedUser.getName(), user.getName());
		assertEquals(expectedUser.getPassword(), user.getPassword());
	}

	@Test
    public void testGetUserByUsername_NotFound() {
        when(restTemplate.getForObject(anyString(), eq(User.class))).thenReturn(null);

        User user = userService.getUserByUsername("NonExistentUser");

        assertNull(user);
    }

	@Test
	public void testGetUserByUsernameAndPassword_Success() {
		User expectedUser = new User(1, "User1", "password1");
		when(restTemplate.getForObject(anyString(), eq(User.class))).thenReturn(expectedUser);

		User user = userService.getUserByUsernameAndPassword("User1", "password1");

		assertNotNull(user);
		assertEquals(expectedUser.getName(), user.getName());
		assertEquals(expectedUser.getPassword(), user.getPassword());
	}

	@Test
    public void testGetUserByUsernameAndPassword_NotFound() {
        when(restTemplate.getForObject(anyString(), eq(User.class))).thenReturn(null);

        User user = userService.getUserByUsernameAndPassword("NonExistentUser", "password");

        assertNull(user);
    }

}
