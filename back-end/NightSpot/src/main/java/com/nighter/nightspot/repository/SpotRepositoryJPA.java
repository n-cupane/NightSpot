package com.nighter.nightspot.repository;

import com.nighter.nightspot.models.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepositoryJPA extends JpaRepository<Spot,Long> {
}
