package com.nighter.nightspot.repository;

import com.nighter.nightspot.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepositoryJPA extends JpaRepository<Photo, Long> {
}
