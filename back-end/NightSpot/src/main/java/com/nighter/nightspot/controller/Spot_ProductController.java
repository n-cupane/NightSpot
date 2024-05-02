package com.nighter.nightspot.controller;

import com.nighter.nightspot.dto.spot_product.InsertSpotProductDTO;
import com.nighter.nightspot.dto.spot_product.SpotProductDTO;
import com.nighter.nightspot.dto.spot_product.UpdateSpotProductDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.service.definition.Spot_ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
public class Spot_ProductController {

    @Autowired
    private Spot_ProductService spotProductService;

    @PostMapping("/all/spot_product/insert")
    public ResponseEntity<Void> insert(@Valid @RequestBody InsertSpotProductDTO insertSpotProductDTO) {
        try {
            spotProductService.insert(insertSpotProductDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/spot_product/update")
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateSpotProductDTO updateSpotProductDTO) {
        try {
            spotProductService.update(updateSpotProductDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/spot_product/findById/id/{id}")
    public ResponseEntity<SpotProductDTO> findById(@PathVariable Long id) throws NoResultException {
        SpotProductDTO s = spotProductService.findById(id);
        return ResponseEntity.ok(s);
    }

    @GetMapping("/spot_product/findAll")
    public ResponseEntity<List<SpotProductDTO>> findAll() throws NoResultException {
        List<SpotProductDTO> s =spotProductService.findAll();
        return ResponseEntity.ok(s);
    }

    @DeleteMapping("/spot_product/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws NoResultException {
        spotProductService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/spot_product/findBySpotId/{spotId}")
    public ResponseEntity<List<SpotProductDTO>> findBySpotId(@PathVariable Long spotId) throws NoResultException {
        List<SpotProductDTO>sList = spotProductService.findBySpotI(spotId);
        return ResponseEntity.ok(sList);
    }
}
