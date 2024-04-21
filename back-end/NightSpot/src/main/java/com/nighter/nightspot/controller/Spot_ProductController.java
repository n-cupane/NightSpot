package com.nighter.nightspot.controller;

import com.nighter.nightspot.dto.spot_product.InsertSpotProductDTO;
import com.nighter.nightspot.dto.spot_product.SpotProductDTO;
import com.nighter.nightspot.dto.spot_product.UpdateSpotProductDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.models.Spot_ProductKey;
import com.nighter.nightspot.service.definition.Spot_ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spot_product")
public class Spot_ProductController {

    @Autowired
    private Spot_ProductService spotProductService;

    @PostMapping("/insert")
    public ResponseEntity<Void> insert(@Valid @RequestBody InsertSpotProductDTO insertSpotProductDTO) {
        try {
            spotProductService.insert(insertSpotProductDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateSpotProductDTO updateSpotProductDTO) {
        try {
            spotProductService.update(updateSpotProductDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/findById/id/{id}")
    public ResponseEntity<SpotProductDTO> findById(@PathVariable Spot_ProductKey id) throws NoResultException {
        SpotProductDTO s = spotProductService.findById(id);
        return ResponseEntity.ok(s);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<SpotProductDTO>> findAll() throws NoResultException {
        List<SpotProductDTO> s =spotProductService.findAll();
        return ResponseEntity.ok(s);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws NoResultException {
        spotProductService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
