package com.nighter.nightspot.mapper;

import com.nighter.nightspot.dto.review.InsertReviewDTO;
import com.nighter.nightspot.dto.review.ReviewDTO;
import com.nighter.nightspot.dto.review.UpdateReviewDTO;
import com.nighter.nightspot.dto.spot_product.InsertSpotProductDTO;
import com.nighter.nightspot.dto.spot_product.SpotProductDTO;
import com.nighter.nightspot.dto.spot_product.UpdateSpotProductDTO;
import com.nighter.nightspot.models.Review;
import com.nighter.nightspot.models.Spot_Product;
import com.nighter.nightspot.service.implementation.UserServiceJPA;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring",uses = UserServiceJPA.class)
public interface Spot_ProductMapper {

    public Spot_Product fromSpotProductDTO(SpotProductDTO spotProductDTO);

    public SpotProductDTO toSpotProductDTO(Spot_Product spot_product);

    public Spot_Product fromInsertSpotProductDTO(InsertSpotProductDTO insertSpotProductDTO);

    public Spot_Product fromUpdateSpotProductDTO(UpdateSpotProductDTO updateSpotProductDTO);

}
