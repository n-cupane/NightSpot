package com.nighter.nightspot.repository;

import com.nighter.nightspot.models.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepositoryJPA extends JpaRepository<Ticket, Long> {

    @Modifying
    @Transactional
    @Query("update Ticket t set t.solved = true where t.id = :ticketId")
    public void closeTicket(Long ticketId);

}
