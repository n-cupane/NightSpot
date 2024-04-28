package com.nighter.nightspot.repository;

import com.nighter.nightspot.models.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpotRepositoryJPA extends JpaRepository<Spot,Long> {
}
