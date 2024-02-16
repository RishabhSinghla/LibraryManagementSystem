package com.nagarro.webapp.controller;

/**
 * @author rishabhsinghla
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nagarro.webapp.dao.AuthorRepository;
import com.nagarro.webapp.exception.ResourceNotFoundException;
import com.nagarro.webapp.model.Author;

public class AuthorControllerTest {

	@Mock
	private AuthorRepository authorRepository;

	@InjectMocks
	private AuthorController authorController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllAuthors() {
		List<Author> authors = new ArrayList<>();
		authors.add(new Author(1, "Author 1"));
		authors.add(new Author(2, "Author 2"));

		when(authorRepository.findAll()).thenReturn(authors);

		List<Author> result = authorController.getAllAuthors();

		assertEquals(authors.size(), result.size());
	}

	@Test
	public void testCreateAuthor() {
		Author author = new Author(1, "Author 1");

		when(authorRepository.save(any(Author.class))).thenReturn(author);

		Author createdAuthor = authorController.createAuthor(author);

		assertEquals(author.getAuthorName(), createdAuthor.getAuthorName());
	}

	@Test
	public void testUpdateAuthor() {
		Author author = new Author(1, "Author 1");
		Author updatedAuthor = new Author(1, "Updated Author 1");

		when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
		when(authorRepository.save(author)).thenReturn(updatedAuthor);

		Author result = authorController.updateAuthor(updatedAuthor, 1L);

		assertEquals(updatedAuthor.getAuthorName(), result.getAuthorName());
	}

	@Test
	public void testDeleteAuthor() {
		Author author = new Author(1, "Author 1");

		when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

		ResponseEntity<Author> response = authorController.deleteAuthor(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
    public void testDeleteAuthor_NotFound() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            authorController.deleteAuthor(1L);
        } catch (ResourceNotFoundException e) {
            assertEquals("Author not found with id :1", e.getMessage());
        }
    }

	@Test
	public void testUpdateAuthor_NotFound() {
	    when(authorRepository.findById(1L)).thenReturn(Optional.empty());
	    
	    try {
	        authorController.updateAuthor(new Author(1, "Updated Author 1"), 1L);
	    } catch (ResourceNotFoundException e) {
	        assertEquals("Author not found with id :1", e.getMessage());
	    }
	}

	@Test
	public void testDeleteAuthor_Success() {
		Author author = new Author(1, "Author 1");
		when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

		ResponseEntity<Author> response = authorController.deleteAuthor(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
