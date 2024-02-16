package com.nagarro.webapp.service;

/**
 * @author rishabhsinghla
 */

import java.util.List;

import com.nagarro.webapp.model.Book;

public interface BookService {

	public void addBook(Book book);

	public void updateBook(Book book);

	public List<Book> listBooks();

	public Book getBookById(long bookId);

	public Book getBookByBookCode(long bookCode);

	public void removeBook(long bookId);

}
