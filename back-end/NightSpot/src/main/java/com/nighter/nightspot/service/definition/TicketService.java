package com.nighter.nightspot.service.definition;

import com.nighter.nightspot.dto.ticket.InsertTicketDTO;
import com.nighter.nightspot.dto.ticket.TicketDTO;
import com.nighter.nightspot.dto.ticket.UpdateTicketDTO;
import com.nighter.nightspot.error.exception.NoResultException;

import java.util.List;

public interface TicketService {

    TicketDTO findById(Long id) throws NoResultException;

    List<TicketDTO> findAll();

    void save(InsertTicketDTO ticket);

    void save(UpdateTicketDTO ticket);

    void deleteById(Long id);

    void closeTicket(Long id);

}
