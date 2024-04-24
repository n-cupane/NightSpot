package com.nighter.nightspot.controller;

import com.nighter.nightspot.dto.category.CategoryWithSpotsDTO;
import com.nighter.nightspot.dto.category.CategoryWithoutSpotsDTO;
import com.nighter.nightspot.dto.category.InsertCategoryDTO;
import com.nighter.nightspot.dto.category.UpdateCategoryDTO;
import com.nighter.nightspot.dto.spot.InsertSpotDTO;
import com.nighter.nightspot.dto.spot.SpotWithCategoryDTO;
import com.nighter.nightspot.dto.spot.SpotWithoutCategoryDTO;
import com.nighter.nightspot.dto.spot.UpdateSpotDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.service.definition.CategoryService;
import com.nighter.nightspot.service.definition.SpotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/all/category/insert")
    public ResponseEntity<Void> insert(@Valid @RequestBody InsertCategoryDTO insertCategoryDTO) {
        try {
            categoryService.insert(insertCategoryDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/category/update")
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateCategoryDTO updateCategoryDTO) {
        try {
            categoryService.update(updateCategoryDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/category/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws NoResultException {
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/findByIdWithoutSpot/id/{aLong}")
    public ResponseEntity<CategoryWithoutSpotsDTO> findByIdWithoutCategory(@PathVariable Long aLong) throws NoResultException {

        CategoryWithoutSpotsDTO c = categoryService.findByIdWithoutSpots(aLong);
        return ResponseEntity.ok(c);

    }

    @GetMapping("/category/findByIdWithSpot/id/{aLong}")
    public ResponseEntity<CategoryWithSpotsDTO> findByIdWithCategory(@PathVariable Long aLong) throws NoResultException {

        CategoryWithSpotsDTO c = categoryService.findByIdWithSpots(aLong);
        return ResponseEntity.ok(c);

    }

    @GetMapping("/category/findAllWithSpots")
    public ResponseEntity<List<CategoryWithSpotsDTO>> findAllWithSpots() throws NoResultException {
        List<CategoryWithSpotsDTO> c = categoryService.findAllWithSpots();
        return ResponseEntity.ok(c);
    }

    @GetMapping("/category/findAllWithoutCategory")
    public ResponseEntity<List<CategoryWithoutSpotsDTO>> findAllWithoutSpots() throws NoResultException {
        List<CategoryWithoutSpotsDTO> c = categoryService.findAllWithoutSpots();
        return ResponseEntity.ok(c);
    }


}
