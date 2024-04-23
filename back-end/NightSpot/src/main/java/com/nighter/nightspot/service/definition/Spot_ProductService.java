package com.nighter.nightspot.service.definition;


import com.nighter.nightspot.dto.spot_product.InsertSpotProductDTO;
import com.nighter.nightspot.dto.spot_product.SpotProductDTO;
import com.nighter.nightspot.dto.spot_product.UpdateSpotProductDTO;
import com.nighter.nightspot.error.exception.NoResultException;

import java.util.List;

public interface Spot_ProductService {

    SpotProductDTO findById(Long id) throws NoResultException;

    List<SpotProductDTO> findAll() throws NoResultException;

    void update(UpdateSpotProductDTO updateSpotProductDTO) throws Exception;

    void insert(InsertSpotProductDTO insertSpotProductDTO) throws Exception;

    void deleteById(Long aLong) throws NoResultException;

}
