package com.project.tickets.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.project.tickets.entities.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {

}
