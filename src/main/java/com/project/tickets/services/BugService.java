package com.project.tickets.services;

import java.util.List;

import com.project.tickets.entities.Bug;
import com.project.tickets.repositories.BugRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BugService {
	@Autowired
	private BugRepository repo;

	public List<Bug> listAll() {
		return repo.findAll();
	}

	public void save(Bug product) {
		repo.save(product);
	}

	public Bug get(Long id) {
		return repo.findById(id).get();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}
