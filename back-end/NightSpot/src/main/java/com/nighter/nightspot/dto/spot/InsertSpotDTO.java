package com.nighter.nightspot.dto.spot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nighter.nightspot.dto.category.CategoryWithoutSpotsDTO;
import com.nighter.nightspot.models.Category;
import com.nighter.nightspot.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class InsertSpotDTO {



    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Position cannot be blank")
    private String position;

    @NotBlank(message = "Contact cannot be blank")
    private String contact;

    @NotBlank(message = "Timetables cannot be blank")
    private  String timetables;

    @NotNull(message = "Category cannot be null")
    private CategoryWithoutSpotsDTO category;



}
