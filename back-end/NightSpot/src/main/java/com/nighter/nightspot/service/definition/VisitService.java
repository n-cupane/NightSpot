package com.nighter.nightspot.service.definition;

import com.nighter.nightspot.dto.visit.InsertVisitDTO;
import com.nighter.nightspot.dto.visit.UpdateVisitDTO;
import com.nighter.nightspot.dto.visit.VisitDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.models.Visit;

import java.time.LocalTime;
import java.util.List;

public interface VisitService {

    VisitDTO findById(Long id) throws NoResultException;

    List<VisitDTO> findAll();

    void save(InsertVisitDTO visit);

    void save(UpdateVisitDTO visit);

    void deleteById(Long id);

    VisitDTO findByIds(Long uId, Long sId) throws NoResultException;

    void deleteByIds(Long uId, Long sId) throws NoResultException;

    List<VisitDTO> findVisitByUserId(Long UserId);

    List<VisitDTO> selectVisitFromTime(Long sId, LocalTime visitTIme);

}
