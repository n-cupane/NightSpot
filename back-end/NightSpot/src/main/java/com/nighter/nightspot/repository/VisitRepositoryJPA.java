package com.nighter.nightspot.repository;

import com.nighter.nightspot.dto.visit.VisitDTO;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.models.Visit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface VisitRepositoryJPA extends JpaRepository<Visit, Long> {

    @Query("select v from Visit v where v.spot.id = :sId and v.user.id = :uId")
    Optional<Visit> findByIds(Long uId, Long sId);

    @Transactional
    @Modifying
    @Query("delete from Visit v where v.spot.id = :sId and v.user.id = :uId")
    void deleteByIds(Long uId, Long sId);
    @Query("select v from Visit v where v.user.id = :uId")
    List<Visit> selectVisitFromUserId(Long uId);
    @Query("SELECT v FROM Visit v JOIN v.user u WHERE v.spot.id = :sId AND v.visitTime = :visitTime")
    List<Visit> selectVisitFromTime(Long sId, LocalTime visitTime);


}
