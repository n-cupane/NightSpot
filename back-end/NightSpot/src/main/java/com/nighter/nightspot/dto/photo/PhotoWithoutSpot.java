package com.nighter.nightspot.dto.photo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoWithoutSpot {

    @NotNull(message = "id cannot be null")
    private Long id;

    private String path;
}
