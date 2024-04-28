package com.nighter.nightspot.service.implementation;

import com.nighter.nightspot.dto.category.InsertCategoryDTO;
import com.nighter.nightspot.dto.spot.InsertSpotDTO;
import com.nighter.nightspot.dto.spot.SpotWithCategoryDTO;
import com.nighter.nightspot.dto.spot.SpotWithoutCategoryDTO;
import com.nighter.nightspot.dto.spot.UpdateSpotDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.mapper.SpotMapper;
import com.nighter.nightspot.models.Spot;
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
        return spotMapper.toSpotDTOWithCategory(repository.findById(aLong)
                .orElseThrow(() -> new NoResultException("Spot with id " + aLong + " not found")));
    }

    @Override
    public SpotWithoutCategoryDTO findByIdWithoutCategory(Long aLong) throws NoResultException {
        return spotMapper.toSpotDTOWithoutCategory(repository.findById(aLong)
                .orElseThrow(()->new NoResultException("spot with id " + aLong + " not found")));
    }

    @Override
    public List<SpotWithCategoryDTO> findAllWithCategory() throws NoResultException {
        return repository.findAll().stream().map(t->spotMapper.toSpotDTOWithCategory(t)).toList();
    }

    @Override
    public List<SpotWithoutCategoryDTO> findAlWithoutCategory() throws NoResultException {
        return repository.findAll().stream().map(t->spotMapper.toSpotDTOWithoutCategory(t)).toList();
    }

    @Override
    public void update(UpdateSpotDTO updateSpotDTO) throws Exception {
        repository.save(spotMapper.fromUpdateSpotDTO(updateSpotDTO));

    }

    @Override
    public void insert(InsertSpotDTO insertSpotDTO) throws Exception {
        repository.save(spotMapper.fromInsertSpotDTO(insertSpotDTO));
    }

    @Override
    public void deleteById(Long aLong) throws NoResultException {
        repository.deleteById(aLong);
    }

    @Override
    public SpotWithoutCategoryDTO findByName(String name) throws NoResultException {
        return spotMapper.toSpotDTOWithoutCategory(
                repository.findByName(name)
                        .orElseThrow(() -> new NoResultException("Spot with name " + name + " not found"))
        );
    }
}
