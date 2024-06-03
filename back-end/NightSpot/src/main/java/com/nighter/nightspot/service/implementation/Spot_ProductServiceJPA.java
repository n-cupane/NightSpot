package com.nighter.nightspot.service.implementation;

import com.nighter.nightspot.dto.spot_product.InsertSpotProductDTO;
import com.nighter.nightspot.dto.spot_product.SpotProductDTO;
import com.nighter.nightspot.dto.spot_product.UpdateSpotProductDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.mapper.Spot_ProductMapper;
import com.nighter.nightspot.models.Spot_Product;
import com.nighter.nightspot.repository.Spot_ProductRepositoryJPA;
import com.nighter.nightspot.service.definition.Spot_ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class Spot_ProductServiceJPA implements Spot_ProductService {

    @Autowired
    private Spot_ProductRepositoryJPA spotProductRepositoryJPA;

    private final Spot_ProductMapper spotProductMapper;
    @Override
    public SpotProductDTO findById(Long id) throws NoResultException {
        return spotProductMapper.toSpotProductDTO(spotProductRepositoryJPA.findById(id).orElseThrow(() -> new NoResultException("Spot_Product with id " + id + " not found")));
    }

    @Override
    public List<SpotProductDTO> findAll() throws NoResultException {
        return spotProductRepositoryJPA.findAll().stream().map(t-> spotProductMapper.toSpotProductDTO(t)).toList();
    }

    @Override
    public void update(UpdateSpotProductDTO updateSpotProductDTO) throws Exception {
        spotProductRepositoryJPA.save(spotProductMapper.fromUpdateSpotProductDTO(updateSpotProductDTO));
    }

    @Override
    public void insert(InsertSpotProductDTO insertSpotProductDTO) throws Exception {
        spotProductRepositoryJPA.save(spotProductMapper.fromInsertSpotProductDTO(insertSpotProductDTO));
    }

    @Override
    public void deleteById(Long aLong) throws NoResultException {
        spotProductRepositoryJPA.deleteById(aLong);
    }

    @Override
    public List<SpotProductDTO> findBySpotI(Long id) throws NoResultException {
        return spotProductRepositoryJPA.findBySpotId(id).stream().map(t-> spotProductMapper.toSpotProductDTO(t)).toList();
    }
}
