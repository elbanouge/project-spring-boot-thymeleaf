package com.project.tickets.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.project.tickets.entities.Role;
import com.project.tickets.entities.Ticket;
import com.project.tickets.entities.User;
import com.project.tickets.repositories.TicketRepository;
import com.project.tickets.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    public void save(Ticket ticket) {
        User user = userRepository.getUserByUsername(userConnected());

        System.out.println("*****" + user.toString());
        ticket.getUsers().add(user);
        ticketRepository.save(ticket);
    }

    public void update(Ticket ticket, Set<User> users) {
        ticket.getUsers().addAll(users);
        ticketRepository.save(ticket);
    }

    public List<Ticket> listAll() {
        List<Ticket> tickets_users = new ArrayList<>();
        User user = userRepository.getUserByUsername(userConnected());
        for (Role role : user.getRoles()) {
            if (role.getName().equals("CLIENT")) {
                tickets_users = listTickets(role.getName());
            } else if (role.getName().equals("DEVELOPER")) {
                tickets_users = listTickets(role.getName());
            } else if (role.getName().equals("ADMIN")) {
                tickets_users = (List<Ticket>) ticketRepository.findAll();
            }
        }
        return tickets_users;
    }

    public Ticket get(Long id) {
        return ticketRepository.findById(id).get();
    }

    public void delete(Long id) {
        Ticket ticket = ticketRepository.findById(id).get();
        System.out.println("*****" + ticket.toString());
        if (ticket != null) {
            ticketRepository.delete(ticket);
        } else {
            System.out.println("Ticket not found");
        }
    }

    public String userConnected() {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    public List<Ticket> listTickets(String roleName) {
        List<Ticket> tickets = (List<Ticket>) ticketRepository.findAll();
        List<Ticket> tickets_users = new ArrayList<>();
        for (Ticket ticket : tickets) {
            for (User user : ticket.getUsers()) {
                if (user.getUsername().equals(userConnected())) {
                    for (Role role : user.getRoles()) {
                        if (role.getName().equals(roleName)) {
                            tickets_users.add(ticket);
                        }
                    }
                }
            }
        }
        return tickets_users;
    }
}
