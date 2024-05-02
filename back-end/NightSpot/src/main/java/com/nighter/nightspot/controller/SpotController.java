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
@CrossOrigin()
public class SpotController {

    @Autowired
    private SpotService spotService;

    @PostMapping("/admin/spot/insert")
    public ResponseEntity<Void> insert(@Valid @RequestBody InsertSpotDTO insertSpotDTO) {
        try {
            spotService.insert(insertSpotDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/admin/spot/update")
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateSpotDTO updateSpotDTO) {
        System.out.println("sssssssssssssssssss");
        try {
            System.out.println("aaaaaaaaaaaaaaaaa");
            spotService.update(updateSpotDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/auth/spot/findByIdWithoutCategory/id/{aLong}")
    public ResponseEntity<SpotWithoutCategoryDTO> findByIdWithoutCategory( @PathVariable Long aLong) throws NoResultException {

        SpotWithoutCategoryDTO s = spotService.findByIdWithoutCategory(aLong);
        return ResponseEntity.ok(s);

    }

    @GetMapping("/auth/spot/findByIdWithCategory/id/{aLong}")
    public ResponseEntity<SpotWithCategoryDTO> findByIdWithCategory( @PathVariable Long aLong) throws NoResultException {

        SpotWithCategoryDTO s = spotService.findByIdWithCategory(aLong);
        return ResponseEntity.ok(s);

    }

    @GetMapping("/auth/spot/findAllWithCategory")
    public ResponseEntity<List<SpotWithCategoryDTO>> findAllWithCategory() throws NoResultException {
        List<SpotWithCategoryDTO> s = spotService.findAllWithCategory();
        return ResponseEntity.ok(s);
    }

    @GetMapping("/all/spot/findAllWithoutCategory")
    public ResponseEntity<List<SpotWithoutCategoryDTO>> findAllWithoutCategory() throws NoResultException {
        List<SpotWithoutCategoryDTO> s = spotService.findAlWithoutCategory();
        return ResponseEntity.ok(s);
    }

    @DeleteMapping("/spot/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws NoResultException{
        spotService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/auth/spot/findByName/{name}")
    public ResponseEntity<SpotWithoutCategoryDTO> findByName(@PathVariable String name) throws NoResultException {
        SpotWithoutCategoryDTO s = spotService.findByName(name);
        return ResponseEntity.ok(s);
    }

}
