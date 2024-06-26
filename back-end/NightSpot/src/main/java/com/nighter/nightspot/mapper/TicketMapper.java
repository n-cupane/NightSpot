package com.nighter.nightspot.mapper;

import com.nighter.nightspot.dto.ticket.InsertTicketDTO;
import com.nighter.nightspot.dto.ticket.TicketDTO;
import com.nighter.nightspot.dto.ticket.UpdateTicketDTO;
import com.nighter.nightspot.models.Ticket;
import com.nighter.nightspot.service.implementation.UserServiceJPA;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface TicketMapper {

    TicketDTO toTicketDTO(Ticket ticket);

    Ticket fromInsertTicketDTO(InsertTicketDTO ticket);

    Ticket fromUpdateTicketDTO(UpdateTicketDTO ticket);

}
