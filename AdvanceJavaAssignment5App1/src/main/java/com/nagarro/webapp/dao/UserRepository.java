package com.nagarro.webapp.dao;

/**
 * @author rishabhsinghla
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.webapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByName(String username);

	public User findByNameAndPassword(String username, String password);

}
