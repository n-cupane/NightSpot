package com.nighter.nightspot.repository;

import com.nighter.nightspot.models.Spot_Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface Spot_ProductRepositoryJPA extends JpaRepository<Spot_Product,Long> {

    @Query("select sp from spot_product sp where sp.id=:id")
    Optional<Spot_Product> findById(Long id);

    @Query("select sp from spot_product sp where sp.spot.id = :id")
    List<Spot_Product> findBySpotId(Long id);

}
