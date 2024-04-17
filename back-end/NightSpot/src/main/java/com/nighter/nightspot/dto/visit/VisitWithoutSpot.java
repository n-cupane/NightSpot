package com.nighter.nightspot.dto.visit;

import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.models.VisitKey;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class VisitWithoutSpot {
    @NotNull(message = "id cannot be null")
    private VisitKey id;
    @NotNull(message = "user cannot be null")
    private User user;
    @PastOrPresent
    private LocalDate visitDate;
    @NotNull(message = "visit time cannot be null")
    private LocalTime visitTime;
}
