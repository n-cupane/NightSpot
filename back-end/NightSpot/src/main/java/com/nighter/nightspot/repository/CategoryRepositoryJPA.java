package com.nighter.nightspot.repository;

import com.nighter.nightspot.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepositoryJPA extends JpaRepository<Category,Long> {

    Optional<Category> findByName(String name);

}
