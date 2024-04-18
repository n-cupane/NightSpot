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
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/insert")
    public ResponseEntity<Void> insert(@Valid @RequestBody InsertReviewDTO insertReviewDTO) {
        try {
            reviewService.insert(insertReviewDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateReviewDTO updateReviewDTO) {
        try {
            reviewService.update(updateReviewDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/findById/id/{id}")
    public ResponseEntity<ReviewDTO> findById(@PathVariable Long id) throws NoResultException {
        ReviewDTO r = reviewService.findById(id);
        return ResponseEntity.ok(r);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ReviewDTO>> findAll() throws NoResultException{
        List<ReviewDTO> r = reviewService.findAll();
        return ResponseEntity.ok(r);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable  Long id) throws NoResultException {
        reviewService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
