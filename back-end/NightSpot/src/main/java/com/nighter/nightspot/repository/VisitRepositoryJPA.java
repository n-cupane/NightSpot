package com.nighter.nightspot.repository;

import com.nighter.nightspot.models.Visit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VisitRepositoryJPA extends JpaRepository<Visit, Long> {

    @Query("select v from Visit v where v.spot.id = :sId and v.user.id = :uId")
    Optional<Visit> findByIds(Long uId, Long sId);

    @Transactional
    @Modifying
    @Query("delete from Visit v where v.spot.id = :sId and v.user.id = :uId")
    void deleteByIds(Long uId, Long sId);

}
