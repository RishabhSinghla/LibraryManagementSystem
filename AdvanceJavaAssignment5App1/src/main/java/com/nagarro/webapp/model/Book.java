package com.nagarro.webapp.model;

/**
 * @author rishabhsinghla
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Book_Id")
	private long bookId;

	@Column(name = "Book_Code")
	private long bookCode;

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "Book_Name")
	private String bookName;

	@Column(name = "Author_Name")
	private String authorName;

	@Column(name = "Created_Date")
	private String createdDate;

	public Book() {
		super();
	}

	public Book(long bookCode, String bookName, String authorName, String createdDate) {
		super();
		this.bookCode = bookCode;
		this.bookName = bookName;
		this.authorName = authorName;
		this.createdDate = createdDate;
	}

	public Book(long bookId, long bookCode, String bookName, String authorName, String createdDate) {
		super();
		this.bookId = bookId;
		this.bookCode = bookCode;
		this.bookName = bookName;
		this.authorName = authorName;
		this.createdDate = createdDate;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public long getBookCode() {
		return bookCode;
	}

	public void setBookCode(long bookCode) {
		this.bookCode = bookCode;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Book(long bookCode, String bookName, String authorName) {
		super();
		this.bookCode = bookCode;
		this.bookName = bookName;
		this.authorName = authorName;
	}

}
