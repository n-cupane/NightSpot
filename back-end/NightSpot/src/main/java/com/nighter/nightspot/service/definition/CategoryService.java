package com.nighter.nightspot.service.definition;

import com.nighter.nightspot.dto.category.CategoryWithSpotsDTO;
import com.nighter.nightspot.dto.category.CategoryWithoutSpotsDTO;
import com.nighter.nightspot.dto.category.InsertCategoryDTO;
import com.nighter.nightspot.dto.category.UpdateCategoryDTO;
import com.nighter.nightspot.error.exception.NoResultException;

import java.util.List;

public interface CategoryService {

    CategoryWithSpotsDTO findByIdWithSpots(Long aLong) throws NoResultException;

    CategoryWithoutSpotsDTO findByIdWithoutSpots(Long aLong) throws NoResultException;

    List<CategoryWithSpotsDTO> findAllWithSpots() throws NoResultException;

    List<CategoryWithoutSpotsDTO> findAllWithoutSpots() throws NoResultException;
    void update(UpdateCategoryDTO updateActorDTO) throws Exception;

    void insert(InsertCategoryDTO insertActorDTO) throws Exception;

    void deleteById(Long aLong) throws Exception;

}
