package com.nighter.nightspot.dto.category;

import com.nighter.nightspot.dto.spot.SpotWithoutCategoryDTO;
import com.nighter.nightspot.models.Spot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class UpdateCategoryDTO {

    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotBlank(message = "Name must be present")
    private String name;

    private List<SpotWithoutCategoryDTO> spots;

}
