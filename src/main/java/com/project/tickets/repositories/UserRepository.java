package com.project.tickets.repositories;

import com.project.tickets.entities.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User getUserByUsername(String username);
}
