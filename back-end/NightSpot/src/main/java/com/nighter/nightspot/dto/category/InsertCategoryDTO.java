package com.nighter.nightspot.dto.category;

import com.nighter.nightspot.models.Spot;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class InsertCategoryDTO {

    @NotBlank(message = "The category name must be present")
    private String name;


}
