package com.nighter.nightspot.dto.ticket;

import com.nighter.nightspot.dto.spot.SpotWithoutCategoryDTO;
import com.nighter.nightspot.dto.user.UserWithoutTicketsDTO;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDTO {

    private Long id;
    private UserWithoutTicketsDTO user;
    private SpotWithoutCategoryDTO spot;
    private String description;

}
