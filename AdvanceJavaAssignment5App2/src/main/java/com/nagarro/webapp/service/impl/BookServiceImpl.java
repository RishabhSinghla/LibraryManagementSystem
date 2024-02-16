package com.nagarro.webapp.service.impl;

/**
 * @author rishabhsinghla
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.webapp.model.Book;
import com.nagarro.webapp.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void addBook(Book book) {
		this.restTemplate.postForEntity("http://localhost:9001/api/books", book, Book.class);

	}

	@Override
	public void updateBook(Book book) {
		this.restTemplate.put("http://localhost:9001/api/books/" + book.getBookId(), book, Book.class);

	}

	@Override
	public List<Book> listBooks() {
		@SuppressWarnings("unchecked")
		List<Book> books = (List<Book>) this.restTemplate.getForObject("http://localhost:9001/api/books", List.class);
		return books;
	}

	@Override
	public Book getBookById(long bookId) {
		Book book = this.restTemplate.getForObject("http://localhost:9001/api/books/" + bookId, Book.class);
		return book;
	}

	@Override
	public void removeBook(long bookId) {
		this.restTemplate.delete("http://localhost:9001/api/books/" + bookId, Book.class);

	}

	@Override
	public Book getBookByBookCode(long bookCode) {
		Book book = this.restTemplate.getForObject("http://localhost:9001/api/books/book/" + bookCode, Book.class);
		return book;
	}

}
