package com.nighter.nightspot.dto.ticket;

import com.nighter.nightspot.dto.spot.SpotWithoutCategoryDTO;
import com.nighter.nightspot.models.Spot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketWithoutUserDTO {

    private Long id;

    private SpotWithoutCategoryDTO spot;

    private String description;

    private Boolean solved;

}
