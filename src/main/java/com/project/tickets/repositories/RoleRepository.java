package com.project.tickets.repositories;

import com.project.tickets.entities.Role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role getRoleByName(String name);
}
