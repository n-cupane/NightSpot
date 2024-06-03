package com.nighter.nightspot.dto.ticket;

import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertTicketDTO {

    @NotNull(message = "user cannot be null")
    private User user;

    @NotNull(message = "spot cannot be null")
    private Spot spot;

    @NotBlank(message = "description must be present")
    private String description;

}
