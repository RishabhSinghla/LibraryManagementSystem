package com.nagarro.webapp.model;

/**
 * @author rishabhsinghla
 */

public class Author {

	private long authorId;
	private String authorName;

	public Author() {

	}

	public Author(long authorId, String authorName) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
	}

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", authorName=" + authorName + "]";
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

}
