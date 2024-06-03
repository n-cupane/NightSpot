package com.nighter.nightspot.dto.product;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductDTO {

    @NotNull(message = "id cannot be null")
    private Long id;

    @NotNull(message = "name cannot be null")
    private String name;

}
