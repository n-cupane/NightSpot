package com.nighter.nightspot.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryWithoutSpotsDTO {

    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotBlank(message = "Name must be present")
    private String name;



}
