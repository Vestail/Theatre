package com.vitaliivitrenko.theatre.model.data.jpa;

import com.vitaliivitrenko.theatre.model.data.TicketRepository;
import com.vitaliivitrenko.theatre.model.domain.Ticket;
import org.springframework.stereotype.Repository;

@Repository
public class JpaTicketRepository extends JpaCrudRepository<Ticket, Long> implements TicketRepository {

    public JpaTicketRepository() {
        super(Ticket.class);
    }
}
