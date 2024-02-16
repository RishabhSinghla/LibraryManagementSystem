package com.nagarro.webapp.service.impl;

/**
 * @author rishabhsinghla
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
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

import com.nagarro.webapp.model.Author;

public class AuthorServiceImplTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private AuthorServiceImpl authorService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testListAuthors_Success() {
		List<Author> expectedAuthors = new ArrayList<>();
		expectedAuthors.add(new Author(1, "Author 1"));
		expectedAuthors.add(new Author(2, "Author 2"));
		when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(expectedAuthors);

		List<Author> authors = authorService.listAuthors();

		assertEquals(expectedAuthors.size(), authors.size());
		assertEquals(expectedAuthors.get(0).getAuthorName(), authors.get(0).getAuthorName());
		assertEquals(expectedAuthors.get(1).getAuthorName(), authors.get(1).getAuthorName());
	}

	@Test
    public void testListAuthors_ExceptionThrown() {
        when(restTemplate.getForObject(anyString(), eq(List.class))).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> {
            authorService.listAuthors();
        });
    }

	@Test
	public void testListAuthors_EmptyListReturned() {
        when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(new ArrayList<>());

        List<Author> authors = authorService.listAuthors();

        assertNotNull(authors);
        assertTrue(authors.isEmpty());
    }

	@Test
	public void testListAuthors_ValidRequestURL() {
        when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(new ArrayList<>());

        authorService.listAuthors();

        verify(restTemplate).getForObject("http://localhost:9001/api/authors", List.class);
    }

}
