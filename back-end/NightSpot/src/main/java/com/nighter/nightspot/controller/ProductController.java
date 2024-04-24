package com.nighter.nightspot.controller;

import com.nighter.nightspot.dto.product.InsertProductDTO;
import com.nighter.nightspot.dto.product.ProductDTO;
import com.nighter.nightspot.dto.product.UpdateProductDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.service.definition.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/insert")
    public ResponseEntity<Void> insertProduct(@Valid @RequestBody InsertProductDTO product) {
        try {
            productService.save(product);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/product/update")
    public ResponseEntity<Void> updateProduct(@Valid @RequestBody UpdateProductDTO product) {
        try {
            productService.save(product);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/product/show/id/{id}")
    public ResponseEntity<ProductDTO> showProduct(@PathVariable Long id) throws NoResultException {
        ProductDTO product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/product/show-all")
    public ResponseEntity<List<ProductDTO>> showAllProduct() {
        List<ProductDTO> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws NoResultException {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
