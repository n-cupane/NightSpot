package com.nighter.nightspot.dto.visit;

import com.nighter.nightspot.dto.spot.SpotWithoutCategoryDTO;
import com.nighter.nightspot.dto.user.UserWithoutListsDTO;
import com.nighter.nightspot.dto.user.UserWithoutVisitsDTO;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.models.VisitKey;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class VisitDTO {

    private VisitKey id;

    private UserWithoutListsDTO user;

    private SpotWithoutCategoryDTO spot;

    private LocalDate visitDate;

    private LocalTime visitTime;

}
