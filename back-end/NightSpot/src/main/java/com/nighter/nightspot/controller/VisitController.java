package com.nighter.nightspot.controller;

import com.nighter.nightspot.dto.visit.InsertVisitDTO;
import com.nighter.nightspot.dto.visit.UpdateVisitDTO;
import com.nighter.nightspot.dto.visit.VisitDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.models.Visit;
import com.nighter.nightspot.repository.VisitRepositoryJPA;
import com.nighter.nightspot.service.definition.VisitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    private VisitService visitService;

    @PostMapping("/insert")
    public ResponseEntity<Void> insert(@Valid @RequestBody InsertVisitDTO visit) {
        try {
            visitService.save(visit);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateVisitDTO visit) {
        try {
            visitService.save(visit);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/show/id/{uId}/{sId}")
    public ResponseEntity<VisitDTO> showVisit(@PathVariable Long uId, @PathVariable Long sId) throws NoResultException {
        VisitDTO visit = visitService.findByIds(uId, sId);
        return ResponseEntity.ok(visit);
    }

    @GetMapping("/show-all")
    public ResponseEntity<List<VisitDTO>> showAllVisits() {
        List<VisitDTO> visits = visitService.findAll();
        return ResponseEntity.ok(visits);
    }

    @DeleteMapping("/delete/{uId}/{sId}")
    public ResponseEntity<Void> delete(@PathVariable Long uId, @PathVariable Long sId) throws NoResultException {
        visitService.deleteByIds(uId, sId);
        return ResponseEntity.ok().build();
    }

}
