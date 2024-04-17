package com.nighter.nightspot.repository;

import com.nighter.nightspot.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepositoryJPA extends JpaRepository<Review,Long> {
}
