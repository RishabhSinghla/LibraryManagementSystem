package com.nagarro.webapp.controller;

/**
 * @author rishabhsinghla
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import com.nagarro.webapp.dao.BookRepository;
import com.nagarro.webapp.exception.ResourceNotFoundException;
import com.nagarro.webapp.model.Book;

public class BookControllerTest {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookController bookController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllBooks() {
		List<Book> books = new ArrayList<>();
		books.add(new Book(1, "Book 1", "Author 1", "2024-02-02"));
		books.add(new Book(2, "Book 2", "Author 2", "2024-02-02"));

		when(bookRepository.findAll()).thenReturn(books);

		List<Book> result = bookController.getAllAuthors();

		assertEquals(books.size(), result.size());
	}

	@Test
	public void testGetBookById() {
		Book book = new Book(1, "Book 1", "Author 1", "2024-02-02");

		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

		Book result = bookController.getBookById(1L);

		assertEquals(book.getBookName(), result.getBookName());
	}

	@Test
	public void testGetBookByCode() {
		Book book = new Book(1, "Book 1", "Author 1", "2024-02-02");

		when(bookRepository.findByBookCode(1L)).thenReturn(book);

		Book result = bookController.getBookByCode(1L);

		assertEquals(book.getBookName(), result.getBookName());
	}

	@Test
	public void testCreateBook() {
		Book book = new Book(1, "Book 1", "Author 1", "2024-02-02");

		when(bookRepository.save(any(Book.class))).thenReturn(book);

		Book createdBook = bookController.createBook(book);

		assertEquals(book.getBookName(), createdBook.getBookName());
	}

	@Test
	public void testUpdateBook() {
		Book book = new Book(1, "Book 1", "Author 1", "2024-02-02");
		Book updatedBook = new Book(1, "Updated Book 1", "Author 1", "2024-02-02");

		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
		when(bookRepository.save(book)).thenReturn(updatedBook);

		Book result = bookController.updateBook(updatedBook, 1L);

		assertEquals(updatedBook.getBookName(), result.getBookName());
	}

	@Test
	public void testDeleteBook() {
		Book book = new Book(1, "Book 1", "Author 1", "2024-02-02");

		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

		ResponseEntity<Book> response = bookController.deleteBook(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
    public void testDeleteBook_NotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            bookController.deleteBook(1L);
        } catch (ResourceNotFoundException e) {
            assertEquals("Book not found with id :1", e.getMessage());
        }
    }

	@Test
	public void testGetBookById_NotFound() {
	    when(bookRepository.findById(1L)).thenReturn(Optional.empty());
	    
	    try {
	        bookController.getBookById(1L);
	    } catch (ResourceNotFoundException e) {
	        assertEquals("Book not found with id :1", e.getMessage());
	    }
	}

	@Test
	public void testGetBookByCode_NotFound() {
	    when(bookRepository.findByBookCode(1L)).thenReturn(null);
	    
	    Book result = bookController.getBookByCode(1L);
	    
	    assertNull(result);
	}

	@Test
	public void testUpdateBook_NotFound() {
	    when(bookRepository.findById(1L)).thenReturn(Optional.empty());
	    
	    try {
	        bookController.updateBook(new Book(1, "Updated Book 1", "Author 1", "2024-02-02"), 1L);
	    } catch (ResourceNotFoundException e) {
	        assertEquals("Book not found with id :1", e.getMessage());
	    }
	}

}
