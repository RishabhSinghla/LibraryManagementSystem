package com.nagarro.webapp.model;

/**
 * @author rishabhsinghla
 */

public class Book {

	private long bookId;
	private long bookCode;
	private String bookName;
	private String authorName;
	private String createdDate;

	public Book(long bookId, long bookCode, String bookName, String authorName, String createdDate) {
		super();
		this.bookId = bookId;
		this.bookCode = bookCode;
		this.bookName = bookName;
		this.authorName = authorName;
		this.createdDate = createdDate;
	}

	public Book() {
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookCode=" + bookCode + ", bookName=" + bookName + ", authorName="
				+ authorName + ", createdDate=" + createdDate + "]";
	}

}
