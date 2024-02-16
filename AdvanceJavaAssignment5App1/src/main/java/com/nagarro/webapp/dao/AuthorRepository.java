package com.nagarro.webapp.dao;

/**
 * @author rishabhsinghla
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.webapp.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
