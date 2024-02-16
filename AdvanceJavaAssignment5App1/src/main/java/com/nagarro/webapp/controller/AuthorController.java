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

import com.nagarro.webapp.dao.AuthorRepository;
import com.nagarro.webapp.exception.ResourceNotFoundException;
import com.nagarro.webapp.model.Author;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

	@Autowired
	private AuthorRepository authorRepository;

	// get all authors
	@GetMapping
	public List<Author> getAllAuthors() {
		return this.authorRepository.findAll();
	}

	// create author
	@PostMapping
	public Author createAuthor(@RequestBody Author author) {
		return this.authorRepository.save(author);
	}

	// update author
	@PutMapping("/{id}")
	public Author updateAuthor(@RequestBody Author author, @PathVariable("id") long authorId) {
		Author existingAuthor = this.authorRepository.findById(authorId)
				.orElseThrow(() -> new ResourceNotFoundException("Author not found with id :" + authorId));
		existingAuthor.setAuthorName(author.getAuthorName());
		return this.authorRepository.save(existingAuthor);
	}

	// delete author
	@DeleteMapping("/{id}")
	public ResponseEntity<Author> deleteAuthor(@PathVariable("id") long authorId) {
		Author existingAuthor = this.authorRepository.findById(authorId)
				.orElseThrow(() -> new ResourceNotFoundException("Author not found with id :" + authorId));
		this.authorRepository.delete(existingAuthor);
		return ResponseEntity.ok().build();
	}

}
