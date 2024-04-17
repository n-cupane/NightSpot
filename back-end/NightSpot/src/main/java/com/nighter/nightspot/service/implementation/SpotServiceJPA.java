package com.nighter.nightspot.service.implementation;

import com.nighter.nightspot.dto.category.InsertCategoryDTO;
import com.nighter.nightspot.dto.spot.SpotWithCategoryDTO;
import com.nighter.nightspot.dto.spot.SpotWithoutCategoryDTO;
import com.nighter.nightspot.dto.spot.UpdateSpotDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.mapper.SpotMapper;
import com.nighter.nightspot.repository.SpotRepositoryJPA;
import com.nighter.nightspot.service.definition.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SpotServiceJPA implements SpotService {

    private  final SpotRepositoryJPA repository;

    private final SpotMapper spotMapper;
    @Override
    public SpotWithCategoryDTO findByIdWithCategory(Long aLong) throws NoResultException {
        return null;
    }

    @Override
    public SpotWithoutCategoryDTO findByIdWithoutCategory(Long aLong) throws NoResultException {
        return null;
    }

    @Override
    public List<SpotWithCategoryDTO> findAllWithCategory() throws NoResultException {
        return null;
    }

    @Override
    public List<SpotWithoutCategoryDTO> findAlWithoutCategory() throws NoResultException {
        return null;
    }

    @Override
    public void update(UpdateSpotDTO updateSpotDTO) throws Exception {

    }

    @Override
    public void insert(InsertCategoryDTO insertCategoryDTO) throws Exception {

    }

    @Override
    public void deleteById(Long aLong) throws Exception {

    }
}
