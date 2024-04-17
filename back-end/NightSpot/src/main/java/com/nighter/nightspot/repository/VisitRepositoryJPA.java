package com.nighter.nightspot.repository;

import com.nighter.nightspot.models.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepositoryJPA extends JpaRepository<Visit, Long> {
}
