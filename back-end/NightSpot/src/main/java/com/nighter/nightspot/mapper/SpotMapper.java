package com.nighter.nightspot.mapper;

import com.nighter.nightspot.dto.category.CategoryWithSpotsDTO;
import com.nighter.nightspot.dto.category.CategoryWithoutSpotsDTO;
import com.nighter.nightspot.dto.category.InsertCategoryDTO;
import com.nighter.nightspot.dto.category.UpdateCategoryDTO;
import com.nighter.nightspot.dto.spot.InsertSpotDTO;
import com.nighter.nightspot.dto.spot.SpotWithCategoryDTO;
import com.nighter.nightspot.dto.spot.SpotWithoutCategoryDTO;
import com.nighter.nightspot.dto.spot.UpdateSpotDTO;
import com.nighter.nightspot.models.Category;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.service.implementation.UserServiceJPA;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface SpotMapper {

    public Spot fromSpotWithoutCategoryDTO(SpotWithoutCategoryDTO spotWithoutCategoryDTO);

    public Spot fromSpotWithCategoryDTO(SpotWithCategoryDTO spotWithCategoryDTO);

    public SpotWithCategoryDTO toSpotDTOWithCategory(Spot spot);

    public SpotWithoutCategoryDTO toSpotDTOWithoutCategory(Spot spot);

    public Spot fromInsertSpotDTO(InsertSpotDTO insertSpotDTO);

    public Spot fromUpdateSpotDTO(UpdateSpotDTO updateSpotDTO);

}
