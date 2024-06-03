package com.nighter.nightspot.dto.product;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertProductDTO {

    @NotNull(message = "name cannot be null")
    private String name;

}
