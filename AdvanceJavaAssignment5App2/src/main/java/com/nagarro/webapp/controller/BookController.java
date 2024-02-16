package com.nagarro.webapp.controller;

/**
 * @author rishabhsinghla
 */

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nagarro.webapp.model.Author;
import com.nagarro.webapp.model.Book;
import com.nagarro.webapp.model.User;
import com.nagarro.webapp.service.AuthorService;
import com.nagarro.webapp.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private AuthorService authorService;

	@RequestMapping("/bookList")
	public String showBookListPage(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("message", "You're not logged in, Login First");
			return "login";
		}
		List<Book> books = this.bookService.listBooks();
		System.out.println(books);
		model.addAttribute("books", books);
		return "bookList";
	}

	@RequestMapping("/addform")
	public String showAddForm(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("message", "You're not logged in, Login First");
			return "login";
		}
		List<Author> authors = this.authorService.listAuthors();
		model.addAttribute("authors", authors);
		return "addBookForm";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String handleForm(@ModelAttribute("book") Book book, Model model) {
		Book existingBook = this.bookService.getBookByBookCode(book.getBookCode());

		if (existingBook != null) {
			model.addAttribute("message", "Book code should be unique!!");
			List<Author> authors = this.authorService.listAuthors();
			model.addAttribute("existingBook", existingBook);
			model.addAttribute("authors", authors);
			return "addBookForm";
		}
		this.bookService.addBook(book);
		return "redirect:/bookList";

	}

	@RequestMapping("/editform")
	public String showEditForm(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("message", "You're not logged in, Login First");
			return "login";
		}
		List<Author> authors = this.authorService.listAuthors();
		model.addAttribute("authors", authors);
		return "editBookForm";
	}

	@RequestMapping("/edit/{id}")
	public String handleEditForm(@PathVariable("id") long bookId, HttpSession session) {
		Book book = this.bookService.getBookById(bookId);
		session.setAttribute("book", book);
		return "redirect:/editform";

	}

	@RequestMapping("/delete/{id}")
	public String handleDelete(@PathVariable("id") long bookId) {
		this.bookService.removeBook(bookId);
		return "redirect:/bookList";

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateBook(@ModelAttribute("book") Book book) {
		this.bookService.updateBook(book);
		return "redirect:/bookList";

	}

}
