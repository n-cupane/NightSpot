package com.nighter.nightspot.controller;

import com.nighter.nightspot.dto.review.InsertReviewDTO;
import com.nighter.nightspot.dto.review.ReviewDTO;
import com.nighter.nightspot.dto.review.UpdateReviewDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.service.definition.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/review/insert")
    public ResponseEntity<Void> insert(@Valid @RequestBody InsertReviewDTO insertReviewDTO) {
        try {
            reviewService.insert(insertReviewDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/review/update")
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateReviewDTO updateReviewDTO) {
        try {
            reviewService.update(updateReviewDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/review/findById/id/{id}")
    public ResponseEntity<ReviewDTO> findById(@PathVariable Long id) throws NoResultException {
        ReviewDTO r = reviewService.findById(id);
        return ResponseEntity.ok(r);
    }

    @GetMapping("/review/findAll")
    public ResponseEntity<List<ReviewDTO>> findAll() throws NoResultException{
        List<ReviewDTO> r = reviewService.findAll();
        return ResponseEntity.ok(r);
    }

    @DeleteMapping("/review/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable  Long id) throws NoResultException {
        reviewService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
