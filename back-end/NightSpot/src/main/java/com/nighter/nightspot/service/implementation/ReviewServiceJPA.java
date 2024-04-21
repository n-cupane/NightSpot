package com.nighter.nightspot.service.implementation;

import com.nighter.nightspot.dto.review.InsertReviewDTO;
import com.nighter.nightspot.dto.review.ReviewDTO;
import com.nighter.nightspot.dto.review.UpdateReviewDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.mapper.ReviewMapper;
import com.nighter.nightspot.repository.ReviewRepositoryJPA;
import com.nighter.nightspot.service.definition.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceJPA implements ReviewService {

    @Autowired
    private ReviewRepositoryJPA reviewRepositoryJPA;

    private final ReviewMapper reviewMapper;
    @Override
    public ReviewDTO findById(Long aLong) throws NoResultException {
        return reviewMapper.toReviewDTO(reviewRepositoryJPA.findById(aLong).orElseThrow(()-> new NoResultException("Review with id " + aLong + " not found")));
    }

    @Override
    public List<ReviewDTO> findAll() throws NoResultException {
        return reviewRepositoryJPA.findAll().stream().map(t->reviewMapper.toReviewDTO(t)).toList();
    }

    @Override
    public void update(UpdateReviewDTO updateReviewDTO) throws Exception {
        reviewRepositoryJPA.save(reviewMapper.fromUpdateReviewDTO(updateReviewDTO));
    }

    @Override
    public void insert(InsertReviewDTO insertReviewDTO) throws Exception {
        reviewRepositoryJPA.save(reviewMapper.fromInsertReviewDTO(insertReviewDTO));
    }

    @Override
    public void deleteById(Long aLong) throws NoResultException {
        reviewRepositoryJPA.deleteById(aLong);
    }
}
