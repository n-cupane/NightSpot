package com.nighter.nightspot.repository;

import com.nighter.nightspot.models.Category;
import com.nighter.nightspot.models.Photo;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpotRepositoryJPA extends JpaRepository<Spot,Long> {

    Optional<Spot> findByName(String name);

    @Transactional
    @Modifying
    @Query("update spot s set s.name = :name, s.position = :position, s.contact = :contact, s.timetables = :timetables, s.category = :category, s.photos = :photos")
    void update(String name, String position, String contact, String timetables, Category category, List<Photo> photos);

}