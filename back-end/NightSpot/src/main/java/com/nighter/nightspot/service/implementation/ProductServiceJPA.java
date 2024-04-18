package com.nighter.nightspot.service.implementation;

import com.nighter.nightspot.dto.product.InsertProductDTO;
import com.nighter.nightspot.dto.product.ProductDTO;
import com.nighter.nightspot.dto.product.UpdateProductDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.mapper.ProductMapper;
import com.nighter.nightspot.repository.ProductRepositoryJPA;
import com.nighter.nightspot.service.definition.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceJPA implements ProductService {

    @Autowired
    private ProductRepositoryJPA repo;

    private final ProductMapper mapper;


    @Override
    public ProductDTO findById(Long id) throws NoResultException {
        return mapper.toProductDTO(
                repo.findById(id)
                        .orElseThrow(
                                () -> new NoResultException("Product with id " + id + " not found")
                        )
        );
    }

    @Override
    public List<ProductDTO> findAll() {
        return repo.findAll().stream()
                .map(mapper::toProductDTO)
                .toList();
    }

    @Override
    public void save(InsertProductDTO product) {
        repo.save(
                mapper.fromInsertProductDTO(product)
        );
    }

    @Override
    public void save(UpdateProductDTO product) {
        repo.save(
                mapper.fromUpdateProductDTO(product)
        );
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
