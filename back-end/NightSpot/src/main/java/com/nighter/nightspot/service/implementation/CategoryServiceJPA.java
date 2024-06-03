package com.nighter.nightspot.service.implementation;

import com.nighter.nightspot.dto.category.CategoryWithSpotsDTO;
import com.nighter.nightspot.dto.category.CategoryWithoutSpotsDTO;
import com.nighter.nightspot.dto.category.InsertCategoryDTO;
import com.nighter.nightspot.dto.category.UpdateCategoryDTO;
import com.nighter.nightspot.dto.spot.SpotWithoutCategoryDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.mapper.CategoryMapper;
import com.nighter.nightspot.mapper.SpotMapper;
import com.nighter.nightspot.models.Category;
import com.nighter.nightspot.models.Spot;
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

    private final SpotMapper spotMapper;

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
        Category c = categoryRepositoryJPA.findById(updateCategoryDTO.getId())
                .orElseThrow(()-> new NoResultException("Category with id " + updateCategoryDTO.getId() + " not found"));
        if(updateCategoryDTO.getSpots()==null) {
            List<Spot> s = c.getSpots();
            List<SpotWithoutCategoryDTO> sw = s.stream().map(t->spotMapper.toSpotDTOWithoutCategory(t)).toList();
            updateCategoryDTO.setSpots(sw);
        }
        categoryRepositoryJPA.save(categoryMapper.fromUpdateCategoryDTO(updateCategoryDTO));
    }

    @Override
    public void insert(InsertCategoryDTO insertCategoryDTO) throws Exception {
        categoryRepositoryJPA.save(categoryMapper.fromInsertCategoryDTO(insertCategoryDTO));
    }

    @Override
    public void deleteById(Long aLong) throws NoResultException {
        categoryRepositoryJPA.deleteById(aLong);
    }

    @Override
    public CategoryWithoutSpotsDTO findByName(String name) throws NoResultException {
        return categoryMapper.toCategoryDTOWithoutSpots(categoryRepositoryJPA.findByName(name)
                .orElseThrow(
                        () -> new NoResultException("Category " + name + "not found")
                )
        );
    }
}
