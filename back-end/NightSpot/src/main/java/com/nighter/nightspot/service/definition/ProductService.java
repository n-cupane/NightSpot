package com.nighter.nightspot.service.definition;

import com.nighter.nightspot.dto.product.InsertProductDTO;
import com.nighter.nightspot.dto.product.ProductDTO;
import com.nighter.nightspot.dto.product.UpdateProductDTO;
import com.nighter.nightspot.error.exception.NoResultException;

import java.util.List;

public interface ProductService {

    ProductDTO findById(Long id) throws NoResultException;

    List<ProductDTO> findAll();

    void save(InsertProductDTO product);

    void save(UpdateProductDTO product);

    void deleteById(Long id);

}
