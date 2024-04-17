package com.nighter.nightspot.service.implementation;

import com.nighter.nightspot.dto.category.CategoryWithSpotsDTO;
import com.nighter.nightspot.dto.category.CategoryWithoutSpotsDTO;
import com.nighter.nightspot.dto.category.InsertCategoryDTO;
import com.nighter.nightspot.dto.category.UpdateCategoryDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.mapper.CategoryMapper;
import com.nighter.nightspot.repository.CategoryRepositoryJPA;
import com.nighter.nightspot.service.definition.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceJPA implements CategoryService {

    @Autowired
    private CategoryRepositoryJPA categoryRepositoryJPA;

    private final CategoryMapper categoryMapper;
    @Override
    public CategoryWithSpotsDTO findByIdWithSpots(Long aLong) throws NoResultException {
        return categoryMapper.toCategoryDTOWithSpots(categoryRepositoryJPA.findById(aLong).orElseThrow(()-> new NoResultException("Category with id " + aLong + " not found")));
    }

    @Override
    public CategoryWithoutSpotsDTO findByIdWithoutSpots(Long aLong) throws NoResultException {
        return categoryMapper.toCategoryDTOWithoutSpots(categoryRepositoryJPA.findById(aLong).orElseThrow(()-> new NoResultException("Category with id " + aLong + " not found")));
    }

    @Override
    public List<CategoryWithSpotsDTO> findAllWithSpots() throws NoResultException {
        return categoryRepositoryJPA.findAll().stream().map(t-> categoryMapper.toCategoryDTOWithSpots(t)).toList();
    }

    @Override
    public List<CategoryWithoutSpotsDTO> findAllWithoutSpots() throws NoResultException {
        return categoryRepositoryJPA.findAll().stream().map(t-> categoryMapper.toCategoryDTOWithoutSpots(t)).toList();
    }

    @Override
    public void update(UpdateCategoryDTO updateCategoryDTO) throws Exception {
        categoryRepositoryJPA.save(categoryMapper.fromUpdateCategoryDTO(updateCategoryDTO));
    }

    @Override
    public void insert(InsertCategoryDTO insertCategoryDTO) throws Exception {
        categoryRepositoryJPA.save(categoryMapper.fromInsertCategoryDTO(insertCategoryDTO));
    }

    @Override
    public void deleteById(Long aLong) throws Exception {
        categoryRepositoryJPA.deleteById(aLong);
    }
}
