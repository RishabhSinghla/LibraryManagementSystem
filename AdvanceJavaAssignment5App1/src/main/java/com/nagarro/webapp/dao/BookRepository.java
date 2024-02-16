package com.nagarro.webapp.dao;

/**
 * @author rishabhsinghla
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.webapp.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	Book findByBookCode(long bookCode);
}
