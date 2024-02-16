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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.nagarro.webapp.model.Book;

public class BookServiceImplTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private BookServiceImpl bookService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
    public void testListBooks_ExceptionThrown() {
        when(restTemplate.getForObject(anyString(), eq(List.class))).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> {
            bookService.listBooks();
        });
    }

	@Test
    public void testListBooks_EmptyListReturned() {
        when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(Arrays.asList());

        List<Book> books = bookService.listBooks();

        assertNotNull(books);
        assertTrue(books.isEmpty());
    }

	@Test
    public void testListBooks_ValidRequestURL() {
        when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(Arrays.asList());

        bookService.listBooks();

        verify(restTemplate).getForObject("http://localhost:9001/api/books", List.class);
    }

	@Test
    public void testGetBookById_NotFound() {
        when(restTemplate.getForObject(anyString(), eq(Book.class))).thenReturn(null);

        Book book = bookService.getBookById(1L);

        assertNull(book);
    }

	@Test
	public void testGetBookByBookCode_Success() {
		Book expectedBook = new Book(1L, 110L, "Book 1", "Author 1", "2024-02-02");
		when(restTemplate.getForObject(anyString(), eq(Book.class))).thenReturn(expectedBook);

		Book book = bookService.getBookByBookCode(1L);

		assertEquals(expectedBook, book);
	}

	@Test
    public void testGetBookByBookCode_NotFound() {
        when(restTemplate.getForObject(anyString(), eq(Book.class))).thenReturn(null);

        Book book = bookService.getBookByBookCode(1L);

        assertNull(book);
    }

	@Test
	public void testAddBook_Success() {
		Book bookToAdd = new Book(1L, 110L, "Book 1", "Author 1", "2024-02-02");
		ResponseEntity<Book> responseEntity = new ResponseEntity<>(bookToAdd, HttpStatus.CREATED);
		when(restTemplate.postForEntity(anyString(), any(Book.class), eq(Book.class))).thenReturn(responseEntity);

		bookService.addBook(bookToAdd);

		verify(restTemplate).postForEntity("http://localhost:9001/api/books", bookToAdd, Book.class);
	}

	@Test
	public void testListBooks_Success() {
		List<Book> expectedBooks = new ArrayList<>();
		expectedBooks.add(new Book(1L, 110L, "Book 1", "Author 1", "2024-02-02"));
		expectedBooks.add(new Book(2L, 111L, "Book 2", "Author 2", "2024-02-02"));
		when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(expectedBooks);

		List<Book> books = bookService.listBooks();

		assertEquals(expectedBooks.size(), books.size());
	}

	@Test
	public void testGetBookById_Success() {
		Book expectedBook = new Book(1L, 110L, "Book 1", "Author 1", "2024-02-02");
		when(restTemplate.getForObject(anyString(), eq(Book.class))).thenReturn(expectedBook);

		Book book = bookService.getBookById(1L);

		assertEquals(expectedBook, book);
	}

	@Test
	public void testRemoveBook_Success() {
		long bookId = 1L;
		bookService.removeBook(bookId);

		verify(restTemplate).delete("http://localhost:9001/api/books/" + bookId, Book.class);
	}

}
