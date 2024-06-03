package com.nighter.nightspot.mapper;

import com.nighter.nightspot.dto.product.InsertProductDTO;
import com.nighter.nightspot.dto.product.ProductDTO;
import com.nighter.nightspot.dto.product.UpdateProductDTO;
import com.nighter.nightspot.models.Product;
import com.nighter.nightspot.service.implementation.UserServiceJPA;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface ProductMapper {

    ProductDTO toProductDTO(Product product);

    Product fromInsertProductDTO(InsertProductDTO insertProductDTO);

    Product fromUpdateProductDTO(UpdateProductDTO updateProductDTO);

}
