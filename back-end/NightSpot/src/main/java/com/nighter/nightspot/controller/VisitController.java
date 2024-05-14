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

import java.time.LocalTime;
import java.util.List;

@RestController
@CrossOrigin()
public class VisitController {

    @Autowired
    private VisitService visitService;

    @PostMapping("/auth/visit/insert")
    public ResponseEntity<Void> insert(@Valid @RequestBody InsertVisitDTO visit) {
        try {
            visitService.save(visit);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/auth/visit/update")
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateVisitDTO visit) {
        try {
            visitService.save(visit);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/auth/visit/show/id/{id}")
    public ResponseEntity<VisitDTO> showVisit(@PathVariable Long id) throws NoResultException {
        VisitDTO visit = visitService.findById(id);
        return ResponseEntity.ok(visit);
    }

    @GetMapping("/admin/visit/show-all")
    public ResponseEntity<List<VisitDTO>> showAllVisits() {
        List<VisitDTO> visits = visitService.findAll();
        return ResponseEntity.ok(visits);
    }

    @DeleteMapping("/auth/visit/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws NoResultException {
        visitService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/auth/visit/findByUserId/{userId}")
    public ResponseEntity<List<VisitDTO>> selectVisitFromUserId(@PathVariable Long userId) {
     List<VisitDTO> visits = visitService.findVisitByUserId(userId);
     return ResponseEntity.ok(visits);
    }

    @GetMapping("/auth/visit/findVisitFromSpotIDandTime/")
    public ResponseEntity<List<VisitDTO>> selectVisitFromTime(@RequestParam("sId") Long sId, @RequestParam("visitTime") LocalTime visitTime) {
        List<VisitDTO> visitDTOS = visitService.selectVisitFromTime(sId, visitTime);
        System.out.println(visitDTOS);
        return ResponseEntity.ok(visitDTOS);
    }

    @GetMapping("/admin/visit/findAllBySpot/{spotId}")
    public ResponseEntity<List<VisitDTO>> findAllBySpot(@PathVariable Long spotId) {
        List<VisitDTO> visitsOfSpot = visitService.findAllBySpot(spotId);
        return ResponseEntity.ok(visitsOfSpot);
    }

}
