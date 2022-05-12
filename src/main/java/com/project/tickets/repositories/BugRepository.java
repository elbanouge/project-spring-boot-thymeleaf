package com.project.tickets.repositories;

import com.project.tickets.entities.Bug;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BugRepository extends JpaRepository<Bug, Long> {

}
