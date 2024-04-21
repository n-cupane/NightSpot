package com.nighter.nightspot.dto.photo;

import com.nighter.nightspot.models.Spot;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertPhotoDTO {

    private byte[] photo;

    @NotNull(message = "spot cannot be null")
    private Spot spot;

}
