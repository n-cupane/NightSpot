package com.nighter.nightspot.service.implementation;

import com.nighter.nightspot.dto.visit.InsertVisitDTO;
import com.nighter.nightspot.dto.visit.UpdateVisitDTO;
import com.nighter.nightspot.dto.visit.VisitDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.mapper.VisitMapper;
import com.nighter.nightspot.repository.VisitRepositoryJPA;
import com.nighter.nightspot.service.definition.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitServiceJPA implements VisitService {

    @Autowired
    private VisitRepositoryJPA repo;

    private final VisitMapper mapper;


    @Override
    public VisitDTO findById(Long id) throws NoResultException {
        return mapper.toVisitDTO(
                repo.findById(id)
                        .orElseThrow(
                                () -> new NoResultException("Visit with id " + id + " not found")
                        )
        );
    }

    @Override
    public List<VisitDTO> findAll() {
        return repo.findAll().stream()
                .map(mapper::toVisitDTO)
                .toList();
    }

    @Override
    public void save(InsertVisitDTO visit) {
        repo.save(
                mapper.fromInsertVisitDTO(visit)
        );
    }

    @Override
    public void save(UpdateVisitDTO visit) {
        repo.save(
                mapper.fromUpdateVisitDTO(visit)
        );
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public VisitDTO findByIds(Long uId, Long sId) throws NoResultException {
        return mapper.toVisitDTO(
                repo.findByIds(uId, sId)
                        .orElseThrow(
                                () -> new NoResultException("Visit with user id " + uId + " and spot id " + sId + " not found")
                        )
        );
    }

    @Override
    public void deleteByIds(Long uId, Long sId) throws NoResultException {
        repo.deleteByIds(uId, sId);
    }
}
