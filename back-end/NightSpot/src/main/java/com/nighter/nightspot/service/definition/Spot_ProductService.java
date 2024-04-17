package com.nighter.nightspot.service.definition;


import com.nighter.nightspot.dto.spot_product.InsertSpotProductDTO;
import com.nighter.nightspot.dto.spot_product.SpotProductDTO;
import com.nighter.nightspot.dto.spot_product.UpdateSpotProductDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.models.Spot_ProductKey;

import java.util.List;

public interface Spot_ProductService {

    SpotProductDTO findById(Spot_ProductKey id) throws NoResultException;

    List<SpotProductDTO> findAll() throws NoResultException;

    void update(UpdateSpotProductDTO updateSpotProductDTO) throws Exception;

    void insert(InsertSpotProductDTO insertSpotProductDTO) throws Exception;

    void deleteById(Long aLong) throws Exception;

}
