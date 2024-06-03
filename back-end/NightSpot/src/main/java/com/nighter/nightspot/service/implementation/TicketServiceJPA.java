package com.nighter.nightspot.service.implementation;

import com.nighter.nightspot.dto.ticket.InsertTicketDTO;
import com.nighter.nightspot.dto.ticket.TicketDTO;
import com.nighter.nightspot.dto.ticket.UpdateTicketDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.mapper.TicketMapper;
import com.nighter.nightspot.models.Ticket;
import com.nighter.nightspot.repository.TicketRepositoryJPA;
import com.nighter.nightspot.service.definition.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceJPA implements TicketService {

    @Autowired
    private TicketRepositoryJPA repo;

    private final TicketMapper mapper;


    @Override
    public TicketDTO findById(Long id) throws NoResultException {
        return mapper.toTicketDTO(
                repo.findById(id)
                        .orElseThrow(
                                () -> new NoResultException("Ticket with id " + id + " not found")
                        )
        );
    }

    @Override
    public List<TicketDTO> findAll() {
        return repo.findAll().stream()
                .map(mapper::toTicketDTO)
                .toList();
    }

    @Override
    public void save(InsertTicketDTO ticket) {
        repo.save(
                mapper.fromInsertTicketDTO(ticket)
        );
    }

    @Override
    public void save(UpdateTicketDTO ticket) {
        repo.save(
                mapper.fromUpdateTicketDTO(ticket)
        );
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void closeTicket(Long id) {
        repo.closeTicket(id);
    }
}
