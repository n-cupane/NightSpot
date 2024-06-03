package com.nighter.nightspot.mapper;

import com.nighter.nightspot.dto.visit.InsertVisitDTO;
import com.nighter.nightspot.dto.visit.UpdateVisitDTO;
import com.nighter.nightspot.dto.visit.VisitDTO;
import com.nighter.nightspot.models.Visit;
import com.nighter.nightspot.service.implementation.UserServiceJPA;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface VisitMapper {

    VisitDTO toVisitDTO(Visit visit);

    Visit fromInsertVisitDTO(InsertVisitDTO insertVisitDTO);

    Visit fromUpdateVisitDTO(UpdateVisitDTO updateVisitDTO);

}
