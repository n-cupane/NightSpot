package com.nighter.nightspot.dto.photo;

import com.nighter.nightspot.models.Spot;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePhotoDTO {

    @NotNull(message = "id cannot be null")
    private Long id;

    private String path;

    @NotNull(message = "spot cannot be null")
    private Spot spot;
}
