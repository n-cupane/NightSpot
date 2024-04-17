package com.nighter.nightspot.dto.spot;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SpotWithoutCategoryDTO {

    @NotBlank(message = "Id cannot be blank")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Position cannot be blank")
    private String position;

    @NotBlank(message = "Contact cannot be blank")
    private String contact;

    @NotBlank(message = "Timetables cannot be blank")
    private  String timetables;


}
