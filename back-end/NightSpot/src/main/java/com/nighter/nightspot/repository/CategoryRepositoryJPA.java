package com.nighter.nightspot.repository;

import com.nighter.nightspot.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositoryJPA extends JpaRepository<Category,Long> {
}
