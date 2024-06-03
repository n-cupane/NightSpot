package com.nighter.nightspot.service.definition;

import com.nighter.nightspot.dto.category.CategoryWithSpotsDTO;
import com.nighter.nightspot.dto.category.CategoryWithoutSpotsDTO;
import com.nighter.nightspot.dto.category.InsertCategoryDTO;
import com.nighter.nightspot.dto.category.UpdateCategoryDTO;
import com.nighter.nightspot.dto.spot.InsertSpotDTO;
import com.nighter.nightspot.dto.spot.SpotWithCategoryDTO;
import com.nighter.nightspot.dto.spot.SpotWithoutCategoryDTO;
import com.nighter.nightspot.dto.spot.UpdateSpotDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.models.Spot;

import java.util.List;

public interface SpotService {

    SpotWithCategoryDTO findByIdWithCategory(Long aLong) throws NoResultException;

    SpotWithoutCategoryDTO findByIdWithoutCategory(Long aLong) throws NoResultException;

    List<SpotWithCategoryDTO> findAllWithCategory() throws NoResultException;

    List<SpotWithoutCategoryDTO> findAlWithoutCategory() throws NoResultException;
    void update(UpdateSpotDTO updateSpotDTO) throws Exception;

    void insert(InsertSpotDTO insertSpotDTO) throws Exception;

    void deleteById(Long aLong) throws NoResultException;

    SpotWithoutCategoryDTO findByName(String name) throws NoResultException;

    List<SpotWithoutCategoryDTO> findAllByCategoryId(Long id);


}
