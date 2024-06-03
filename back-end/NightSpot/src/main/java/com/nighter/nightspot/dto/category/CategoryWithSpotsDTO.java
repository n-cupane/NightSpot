package com.nighter.nightspot.dto.category;


import com.nighter.nightspot.dto.spot.SpotWithCategoryDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CategoryWithSpotsDTO {

    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotBlank(message = "Name must be present")
    private String name;

    @NotBlank(message = "Spots must be present")
    List<SpotWithCategoryDTO> spots;

}
