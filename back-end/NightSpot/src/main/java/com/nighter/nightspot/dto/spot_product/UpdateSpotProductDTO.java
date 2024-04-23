package com.nighter.nightspot.dto.spot_product;

import com.nighter.nightspot.dto.spot.SpotWithCategoryDTO;
import com.nighter.nightspot.models.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateSpotProductDTO {

    @NotBlank(message = "Id cannot be blank")
    private Long id;

    @NotNull(message = "Spot cannot be null")
    private SpotWithCategoryDTO spot;

    @NotNull(message = "Product cannot be null")
    private Product product;

    @NotNull(message = "Price cannot be null")
    private Double price;

}
