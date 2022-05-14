package com.project.tickets.services;

import java.util.ArrayList;
import java.util.List;

import com.project.tickets.entities.Role;
import com.project.tickets.entities.User;
import com.project.tickets.repositories.RoleRepository;
import com.project.tickets.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createNewUser(User user) {
        User userExist = userRepository.getUserByUsername(user.getUsername());

        if (userExist == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            System.out.println("User already exist");
        }
    }

    public void createNewRole(Role role) {
        Role roleExist = roleRepository.getRoleByName(role.getName());
        if (roleExist == null) {
            roleRepository.save(role);
        } else {
            System.out.println("Role already exist");
        }
    }

    public void addRoleToUser(String username, String name) {
        User user = userRepository.getUserByUsername(username);
        Role role = roleRepository.getRoleByName(name);
        boolean bool = user.getRoles().add(role);
        if (bool) {
            userRepository.save(user);
        } else {
            System.out.println("Role already exist");
        }
    }

    public List<User> getUsersByRole(String name) {
        List<User> users_roles = new ArrayList<>();
        List<User> users = (List<User>) userRepository.findAll();
        for (User user : users) {
            for (Role role : user.getRoles()) {
                if (role.getName().equals(name)) {
                    users_roles.add(user);
                }
            }
        }
        return users_roles;
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
}
