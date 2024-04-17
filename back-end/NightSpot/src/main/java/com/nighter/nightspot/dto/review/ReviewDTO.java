package com.nighter.nightspot.dto.review;

import com.nighter.nightspot.dto.spot.SpotWithCategoryDTO;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewDTO {


    @NotNull(message = "Id cannot be null")
    private Long id;
    @NotNull(message = "User cannot be null")
    private User user;
    @NotNull(message = "Spot cannot be null")
    private SpotWithCategoryDTO spot;
    @NotBlank(message = "The review text cannot be empty")
    private String text;
    @NotNull(message= "rating cannot be empty")
    private Integer rating;
    @NotNull(message= "date cannot be empty")
    private LocalDate date;

}
