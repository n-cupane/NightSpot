package com.nighter.nightspot.controller;

import com.nighter.nightspot.dto.spot.InsertSpotDTO;
import com.nighter.nightspot.dto.spot.SpotWithCategoryDTO;
import com.nighter.nightspot.dto.spot.SpotWithoutCategoryDTO;
import com.nighter.nightspot.dto.spot.UpdateSpotDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.service.definition.SpotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.Controller;

import java.util.List;

@RestController
@RequestMapping("/spot")
public class SpotController {

    @Autowired
    private SpotService spotService;

    @PostMapping("/insert")
    public ResponseEntity<Void> insert(@Valid @RequestBody InsertSpotDTO insertSpotDTO) {
        try {
            spotService.insert(insertSpotDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateSpotDTO updateSpotDTO) {
        try {
            spotService.update(updateSpotDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/findByIdWithoutCategory/id/{aLong}")
    public ResponseEntity<SpotWithoutCategoryDTO> findByIdWithoutCategory( @PathVariable Long aLong) throws NoResultException {

        SpotWithoutCategoryDTO s = spotService.findByIdWithoutCategory(aLong);
        return ResponseEntity.ok(s);

    }

    @GetMapping("/findByIdWithCategory/id/{aLong}")
    public ResponseEntity<SpotWithCategoryDTO> findByIdWithCategory( @PathVariable Long aLong) throws NoResultException {

        SpotWithCategoryDTO s = spotService.findByIdWithCategory(aLong);
        return ResponseEntity.ok(s);

    }

    @GetMapping("/findAllWithCategory")
    public ResponseEntity<List<SpotWithCategoryDTO>> findAllWithCategory() throws NoResultException {
        List<SpotWithCategoryDTO> s = spotService.findAllWithCategory();
        return ResponseEntity.ok(s);
    }

    @GetMapping("/findAllWithoutCategory")
    public ResponseEntity<List<SpotWithoutCategoryDTO>> findAllWithoutCategory() throws NoResultException {
        List<SpotWithoutCategoryDTO> s = spotService.findAlWithoutCategory();
        return ResponseEntity.ok(s);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws NoResultException{
        spotService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
