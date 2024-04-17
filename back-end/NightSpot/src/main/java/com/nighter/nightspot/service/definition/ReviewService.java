package com.nighter.nightspot.service.definition;

import com.nighter.nightspot.dto.review.InsertReviewDTO;
import com.nighter.nightspot.dto.review.ReviewDTO;
import com.nighter.nightspot.dto.review.UpdateReviewDTO;
import com.nighter.nightspot.error.exception.NoResultException;

import java.util.List;

public interface ReviewService {

    ReviewDTO findById(Long aLong) throws NoResultException;

    List<ReviewDTO> findAll() throws NoResultException;

    void update(UpdateReviewDTO updateReviewDTO) throws Exception;

    void insert(InsertReviewDTO insertReviewDTO) throws Exception;

    void deleteById(Long aLong) throws Exception;

}
