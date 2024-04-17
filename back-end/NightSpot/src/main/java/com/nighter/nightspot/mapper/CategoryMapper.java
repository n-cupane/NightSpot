package com.nighter.nightspot.mapper;

import com.nighter.nightspot.dto.category.CategoryWithSpotsDTO;
import com.nighter.nightspot.dto.category.CategoryWithoutSpotsDTO;
import com.nighter.nightspot.dto.category.InsertCategoryDTO;
import com.nighter.nightspot.dto.category.UpdateCategoryDTO;
import com.nighter.nightspot.models.Category;
import com.nighter.nightspot.service.implementation.UserServiceJPA;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface CategoryMapper {

    public Category fromCategoryWithoutSpotsDTO(CategoryWithoutSpotsDTO categoryWithoutSpotsDTO);

    public Category fromCategoryWithSpotsDTO(CategoryWithSpotsDTO categoryWithSpotsDTO);

    public CategoryWithSpotsDTO toCategoryDTOWithSpots(Category category);

    public CategoryWithoutSpotsDTO toCategoryDTOWithoutSpots(Category category);

    public Category fromInsertCategoryDTO(InsertCategoryDTO insertCategoryDTO);

    public Category fromUpdateCategoryDTO(UpdateCategoryDTO updateCategoryDTO);

}
