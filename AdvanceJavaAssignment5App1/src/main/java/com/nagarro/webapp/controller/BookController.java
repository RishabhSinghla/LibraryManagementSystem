package com.nagarro.webapp.controller;

/**
 * @author rishabhsinghla
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.webapp.dao.BookRepository;
import com.nagarro.webapp.exception.ResourceNotFoundException;
import com.nagarro.webapp.model.Book;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookRepository bookrepository;

	// get all books
	@GetMapping
	public List<Book> getAllAuthors() {
		return this.bookrepository.findAll();
	}

	// get book by id
	@GetMapping("/{id}")
	public Book getBookById(@PathVariable("id") long bookId) {
		return this.bookrepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found with id :" + bookId));
	}

	// get book by bookCode
	@GetMapping("/book/{code}")
	public Book getBookByCode(@PathVariable("code") long bookCode) {
		return this.bookrepository.findByBookCode(bookCode);
	}

	// add a book
	@PostMapping
	public Book createBook(@RequestBody Book book) {
		return this.bookrepository.save(book);
	}

	// update book
	@PutMapping("/{id}")
	public Book updateBook(@RequestBody Book book, @PathVariable("id") long bookId) {
		Book existingBook = this.bookrepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found with id :" + bookId));
		existingBook.setBookName(book.getBookName());
		existingBook.setAuthorName(book.getAuthorName());
		existingBook.setCreatedDate(book.getCreatedDate());
		return this.bookrepository.save(existingBook);
	}

	// delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable("id") long bookId) {
		Book existingBook = this.bookrepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found with id :" + bookId));
		this.bookrepository.delete(existingBook);
		return ResponseEntity.ok().build();
	}

}
