package com.nighter.nightspot.repository;

import com.nighter.nightspot.models.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpotRepositoryJPA extends JpaRepository<Spot,Long> {

    Optional<Spot> findByName(String name);

}
