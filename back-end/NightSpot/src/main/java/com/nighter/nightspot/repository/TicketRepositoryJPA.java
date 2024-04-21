package com.nighter.nightspot.repository;

import com.nighter.nightspot.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepositoryJPA extends JpaRepository<Ticket, Long> {
}
