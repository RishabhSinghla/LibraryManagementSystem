package com.nagarro.webapp.service.impl;

/**
 * @author rishabhsinghla
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.webapp.model.Author;
import com.nagarro.webapp.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Author> listAuthors() {
		@SuppressWarnings("unchecked")
		List<Author> authors = (List<Author>) this.restTemplate.getForObject("http://localhost:9001/api/authors",
				List.class);
		return authors;
	}

}
