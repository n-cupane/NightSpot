package com.nighter.nightspot.dto.photo;

import com.nighter.nightspot.models.Spot;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoDTO {
    private Long id;
    private byte[] photo;
    private Spot spot;
}
