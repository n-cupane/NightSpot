package com.nighter.nightspot.dto.ticket;

import com.nighter.nightspot.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketWithoutSpotDTO {

    private Long id;

    private User user;

    private String description;

}
