package com.nighter.nightspot.controller;

import com.nighter.nightspot.dto.ticket.InsertTicketDTO;
import com.nighter.nightspot.dto.ticket.TicketDTO;
import com.nighter.nightspot.dto.ticket.UpdateTicketDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.models.Ticket;
import com.nighter.nightspot.service.definition.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/auth/ticket/insert")
    public ResponseEntity<Void> insertTicket(@Valid @RequestBody InsertTicketDTO ticket) {
        try {
            ticketService.save(ticket);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/ticket/update")
    public ResponseEntity<Void> updateTicket(@Valid @RequestBody UpdateTicketDTO ticket) {
        try {
            ticketService.save(ticket);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/auth/ticket/show/id/{id}")
    public ResponseEntity<TicketDTO> showTicket(@PathVariable Long id) throws NoResultException {
        TicketDTO ticket = ticketService.findById(id);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/admin/ticket/show-all")
    public ResponseEntity<List<TicketDTO>> showAllTickets() {
        List<TicketDTO> tickets = ticketService.findAll();
        return ResponseEntity.ok(tickets);
    }

    @DeleteMapping("/ticket/delete/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) throws NoResultException {
        ticketService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/ticket/close/{ticketId}")
    public ResponseEntity<Void> closeTicket(@PathVariable Long ticketId) {
        ticketService.closeTicket(ticketId);
        return ResponseEntity.ok().build();
    }
}