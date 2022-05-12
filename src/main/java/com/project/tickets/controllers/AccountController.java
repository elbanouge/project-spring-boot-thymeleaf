package com.project.tickets.controllers;

import javax.annotation.PostConstruct;

import com.project.tickets.entities.Role;
import com.project.tickets.entities.User;
import com.project.tickets.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostConstruct
    public void init() {
        System.out.println("AccountController initialized");
        // accountService.createNewRole(new Role(null, "ADMIN"));
        // accountService.createNewRole(new Role(null, "DEVELOPER"));
        // accountService.createNewRole(new Role(null, "CLIENT"));

        // accountService.createNewUser(new User(null, "admin", "admin123", true,
        // null));
        // accountService.createNewUser(new User(null, "client", "client123", true,
        // null));
        // accountService.createNewUser(new User(null, "developer", "dev123", true,
        // null));

        // accountService.addRoleToUser("admin", "ADMIN");
        // accountService.addRoleToUser("client", "CLIENT");
        // accountService.addRoleToUser("developer", "DEVELOPER");
    }

}
