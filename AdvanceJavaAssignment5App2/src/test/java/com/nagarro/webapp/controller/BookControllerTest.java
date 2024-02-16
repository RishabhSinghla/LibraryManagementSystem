package com.nagarro.webapp.controller;

/**
 * @author rishabhsinghla
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.nagarro.webapp.model.Author;
import com.nagarro.webapp.model.Book;
import com.nagarro.webapp.model.User;
import com.nagarro.webapp.service.AuthorService;
import com.nagarro.webapp.service.BookService;

public class BookControllerTest {

	@Mock
	private BookService bookService;

	@Mock
	private AuthorService authorService;

	@InjectMocks
	private BookController bookController;

	@Mock
	private HttpSession session;

	@Mock
	private Model model;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
    public void testShowBookListPage() {
        when(session.getAttribute("user")).thenReturn(new User());

        List<Book> books = new ArrayList<>();
        when(bookService.listBooks()).thenReturn(books);

        String result = bookController.showBookListPage(model, session);

        verify(model).addAttribute("books", books);
        assertEquals("bookList", result);
    }

	@Test
    public void testShowBookListPage_WhenUserIsNotLoggedIn_ShouldReturnLoginPage() {
        
        when(session.getAttribute("user")).thenReturn(null);

        String result = bookController.showBookListPage(model, session);
        
        assertEquals("login", result);
        verify(model).addAttribute("message", "You're not logged in, Login First");
    }

	@Test
    public void testShowAddForm_WhenUserIsLoggedIn_ShouldReturnAddBookFormPage() {
        
        when(session.getAttribute("user")).thenReturn(new User());

        List<Author> authors = new ArrayList<>();
        when(authorService.listAuthors()).thenReturn(authors);

        String result = bookController.showAddForm(model, session);
        
        verify(model).addAttribute("authors", authors);
        assertEquals("addBookForm", result);
    }

	@Test
    public void testShowAddForm_WhenUserIsNotLoggedIn_ShouldReturnLoginPage() {
        
        when(session.getAttribute("user")).thenReturn(null);
    
        String result = bookController.showAddForm(model, session);
        
        assertEquals("login", result);
        verify(model).addAttribute("message", "You're not logged in, Login First");
    }

	@Test
	public void testHandleForm_WhenBookCodeIsNotUnique_ShouldReturnAddBookFormPageWithErrorMessage() {

		Book existingBook = new Book();
		when(bookService.getBookByBookCode(existingBook.getBookCode())).thenReturn(existingBook);

		Book book = new Book();

		String result = bookController.handleForm(book, model);

		verify(model).addAttribute("message", "Book code should be unique!!");
		verify(model).addAttribute("existingBook", existingBook);
		assertEquals("addBookForm", result);
	}

	@Test
	public void testHandleForm_WhenBookCodeIsUnique_ShouldAddBookAndRedirectToBookListPage() {

		Book book = new Book();
		when(bookService.getBookByBookCode(book.getBookCode())).thenReturn(null);

		String result = bookController.handleForm(book, model);

		verify(bookService).addBook(book);
		assertEquals("redirect:/bookList", result);
	}

	@Test
	public void testHandleEditForm_WhenBookExists_ShouldRedirectToEditFormPage() {

		long bookId = 1L;
		Book book = new Book();
		when(bookService.getBookById(bookId)).thenReturn(book);

		String result = bookController.handleEditForm(bookId, session);

		verify(session).setAttribute("book", book);
		assertEquals("redirect:/editform", result);
	}

	@Test
	public void testHandleDelete_WhenBookExists_ShouldRemoveBookAndRedirectToBookListPage() {

		long bookId = 1L;

		String result = bookController.handleDelete(bookId);

		verify(bookService).removeBook(bookId);
		assertEquals("redirect:/bookList", result);
	}

	@Test
	public void testHandleForm_WhenBookCodeIsNotUnique_ShouldReturnToAddBookFormWithErrorMessage() {

		Book book = new Book();
		when(bookService.getBookByBookCode(book.getBookCode())).thenReturn(new Book());

		String result = bookController.handleForm(book, model);

		verify(bookService, never()).addBook(book);
		verify(model).addAttribute("message", "Book code should be unique!!");
		assertEquals("addBookForm", result);
	}
	
	@Test
    public void testShowEditForm_WhenUserIsLoggedIn_ShouldReturnEditBookFormPage() {
        when(session.getAttribute("user")).thenReturn(new User());

        List<Author> authors = new ArrayList<>();
        when(authorService.listAuthors()).thenReturn(authors);

        String result = bookController.showEditForm(model, session);

        verify(model).addAttribute("authors", authors);
        assertEquals("editBookForm", result);
    }

    @Test
    public void testShowEditForm_WhenUserIsNotLoggedIn_ShouldReturnLoginPage() {
        when(session.getAttribute("user")).thenReturn(null);

        String result = bookController.showEditForm(model, session);

        assertEquals("login", result);
        verify(model).addAttribute("message", "You're not logged in, Login First");
    }

    @Test
    public void testUpdateBook_ShouldRedirectToBookListPage() {
        Book book = new Book();

        String result = bookController.updateBook(book);

        verify(bookService).updateBook(book);
        assertEquals("redirect:/bookList", result);
    }

}
