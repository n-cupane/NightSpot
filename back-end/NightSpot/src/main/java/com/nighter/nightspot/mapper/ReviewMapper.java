package com.nighter.nightspot.mapper;

import com.nighter.nightspot.dto.category.CategoryWithSpotsDTO;
import com.nighter.nightspot.dto.category.CategoryWithoutSpotsDTO;
import com.nighter.nightspot.dto.category.InsertCategoryDTO;
import com.nighter.nightspot.dto.category.UpdateCategoryDTO;
import com.nighter.nightspot.dto.review.InsertReviewDTO;
import com.nighter.nightspot.dto.review.ReviewDTO;
import com.nighter.nightspot.dto.review.UpdateReviewDTO;
import com.nighter.nightspot.models.Category;
import com.nighter.nightspot.models.Review;
import com.nighter.nightspot.service.implementation.UserServiceJPA;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring",uses = UserServiceJPA.class)
public interface ReviewMapper {

    public Review fromReviewDTO(ReviewDTO reviewDTO);

    public ReviewDTO toReviewDTO(Review review);

    public Review fromInsertReviewDTO(InsertReviewDTO insertReviewDTO);

    public Review fromUpdateReviewDTO(UpdateReviewDTO updateReviewDTO);

}
